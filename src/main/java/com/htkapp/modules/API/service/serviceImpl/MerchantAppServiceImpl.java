package com.htkapp.modules.API.service.serviceImpl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.htkapp.core.LogUtil;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.entity.ProductDetail;
import com.htkapp.modules.API.service.MerchantAppService;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.pay.entity.BillRecord;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.BillBalanceSheetService;
import com.htkapp.modules.merchant.pay.service.BillRecordService;
import com.htkapp.modules.merchant.pay.service.OrderProductService;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.shop.dao.AccountShopMapper;
import com.htkapp.modules.merchant.shop.dao.ShopMapper;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.dto.AddProductList;
import com.htkapp.modules.merchant.takeout.dto.ListProperty;
import com.htkapp.modules.merchant.takeout.dto.Property;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutCategoryServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.xiaoleilu.hutool.date.DateUtil;

@Service
public class MerchantAppServiceImpl implements MerchantAppService {

	@Resource
	private OrderRecordService orderRecordService;
	@Resource
	private OrderProductService orderProductService;
	@Resource
    private AccountShopMapper accountShopDao;
	@Resource
    private ShopMapper shopDao;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private TakeoutCategoryServiceI takeoutCategoryService;
    @Resource
    private TakeoutProductServiceI takeoutProductService;
	@Resource
	private BillRecordService billRecordService;
	@Resource
	private BillBalanceSheetService billBalanceSheetService;

