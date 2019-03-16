package com.htkapp.modules.API.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.entity.Rider;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.API.service.RiderService;
import com.htkapp.modules.merchant.takeout.service.TakeoutService;

@Controller
@RequestMapping("/API/riderAPI")
public class RiderAPI {

	@Resource
	private RiderService riderService;
    @Resource
    private TakeoutService takeoutService;
    @Resource
    private AccountServiceI accountService;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public APIResponseModel login(APIRequestParams params) {
		
		return riderService.findByPhonePwd(params.getPhone(), params.getPassword());
	}
	
	@RequestMapping(value="/register")
	@ResponseBody
	public APIResponseModel register(APIRequestParams params) {
		
		Rider rider = new Rider();
		rider.setPhone(params.getPhone());
		rider.setTrueName(params.getTrueName());
		rider.setCardID(params.getCardID());
		rider.setPassword(params.getPassword());
		
		return riderService.register(rider);
	}
	
	@RequestMapping(value="/getDaiQiangDan")
	@ResponseBody
	public APIResponseModel getDaiQiangDan(APIRequestParams params) {
		
		return riderService.getDaiQiangDan();
	}
	
	@RequestMapping(value="/getDaiQiangDanDetail")
	@ResponseBody
	public APIResponseModel getDaiQiangDanDetail(APIRequestParams params) {
		
		return riderService.getDaiQiangDanDetail(params.getOrderNumber());
	}

	@RequestMapping(value="/getDaiQuHuo")
	@ResponseBody
	public APIResponseModel getDaiQuHuo(APIRequestParams params) {
		
		return riderService.getDaiQuHuo(params.getRiderId());
	}
	
	@RequestMapping(value="/getDaiSongDa")
	@ResponseBody
	public APIResponseModel getDaiSongDa(APIRequestParams params) {
		
		return riderService.getDaiSongDa();
	}
	
	@RequestMapping(value="/confirmQiangDan")
	@ResponseBody
	public APIResponseModel confirmQiangDan(APIRequestParams params) {
		
		return riderService.confirmQiangDan(params.getOrderNumber(),params.getRiderId());
	}
	
	//配送商品接口
    @RequestMapping("/order/itemsToShip")
    @ResponseBody
    public AjaxResponseModel itemsToShip(APIRequestParams params){
        return takeoutService.itemsToShip(params.getOrderNumber());
    }
    
    //确认收货
    @RequestMapping(value = "/enterReceipt", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel enterReceipt(APIRequestParams params) {
    	APIResponseModel apiResponseModel = accountService.enterReceipt(null,params.getOrderNumber(), params.getAccountToken());
    	return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, apiResponseModel.getMessage());
	}
}
