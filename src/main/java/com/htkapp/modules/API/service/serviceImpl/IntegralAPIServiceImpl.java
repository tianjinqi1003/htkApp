package com.htkapp.modules.API.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.GetIdUtil;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.dto.IntegralAccountMes;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.entity.AppCheckVersion;
import com.htkapp.modules.API.service.AccountFocusService;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.API.service.AppCheckVersionService;
import com.htkapp.modules.API.service.IntegralAPIService;
import com.htkapp.modules.merchant.common.entity.SinglePage;
import com.htkapp.modules.merchant.common.service.SinglePageService;
import com.htkapp.modules.merchant.integral.entity.*;
import com.htkapp.modules.merchant.integral.service.*;
import com.htkapp.modules.merchant.shop.dto.AppShowShopInfo;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.entity.ShopBulletin;
import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;
import com.htkapp.modules.merchant.shop.entity.ShopSuggest;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopBulletinService;
import com.htkapp.modules.merchant.shop.service.ShopSaverTicketRecordService;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopSuggestService;
import com.sun.org.apache.regexp.internal.RE;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

/**
 * Created by yinqilei on 17-7-17.
 * 会员尊享平台api逻辑Service实现类
 */

@Service
public class IntegralAPIServiceImpl implements IntegralAPIService {

    @Resource
    private ShopArticleInfoService shopArticleInfoService;
    @Resource
    private IntegralService integralService;
    @Resource
    private AccountServiceI accountService;
    @Resource
    private AccountTicketListService ticketListService;
    @Resource
    private AccountSaverTicketService saverTicketService;
    @Resource
    private ShopBulletinService shopBulletinService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private SeatOrderService seatOrderService;
    @Resource
    private AccountIntegralRecordService integralRecordService;
    @Resource
    private AppCheckVersionService appCheckVersionService;
    @Resource
    private AccountFocusService focusService;
    @Resource
    private ShopSaverTicketRecordService shopSaverTicketRecordService;
    @Resource
    private AccountTradingRecordService tradingRecordService;
    @Resource
    private ShopSuggestService shopSuggestService;
    @Resource
	private MoreMethodsUtils methodsUtils;
	@Resource
	private AccountShopServiceI accountShopService;

    /* =============接口开始================= */