	@Override
	public APIResponseModel getNewOrderList(Integer shopId, String startDate, String endDate, Integer statusCode) {
		// TODO Auto-generated method stub
		
		try {
			List<OrderRecord> resultList = orderRecordService.getTakeoutRealTimeOrderByCondition(shopId, startDate, endDate, statusCode);
			if (resultList != null) {
				for (OrderRecord each : resultList) {
					List<OrderProduct> productList = orderProductService.getProductListByOrderId(each.getId());
					each.setProductLists(productList);
				}
				return new APIResponseModel<List<OrderRecord>>(Globals.API_SUCCESS, "查询新订单成功", resultList);
			}
			else {
				return new APIResponseModel<List<OrderRecord>>(Globals.API_FAIL, "查询新订单失败", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new APIResponseModel<List<OrderRecord>>(Globals.API_FAIL, "查询新订单失败", null);
		}
	}

	@Override
	public APIResponseModel getFinishedOrderList(int shopId, String startDate, String endDate, Integer statusCode) {
		// TODO Auto-generated method stub

		try {
			List<OrderRecord> resultList = orderRecordService.getFinishedMerchantAppOrderList(shopId, startDate, endDate, statusCode);
			if (resultList != null) {
				for (OrderRecord each : resultList) {
					List<OrderProduct> productList = orderProductService.getProductListByOrderId(each.getId());
					each.setProductLists(productList);
				}
				return new APIResponseModel<List<OrderRecord>>(Globals.API_SUCCESS, "查询已完成订单成功", resultList);
			}
			else {
				return new APIResponseModel<List<OrderRecord>>(Globals.API_FAIL, "查询已完成订单失败", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new APIResponseModel<List<OrderRecord>>(Globals.API_FAIL, "查询已完成订单失败", null);
		}
	}

	@Override
	public AjaxResponseModel confirmFinishedOrder(String orderNumber) {
		// TODO Auto-generated method stub
		boolean result = orderRecordService.changeConfirmedByAppByOrderNumber(orderNumber);
        if (result) {
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "确认订单成功!");
        }
        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "确认订单失败!");
	}

	@Override
	public AjaxResponseModel findByUserNamePwd(String userName, String password) {
		// TODO Auto-generated method stub
		LoginUser loginUser = accountShopDao.getAccountShopByNameAndPwdDAO(userName, password);
		Date endTime = DateUtil.parse(loginUser.getUseEndTime());
        Date nowTime = new Date();
        if (nowTime.getTime() > endTime.getTime()) {
            return new AjaxResponseModel(Globals.API_FAIL, "帐号使用时间过期");
        }
        
        Shop shop = shopDao.getShopByAccountShopIdAndMarkDAO(loginUser.getUserId(), 0);
        loginUser.setState(shop.getState());
        loginUser.setShopName(shop.getShopName());
		return new AjaxResponseModel<LoginUser>(Globals.API_SUCCESS, "成功", loginUser);
	}

	@Override
	public APIResponseModel getProductDetailByPID(Integer userId, Integer productId) {
		// TODO Auto-generated method stub
		List<TakeoutCategory> resultList=null;
		TakeoutProduct takeoutProduct=null;
		try {
            resultList = takeoutCategoryService.getTakeoutCategoryListByAccountShopId(userId);
            //dataMap.put("data", resultList);
            takeoutProduct = takeoutProductService.getTakeoutProductById(userId, productId);
            if (takeoutProduct != null) {
                takeoutProduct.setImgUrl(OtherUtils.getRootDirectory() + takeoutProduct.getImgUrl());
            }
            //dataMap.put("dataPro", takeoutProduct);
        } catch (Exception e) {
            //model.addAttribute("dataPro", null);
            return new APIResponseModel(Globals.API_FAIL, "失败");
        }
		return new APIResponseModel<ProductDetail>(Globals.API_SUCCESS, "成功", new ProductDetail(resultList,takeoutProduct));
	}

	@Override
	public List<TakeoutCategory> getTakeoutCategoryListByAccountShopId(Integer userId) {
		// TODO Auto-generated method stub
		List<TakeoutCategory> tcList=null;
		try {
			tcList = takeoutCategoryService.getTakeoutCategoryListByAccountShopId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return tcList;
		}
	}

	@Override
	public void addTakeoutProduct(TakeoutProduct takeoutProduct, MultipartFile imgFile,
            String label, PropertyList propertyList, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		//商品名称
        //店内分类
        //图片
        //描述
        //标签
        //价格与库存
        //属性
        //售卖时间(全时段售卖，　自定义时间售卖)
        //按照规格来添加商品(几个规格添加几个商品)
        //处理产品图片
        if (imgFile != null) {
            String uploadUrl = FileUploadUtils.appUploadAvatarImg(imgFile, "shop/takeout/", FTPConfig.port_to);
            takeoutProduct.setImgUrl(uploadUrl);
        }
        String labels = String.valueOf(label);
        takeoutProduct.setLabel(labels);
        //产品属性集合
        Set<String> propertyLists = new HashSet<>();
        StringBuffer property = new StringBuffer();
        if (propertyList != null && propertyList.getPropertyList() != null) {
            for (Property every : propertyList.getPropertyList()) {
                propertyLists.add(every.getPropertyE());
            }
            for (String ever : propertyLists) {
                property.append(ever).append(",");
            }
        }
        Shop shop = shopService.getShopIdByAccountShopId(userId, takeoutProduct.getMark());
        takeoutProduct.setShopId(shop.getShopId());
        takeoutProduct.setProperty(String.valueOf(property));
        takeoutProductService.addTakeoutProduct(takeoutProduct);
		
	}

	@Override
	public APIResponseModel getBillRecord(String token) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = OtherUtils.getDateMapByNum(Integer.parseInt("2"));
		String startTime = map.get("startTime");
		String endTime = map.get("endTime");
		
		List<BillRecord> billRecordList = billRecordService.getBillRecordListByDateDesc(token, startTime, endTime);

		/**
		 * @author 马鹏昊
		 * @desc 去掉时间.0后缀（java接收DateTime类型字段值原因）
		 */
		for (BillRecord bill :billRecordList){
			String trueGmtCreate = bill.getGmtCreate();
			String trueGmtModified = bill.getGmtModified();
			bill.setGmtCreate(trueGmtCreate.substring(0,trueGmtCreate.length()-2));
			bill.setGmtModified(trueGmtModified.substring(0,trueGmtModified.length()-2));
		}

    	if (billRecordList == null) {
            return new APIResponseModel(Globals.API_FAIL, "失败");
        }
    	return new APIResponseModel<List<BillRecord>>(Globals.API_SUCCESS, "成功",billRecordList);
	}

	@Override
	public APIResponseModel getBalance(String token) {
		// TODO Auto-generated method stub
		
		Map<String, Object> balanceMap = new HashMap<String, Object>();
		//商家的提现收款账号
		String aliPayAccount = accountShopDao.selectByTokenDAO(token).getAlipayAccount();
		//账户可用余额
		double accountBalance = billBalanceSheetService.getAccountBalance(token);
		DecimalFormat df = new DecimalFormat("#.00");
		balanceMap.put("aliPayAccount", aliPayAccount);
		balanceMap.put("availableBalance", Double.parseDouble(df.format(accountBalance)));
		return new APIResponseModel<Map<String, Object>>(Globals.API_SUCCESS, "成功",balanceMap);
	}

	@Override
	public AccountShop getAlipayAccount(Integer id) {
		// TODO Auto-generated method stub
		try {
            return accountShopDao.getAlipayAccount(id);
        } catch (Exception e) {
           return  null;
        }
	}

	@Override
	public double getAccountBalance(String accountShopToken) {
		// TODO Auto-generated method stub
		return billBalanceSheetService.getAccountBalance(accountShopToken);
	}

	@Override
	public int updateAccountBalance(String accountShopToken, double newBalance) {
		// TODO Auto-generated method stub
		return billBalanceSheetService.updateAccountBalance(accountShopToken, newBalance);
	}

}
