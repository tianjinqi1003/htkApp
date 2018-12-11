package com.htkapp.modules.API.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.entity.Rider;
import com.htkapp.modules.API.service.RiderService;

@Controller
@RequestMapping("/API/riderAPI")
public class RiderAPI {

	@Resource
	private RiderService riderService;
	
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
}
