package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.DateUtil;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.entity.AccountFocus;
import com.htkapp.modules.API.service.AccountFocusService;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.integral.dto.AccountIntegralList;
import com.htkapp.modules.merchant.integral.entity.AccountSaverTicket;
import com.htkapp.modules.merchant.integral.entity.IntegralManageRecord;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;
import com.htkapp.modules.merchant.integral.entity.ShopArticleInfo;
import com.htkapp.modules.merchant.integral.service.*;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.htkapp.core.OtherUtils.getLoginUserByRequest;
import static com.htkapp.core.OtherUtils.getPageByKey;
import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

@Service
public class IntegralManageServiceImpl implements IntegralManageService {

	@Resource
	private IntegralService integralService;
	@Resource
	private AccountFocusService accountFocusService;
	@Resource
	private IntegralManageRecordService integralManageRecordService;
	@Resource
	private ShopServiceI shopService;
	@Resource
	private ShopArticleInfoService shopArticleInfoService;
	@Resource
	private AccountSaverTicketService accountSaverTicketService;
	@Resource
	private AccountShopServiceI accountShopService;
	@Resource
	private SeatOrderService seatOrderService;
	@Resource
	private MoreMethodsUtils moreMethodsUtils;

	/* =====================接口开始========================= */
	//获取用户积分列表数据
	@Override
	public TableResponseModel getAccountIntegralListJsonDataById(HttpServletRequest request) {
		try {
			Integer accountShopId = getLoginUserByRequest().getUserId();
			int pageNo = getPageByKey(request, "pageSize");
			int pageLimit = getPageByKey(request, "pageSize");
			String userName = request.getParameter("userName");
			if (StringUtils.isEmpty(userName)) {
				userName = null;
			}
			List<AccountIntegralList> resultList = integralService.getAccountIntegralListById(accountShopId, userName, pageNo, pageLimit);
			if (resultList != null) {
				return new TableResponseModel<List<AccountIntegralList>>(new PageInfo<AccountIntegralList>(resultList).getPages(), resultList);
			} else {
				return new TableResponseModel<>(0, null);
			}
		} catch (Exception e) {
			return new TableResponseModel<>(0, null);
		}
	}
	/* ======================接口结束========================== */


