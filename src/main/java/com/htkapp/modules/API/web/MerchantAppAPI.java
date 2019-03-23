package com.htkapp.modules.API.web;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.service.MerchantAppService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.xiaoleilu.hutool.date.DateUtil;

@Controller
@RequestMapping("/API/merchantAppAPI")
public class MerchantAppAPI {

	@Resource
	private MerchantAppService merchantService;
	@Resource
    private ShopServiceI shopService;
	
	@RequestMapping(value="/getNewOrderList")
	@ResponseBody
	public APIResponseModel getNewOrderList(APIRequestParams params) throws Exception {
		
		//String startDate = DateUtil.beginOfDay(new Date()).toString();
		String startDate = "2019-03-01";
        String endDate = DateUtil.endOfDay(new Date()).toString();
		//此处是外卖，所以mark是0
		Shop shop = shopService.getShopByAccountShopIdAndMark(params.getUserId(), 0);
        return merchantService.getNewOrderList(shop.getShopId(), startDate, endDate, params.getStatusCode());
	}
}
