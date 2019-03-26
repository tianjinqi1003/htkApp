package com.htkapp.modules.API.web;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.MD5Utils;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.API.service.MerchantAppService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutService;
import com.xiaoleilu.hutool.date.DateUtil;

@Controller
@RequestMapping("/API/merchantAppAPI")
public class MerchantAppAPI {

	@Resource
	private MerchantAppService merchantAppService;
	@Resource
    private ShopServiceI shopService;
    @Resource
    private TakeoutService takeoutService;
    
    @RequestMapping(value = "/login")
    @ResponseBody
    public AjaxResponseModel merchantLogin(APIRequestParams params) {
    	
    	return merchantAppService.findByUserNamePwd(params.getUserName(), MD5Utils.MD5Encode(params.getPassword(), "UTF-8", true));
    }
	
	@RequestMapping(value="/getNewOrderList")
	@ResponseBody
	public APIResponseModel getNewOrderList(APIRequestParams params) throws Exception {
		
		//String startDate = DateUtil.beginOfDay(new Date()).toString();
		String startDate = "2019-03-01";
        String endDate = DateUtil.endOfDay(new Date()).toString();
		//此处是外卖，所以mark是0
		Shop shop = shopService.getShopByAccountShopIdAndMark(params.getUserId(), 0);
        return merchantAppService.getNewOrderList(shop.getShopId(), startDate, endDate, params.getStatusCode());
	}
	
	@RequestMapping(value="/getFinishedOrderList")
	@ResponseBody
	public APIResponseModel getFinishedOrderList(APIRequestParams params) throws Exception {
		
		//String startDate = DateUtil.beginOfDay(new Date()).toString();
		String startDate = "2019-03-01";
		String endDate = DateUtil.endOfDay(new Date()).toString();
		//此处是外卖，所以mark是0
		Shop shop = shopService.getShopByAccountShopIdAndMark(params.getUserId(), 0);
		return merchantAppService.getFinishedOrderList(shop.getShopId(), startDate, endDate, params.getStatusCode());
	}
	
	@RequestMapping(value="/confirmTheOrder")
	@ResponseBody
	public AjaxResponseModel confirmTheOrder(APIRequestParams params) {
		return takeoutService.confirmTheOrderSuc(params.getOrderNumber());
	}
	
	@RequestMapping(value="/confirmFinishedOrder")
	@ResponseBody
	public AjaxResponseModel confirmFinishedOrder(APIRequestParams params) {
		return merchantAppService.confirmFinishedOrder(params.getOrderNumber());
	}
}