	/* ====================JSP页面接口开始=========================== */
	//赠送或抵扣积分接口
	@Override
	@Transactional
	public AjaxResponseModel presentOrDeductionIntegral(AjaxRequestParams params) {
		try {
			String accountShopToken = OtherUtils.getLoginUserByRequest().getToken();
			//根据拿到的用户信息查找积分值
			AccountFocus accountFocus = accountFocusService.getAccountFocusByUserPhone(params.getUserPhone(), accountShopToken);
			if (accountFocus != null) {
				//先查出积分数量
				int nowVal = integralService.getVal(accountFocus.getAccountToken(), accountFocus.getShopId());
				int newVal = 0;


				//0抵扣积分  1赠送积分
				//                Date date = new Date();
				//                Timestamp timestamp = new Timestamp(date.getTime());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				Date d = new Date();
				String dateStr = df.format(d);

				if (params.getOperationId() == 0) {
					newVal = (nowVal - params.getValue()) < 0 ? 0 : (nowVal - params.getValue());

					/**
					 * @author 马鹏昊
					 * @desc 修改最近消费时间（gmt_modified字段）
					 */
					integralService.updateLatestConsumeTime(accountFocus.getAccountToken(), accountFocus.getShopId(), dateStr);

				} else if (params.getOperationId() == 1) {
					newVal = nowVal + params.getValue();

					/**
					 * @author 马鹏昊
					 * @desc 修改最近获得时间（gmt_latest_get字段）
					 */
					integralService.updateLatestGetTime(accountFocus.getAccountToken(), accountFocus.getShopId(), dateStr);

				}
				integralService.updateIntegral(accountFocus.getAccountToken(), accountFocus.getShopId(), newVal);
				//                integralService.presentOrDeductionIntegralByToken(accountFocus.getAccountToken(), accountFocus.getShopId(), params.getValue(), params.getOperationId());
				//通过商铺id查找商铺信息
				//记录积分变动记录
				String recordTypeStr = params.getOperationId() == 0 ? "抵扣积分" : "赠送积分";
				IntegralManageRecord record = new IntegralManageRecord(recordTypeStr, accountShopToken, accountFocus.getAccountToken(), params.getValue());
				integralManageRecordService.insertRecordByToken(record);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} else {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, params.getUserPhone() + "没有加入当前店铺,无法操作");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}

	//创建资讯
	@Override
	public AjaxResponseModel createNewActive(ShopArticleInfo articleInfo) {
		if (articleInfo != null) {
			try {
				LoginUser user = OtherUtils.getLoginUserByRequest();
				Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(), 0);
				articleInfo.setShopId(shop.getShopId());
				shopArticleInfoService.insertShopArticleInfoDAO(articleInfo);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		} else {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}

	//创建资讯图片上传
	@Override
	public AjaxResponseModel uploadMsgImg(MultipartFile file) {
		try {
			String imgUrl = FileUploadUtils.appUploadAvatarImg(file, "merchant/message/");
			return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", imgUrl);
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}

	//创建资讯图片上传
	@Override
	public String uploadNewsContentImg(MultipartFile file) {
		try {
			System.out.println("-----==================进入了uploadNewsContentImg");
			String imgUrl = FileUploadUtils.appUploadContentImg(file, "merchant/newContentImg/");
			return imgUrl;
		} catch (Exception e) {

			return "{\"code\": 1,\"msg\": \"成功\"}";
		}

	}

	@Override
	public AjaxResponseModel uploadMsgTitleImg(MultipartFile file) {
		try {
			System.out.println("-----==================进入了uploadNewsContentImg");
			String imgUrl = FileUploadUtils.appUploadMsgTitleImg(file, "merchant/message/");
			return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", imgUrl);
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}

	//创建兑换活动
	@Override
	public AjaxResponseModel createExchangeActivity(AccountSaverTicket saverTicket, String startTime, String endTime) {
		if (saverTicket != null) {
			//插入兑换活动
			try {
				saverTicket.settStartTime(startTime);
				saverTicket.settExpiration(endTime);
				LoginUser user = OtherUtils.getLoginUserByRequest();
				//取外卖店铺
				Shop shop = shopService.getShopByAccountShopIdAndMark(user.getUserId(), 0);
				saverTicket.setShopId(shop.getShopId());
				accountSaverTicketService.insertSaverTicketByShopId(saverTicket);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		} else {
			return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
		}
	}

	//未开始活动
	@Override
	public void getExitsActiveNoStartPage(RequestParams params) {
		//标识-判断
		if (params != null) {
			try {
				LoginUser user = OtherUtils.getLoginUserByRequest();
				Shop shop = shopService.getShopByAccountShopIdAndMark(user.getUserId(), 0);
				String orderDesc = "gmt_create desc";
				String time = format(new Date(), NORM_DATETIME_PATTERN);
				List<AccountSaverTicket> resultList = accountSaverTicketService.getSaverTicketListByCondition(shop.getShopId(), orderDesc, time, 0);
				if (resultList != null) {
					Gson gson = new Gson();
					for (AccountSaverTicket each : resultList) {
						each.setJsonStr(gson.toJson(each));
					}
				}
				Model model = params.getModel();
				System.out.println("未开始活动");
				model.addAttribute("data", resultList);
			} catch (Exception e) {
				return;
			}
		}
	}

	//进行中活动
	@Override
	public void getExitsActiveProcessPage(RequestParams params) {
		if (params != null) {
			try {
				LoginUser user = OtherUtils.getLoginUserByRequest();
				Shop shop = shopService.getShopByAccountShopIdAndMark(user.getUserId(), 0);
				String orderDesc = "gmt_create desc";
				String time = format(new Date(), NORM_DATETIME_PATTERN);
				List<AccountSaverTicket> resultList = accountSaverTicketService.getSaverTicketListByCondition(shop.getShopId(), orderDesc, time, 1);
				if (resultList != null) {
					Gson gson = new Gson();
					for (AccountSaverTicket each : resultList) {
						each.setJsonStr(gson.toJson(each));
					}
				}
				Model model = params.getModel();
				System.out.println("进行中活动");
				model.addAttribute("data", resultList);
			} catch (Exception e) {
				return;
			}
		}
	}

	//已停止活动
	@Override
	public void getExitsActiveStopPage(RequestParams params) {
		if (params != null) {
			try {
				LoginUser user = OtherUtils.getLoginUserByRequest();
				Shop shop = shopService.getShopByAccountShopIdAndMark(user.getUserId(), 0);
				String orderDesc = "gmt_create desc";
				String time = format(new Date(), NORM_DATETIME_PATTERN);
				List<AccountSaverTicket> resultList = accountSaverTicketService.getSaverTicketListByCondition(shop.getShopId(), orderDesc, time, 2);
				if (resultList != null) {
					Gson gson = new Gson();
					for (AccountSaverTicket each : resultList) {
						each.setJsonStr(gson.toJson(each));
					}
				}
				Model model = params.getModel();
				System.out.println("已停止活动");
				model.addAttribute("data", resultList);
			} catch (Exception e) {
				return;
			}
		}
	}

	//资讯
	@Override
	public void getExitsActiveMessagePage(RequestParams params) {
		if (params != null) {
			Model model = params.getModel();
			try {
				int pageNumber = Globals.DEFAULT_PAGE_NO;
				int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
				if (params.getPageNum() > 1) {
					pageNumber = params.getPageNum();
				}
				LoginUser user = OtherUtils.getLoginUserByRequest();
				String orderDesc = "shop_article_info.gmt_create desc";
				List<ShopArticleInfo> resultList = shopArticleInfoService.getShopArticleInfoById(user.getUserId(), orderDesc, pageNumber, pageLimit);
				if (resultList != null) {
					Gson gson = new Gson();
					for (ShopArticleInfo each : resultList) {
						each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
						each.setJsonStr(gson.toJson(each));
					}
				}
				System.out.println("资讯");
				model.addAttribute("data", resultList);
				PageInfo pageInfo = new PageInfo<>(resultList);
				model.addAttribute("pageInfo", pageInfo);
			} catch (Exception e) {
				return;
			}
		}
	}

	//作废活动接口
	@Override
	public AjaxResponseModel obsoleteActivity(AjaxRequestParams params) {
		if (params != null) {
			try {
				String time = format(new Date(), NORM_DATETIME_PATTERN);
				accountSaverTicketService.updateActiveCloseTime(params.getId(), time);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		} else {
			return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
		}
	}

	//资讯关闭显示接口
	@Override
	public AjaxResponseModel closeTheDisplay(AjaxRequestParams params) {
		if (params != null) {
			try {
				ShopArticleInfo shopArticleInfo = shopArticleInfoService.getShopArticleInfoById(params.getId());
				if (shopArticleInfo == null) {
					return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
				}
				int stateId;
				if (shopArticleInfo.getState() == -1) {
					stateId = 0;
				} else {
					stateId = -1;
				}
				//关闭显示
				shopArticleInfoService.updateArticleInfoShowState(params.getId(), stateId);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		} else {
			return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
		}
	}

	//开启活动
	@Override
	public AjaxResponseModel openActivity(AjaxRequestParams params) {
		if (params != null) {
			try {
				String time = format(new Date(), NORM_DATETIME_PATTERN);
				accountSaverTicketService.updateActiveOpenTime(params.getId(), time);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		} else {
			return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
		}
	}

	//修改资讯
	@Override
	public AjaxResponseModel updateMes(ShopArticleInfo shopArticleInfo) {
		if (shopArticleInfo != null) {
			try {
				shopArticleInfoService.updateMesById(shopArticleInfo);
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
			} catch (Exception e) {
				return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
			}
		} else {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
		}
	}


	@Override
	public AjaxResponseModel getSeatInfo() {
		int accountShopId;
		List<SeatOrder> list=new ArrayList<>();
		try {
			accountShopId = OtherUtils.getLoginUserByRequest().getUserId();
			Shop takeOutShop=shopService.getShopByAccountShopIdAndMark(accountShopId,0);
			list=seatOrderService.getSeatOrderListByShopId(takeOutShop.getShopId().toString(),null,null);
			if(list.size()>0) {
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION,"查询成功",list);
			}
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"目前没有预定订单");
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"查询错误，请联系管理员");
		}
	}


	@Override
	public AjaxResponseModel updataSeatInfo(String seatName,String orderNumber) {
		try {
			SeatOrder order=seatOrderService.getSeatOrderByOrderNumber(orderNumber);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("orderNumber", order.getOrderNumber());
			jsonObject.put("orderState", order.getStatus());
			jsonObject.put("orderId", order.getId());
			Jpush.jPushMethod(order.getAccountToken(),"预定座位成功","ALERT");
			Jpush.jPushMethod(order.getAccountToken(),jsonObject.toJSONString(),"");
			seatOrderService.updataSeatInfo(seatName, orderNumber);
			return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION,"更新成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"查询错误，请联系管理员");
		}
	}


	@Override
	public AjaxResponseModel getSeatInfoByStatus() {
		int accountShopId;
		List<SeatOrder> list=new ArrayList<>();
		try {
			accountShopId = OtherUtils.getLoginUserByRequest().getUserId();
			Shop takeOutShop=shopService.getShopByAccountShopIdAndMark(accountShopId,0);
			list=seatOrderService.getSeatOrderListByShopIdAndStatus(takeOutShop.getShopId().toString());
			if(list.size()>0) {
				return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION,"查询成功",list);
			}
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"目前没有预定订单");
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,"查询错误，请联系管理员");
		}
	}


	@Override
	public AjaxResponseModel deleteSeatOrder(String selectedIds) {
		try {
			//转成int型id
			String[] idStrs = selectedIds.split(",");
			List<String> idInts = new ArrayList<>();
			for (String idStr : idStrs) {
				String i = idStr;
				SeatOrder order=seatOrderService.getSeatOrderByOrderNumber(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("orderNumber", order.getOrderNumber());
				jsonObject.put("orderState", order.getStatus());
				jsonObject.put("orderId", order.getId());
				Jpush.jPushMethod(order.getAccountToken(),"由于座位已满，预定失败","ALERT");
				Jpush.jPushMethod(order.getAccountToken(),jsonObject.toJSONString(),"");
				idInts.add(i);
			}
			int a=seatOrderService.updataSeatInfoStatus(idInts);
			if(a>0) {
				return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "订单已撤销");
			}else {
				return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "订单撤销失败");
			}
		} catch (Exception e) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "订单撤销失败");
		}
	}

	/* ====================JSP页面接口结束========================== */
}