    //首页活动列表接口
    @Override
    public APIResponseModel getMemberHomeListData(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            int pageNumber = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (params.getPageNumber() > 1) {
                pageNumber = params.getPageNumber();
            }
            try {
                List<ShopArticleInfo> result = shopArticleInfoService.getShopArticleInfoByShopId(params.getShopId(), pageNumber, pageLimit);
                if (result != null) {
                    for (ShopArticleInfo each : result) {
                        each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                        each.setCreateTime(format(DateUtil.parse(each.getCreateTime()), NORM_DATETIME_PATTERN));
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", result);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //会员平台－我的信息
    @Override
    public APIResponseModel getMemberAccountMes(APIRequestParams params) {
        if (params != null && params.getToken() != null && params.getShopId() != null) {
            //查询用户信息{头像、名字、手机号、优惠券数量、积分数量}
            try {
                Integral integral = integralService.getUserIntegralByAccountToken(params.getToken(), params.getShopId());
                Account account = accountService.selectByToken(params.getToken());
                List<AccountTicketList> resultList = ticketListService.getTicketListByTokenAndShopId(params.getToken(), params.getShopId());
                IntegralAccountMes accountMes = new IntegralAccountMes();
                accountMes.setAvatarUrl(OtherUtils.getRootDirectory() + account.getAvatarUrl());
                accountMes.setNickName(account.getNickName());
                accountMes.setPhone(account.getPhone());

                //                accountMes.setTicketCount(resultList == null ? 0 : resultList.size());
                /**
                 * @author 马鹏昊
                 * @desc 计算所有优惠券的数量
                 */
                int ticketCount = 0 ;
                if (resultList!=null) {
                    for (AccountTicketList singleTicket : resultList) {
                        ticketCount += singleTicket.getQuantity();
                    }
                }
                accountMes.setTicketCount(ticketCount);


                accountMes.setIntegralCount(integral == null ? 0 : integral.getVal());
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", accountMes);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //我的－消费券列表(需要token)
    @Override
    public APIResponseModel getAccountCouponList(APIRequestParams params) {
        if (params != null && params.getToken() != null && params.getShopId() != null) {
            try {
                List<AccountTicketList> resultList = ticketListService.getTicketListByTokenAndShopId(params.getToken(), params.getShopId());
                List<AccountSaverTicket> saverTicketList = null;
                if (resultList != null) {
                    saverTicketList = new ArrayList<>();
                    for (AccountTicketList each : resultList) {
                        AccountSaverTicket saverTicket = saverTicketService.getTicketMesByIdAndShopId(each.getTicketId(), params.getShopId());
                        if (saverTicket != null) {
                            saverTicket.settUsePhone(each.getUsePhone());
                            /**
                             * @author 马鹏昊
                             * @desc 保存此优惠券的数量
                             */
                            saverTicket.setQuantity(each.getQuantity());
                        }
                        saverTicketList.add(saverTicket);
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", saverTicketList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //我的－交易记录
    @Override
    public APIResponseModel getAccountTradingRecord(APIRequestParams params) {
        if (params != null && params.getToken() != null && params.getShopId() != null) {
            try {
                int pageNumber = Globals.DEFAULT_PAGE_NO;
                int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                if (params.getPageNumber() > 1) {
                    pageNumber = params.getPageNumber();
                }
                //根据用户token 和 shopId 查找交易记录
                List<AccountTradingRecord> tradingRecordList = tradingRecordService.getTradingRecordByTokenAndShopId(params.getToken(), params.getShopId(), pageNumber, pageLimit);
                if(tradingRecordList != null){
                    for (AccountTradingRecord each : tradingRecordList){
                        each.setCreateTime(format(DateUtil.parse(each.getCreateTime()), NORM_DATETIME_PATTERN));
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", tradingRecordList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //我的－我的预约
    @Override
    public APIResponseModel getAccountReserve(APIRequestParams params) {
        if (params != null && params.getToken() != null && params.getShopId() != null) {
            int pageNumber = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (params.getPageNumber() > 1) {
                pageNumber = params.getPageNumber();
            }
            try {
                List<SeatOrder> resultList = seatOrderService.getSeatOrderListByTokenAndShopId(params.getToken(), params.getShopId(), pageNumber, pageLimit);
                if (resultList != null) {
                    for (SeatOrder each : resultList) {
                        each.setOrderTime(format(DateUtil.parse(each.getOrderTime()), NORM_DATETIME_PATTERN));
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //我的－积分兑换
    @Override
    public APIResponseModel getIntegralBuyData(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            try {
                int pageNumber = Globals.DEFAULT_PAGE_NO;
                int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                if (params.getPageNumber() > 1) {
                    pageNumber = params.getPageNumber();
                }
                //筛选过期优惠券(不查找出来)
                List<AccountSaverTicket> resultList = saverTicketService.getSaverTicketListByShopId(params.getShopId(), pageNumber, pageLimit);
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //加入会员页面二维码
    @Override
    public APIResponseModel getQrImgData(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            try {
                Shop shop = shopService.getShopDataById(params.getShopId());
                AppCheckVersion version = appCheckVersionService.getTheLatestVersionNumber("20170704");
                //获取最新下载地址,生成二维码
                if (shop != null && version != null) {
                    //判断二维码是否存在,存在则直接取值，不存在则生成并保存地址
                    JSONObject object = new JSONObject();
                    if (StringUtils.isEmpty(shop.getShopQrCodeUrl())) {
                        //调用方法生成二维码并返回地址,保存地址到数据库
                        String qrImgURL = OtherUtils.getImgUrl(String.valueOf(params.getShopId()), "/shop/QRCode/", 0);
                        object.put("qrImgUrl", OtherUtils.getRootDirectory() + qrImgURL);
                        //保存到数据库
                        shopService.updateShopQRCode(qrImgURL,params.getShopId());
                    }else {
                        object.put("qrImgUrl", OtherUtils.getRootDirectory() + shop.getShopQrCodeUrl());
                    }
                    String downloadUrl = OtherUtils.getDataQrImgUrl(OtherUtils.getRootDirectory() + version.getDownloadUrl(), "/app/apk/account/downloadQrImg/");
                    int isCollect = focusService.checkCollectionByAccountIdAndShopId(params.getToken(), params.getShopId());
                    object.put("downloadQrImgUrl", OtherUtils.getRootDirectory() + downloadUrl);
                    object.put("isCollect", isCollect);  //是否加入会员：0没有加入，1已加入会员
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", object);
                }
                return new APIResponseModel(Globals.API_FAIL);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //加入按钮操作请求
    @Override
    public APIResponseModel addMember(APIRequestParams params) {
        if (params != null && params.getShopId() != null && params.getToken() != null) {
            try {
                //没有关注,则加入关注，并返回成功
                int row = focusService.checkStateAndJoinMemberByToken(params.getToken(), params.getShopId());
                switch (row) {
                    case -3:
                        //加入收藏，开启之前的积分记录
                        focusService.joinCollectionByAccountIdAndShopId(params.getToken(), params.getShopId());
                        return new APIResponseModel(Globals.API_SUCCESS);
                    case -4:
                        return new APIResponseModel(Globals.API_FAIL, "执行异常");
                    default:
                        break;
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "加入成功");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //预定座位请求
    @Override
    public APIResponseModel addReserveRequest(APIRequestParams params, SeatOrder order) {
        if (params != null && order != null) {
            //预定时间、预定人数、预定人姓名、预定手机
            try {
            	order.setShopId(params.getShopId());
            	order.setOrderNumber(String.valueOf(OrderNumGen.next()));
            	 seatOrderService.insertSeatOrderByToken(order);
            	SeatOrder seatOrder=seatOrderService.getSeatOrderByOrderNumber(order.getOrderNumber());
            	Shop shop=shopService.getShopDataById(seatOrder.getShopId());
            	 AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                methodsUtils.pushMesToManagePage(new PushMesEntity("订座订单信息", "b", "新的订座订单", user.getToken(), 'b',2, "您有一个订座订单信息", user.getId()));
                return new APIResponseModel(Globals.API_SUCCESS);
            } catch (Exception e) {
            	e.printStackTrace();
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //平台－关于
    /*
    店铺名，店铺评分，月售单数，公告，配送信息，商家电话，商家地址，营业时间, 店铺分类, 经纬度
    * */
    @Override
    public APIResponseModel getShopIntroduce(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            try {
                Shop shop = shopService.getShopDataById(params.getShopId());
                if (shop != null) {
                    JSONObject jsonObj = new JSONObject();
                    ShopBulletin shopBulletin = shopBulletinService.getShopBulletinById(params.getShopId());
                    jsonObj.put("shopBulletin", shopBulletin);
                    jsonObj.put("shopName", shop.getShopName());
                    jsonObj.put("score", shop.getScore());
                    jsonObj.put("monthlySalesVolume", shop.getMonthlySalesVolume());
                    jsonObj.put("deliveryFee", shop.getDeliveryFee());
                    jsonObj.put("phone", shop.getPhone());
                    jsonObj.put("locationAddress", shop.getLocation() + shop.getAddress());
                    jsonObj.put("openingTime", shop.getOpeningTime());
                    jsonObj.put("latitude", shop.getLatitude());
                    jsonObj.put("longitude", shop.getLongitude());
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", jsonObj);
                }
                return new APIResponseModel(Globals.API_FAIL, "店铺不存在");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //我的－积分记录
    @Override
    public APIResponseModel getAccountIntegralRecord(APIRequestParams params) {
        if (params != null && params.getToken() != null && params.getShopId() != null) {
            try {
                int pageNumber = Globals.DEFAULT_PAGE_NO;
                int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                if (params.getPageNumber() > 1) {
                    pageNumber = params.getPageNumber();
                }
                List<AccountIntegralRecord> resultList = integralRecordService.getIntegralRecordByTokenAndShopId(params.getToken(), params.getShopId(), pageNumber, pageLimit);
                if (resultList != null) {
                    for (AccountIntegralRecord each : resultList) {
                        each.setRecordTime(format(DateUtil.parse(each.getRecordTime()), NORM_DATETIME_PATTERN));
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //我的－积分兑换操作
    @Override
    @Transactional
    public APIResponseModel redeemOperation(APIRequestParams params) {
        if (params != null && params.getShopId() != null && params.getTicketId() != null && params.getToken() != null) {
            //店铺id, 优惠券id, 用户token
            try {
            	Shop takeOutShop=shopService.getShopDataByAccountShopId(params.getShopId());
                AccountSaverTicket saverTicket = saverTicketService.getTicketMesByIdAndShopId(params.getTicketId(), params.getShopId());
                Account account = accountService.selectByToken(params.getToken());
                //1.根据店铺id和优惠id 查询优惠券  验证优惠券是否存，是否是当前店铺下的优惠券
                if (saverTicket != null) {
                    //2.根据用户token查询积分是否可以兑换优惠券   是否有能够兑换
                    int result = integralService.determineTheIntegralByValue(saverTicket.getIntegralValue(), params.getToken(), params.getShopId());
                    if (result > 0) {
                        //3.减积分，插入用户优惠券列表数据,增加积分消费记录, 商家优惠券兑换记录
                        //执行兑换操作
                        //1.增加用户兑换优惠券记录
                        AccountTicketList ticketList = new AccountTicketList();
                        ticketList.setAccountToken(params.getToken());
                        ticketList.setQuantity(1);  //当前未传数量,默认为1
                        ticketList.setShopId(params.getShopId());
                        ticketList.setUsePhone(account.getPhone());
                        ticketList.setTicketId(params.getTicketId());
                        AccountTicketList hasTicketList=ticketListService.getTicketByTokenAndShopIdAndTicketId(params.getTicketId(), params.getToken(), takeOutShop.getShopId());
                        if(hasTicketList!=null) {
                        	hasTicketList.setQuantity(hasTicketList.getQuantity()+1);
                        	int a =ticketListService.updataTicketByTokenAndShopIdAndTicketId(params.getTicketId(), params.getToken(), takeOutShop.getShopId(), hasTicketList.getQuantity());
                        	if(a<=0) {
                        		return new APIResponseModel(Globals.API_FAIL);
                        	}
                        }else {
                        	  ticketListService.insertAccountTicket(ticketList);
                        }
                        //2.减用户积分   0减  1加
                        integralService.presentOrDeductionIntegralByToken(params.getToken(), params.getShopId(), saverTicket.getIntegralValue(), 0);
                        //3.增加积分消费记录
                        AccountIntegralRecord integralRecord = new AccountIntegralRecord();
                        integralRecord.setTitle("优惠券兑换");
                        integralRecord.setAccountToken(params.getToken());
                        integralRecord.setIntegralValue(saverTicket.getIntegralValue());
                        integralRecord.setRecordType(0);
                        integralRecord.setShopId(params.getShopId());
                        integralRecordService.insertIntegralRecord(integralRecord);
                        //4.插入商家优惠券兑换记录
                        //TODO
                        //为商家插入优惠券记录增加唯一性保障
                        ShopSaverTicketRecord ticketRecord = new ShopSaverTicketRecord();
                        ticketRecord.setAccountToken(params.getToken());
                        ticketRecord.setQuantity(1); //默认为1
                        ticketRecord.setShopId(params.getShopId());
                        ticketRecord.setTicketId(params.getTicketId());
                        ShopSaverTicketRecord str = shopSaverTicketRecordService.getTicketByTokenAndShopIdAndTicketId(params.getTicketId(), params.getToken(), takeOutShop.getShopId());
                        if(str!=null) {
                        	str.setQuantity(str.getQuantity()+1);
                        	int a =shopSaverTicketRecordService.updateAccountExchangeRecordDao(params.getTicketId(), params.getToken(), takeOutShop.getShopId(), str.getQuantity());
                        	if(a<=0) {
                        		return new APIResponseModel(Globals.API_FAIL);
                        	}
                        }else {
                        	shopSaverTicketRecordService.insertAccountExchangeRecord(ticketRecord);
                        }
                        /**
                         * @author 马鹏昊
                         * @desc 修改最近消费时间（gmt_modified字段）
                         */
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        Date d = new Date();
                        String dateStr = df.format(d);
                        integralService.updateLatestConsumeTime(params.getToken(), params.getShopId(),dateStr);
                        return new APIResponseModel(Globals.API_SUCCESS,"兑换成功");
                    }
                    return new APIResponseModel(Globals.API_FAIL, "积分值不足");
                }
                return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误，当前店铺下不存在当前要兑换的优惠券");
            } catch (Exception e) {
            	e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //平台－建议
    @Override
    public APIResponseModel accountSuggestRequest(APIRequestParams params, ShopSuggest suggest) {
        if(params != null && StringUtils.isNotEmpty(params.getContent()) && params.getShopId() != null){
            try{
                shopSuggestService.insertSuggestContentByShopId(suggest);
                return new APIResponseModel(Globals.API_SUCCESS);
            }catch (Exception e){
                return new APIResponseModel(Globals.API_FAIL);
            }
        }else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //资讯详情页
    @Override
    public void articleDetailPage(Integer aId, Model model) {
        if(aId != null && model != null){
            //根据aId 查询数据
            ShopArticleInfo shopArticleInfo = shopArticleInfoService.getShopArticleInfoById(aId);
            if(shopArticleInfo != null){
                shopArticleInfo.setImgUrl(OtherUtils.getRootDirectory() + shopArticleInfo.getImgUrl());
            }
            model.addAttribute("data",shopArticleInfo);
        }
        return;
    }

    /* =============接口结束================= */
    /*
    public static void main(String[] args) {
    	String downloadUrl1="htkApp/upload/app/apk/account/2.0/htkAccount.apk";
    	String downloadUrl = OtherUtils.getDataQrImgUrl(OtherUtils.getRootDirectory() + downloadUrl1, "/app/apk/account/downloadQrImg/");
    	System.out.println("downloadUrl============"+downloadUrl);
	}
	*/
}
