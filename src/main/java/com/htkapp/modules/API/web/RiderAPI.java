package com.htkapp.modules.API.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.RiderService;

@Controller
@RequestMapping("/API/riderAPI")
public class RiderAPI {

	@Resource
	private RiderService riderService;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public APIResponseModel login(APIRequestParams params) {
		
		System.out.println("phone======="+params.getPhone());
		System.out.println("password======="+params.getPassword());
		return riderService.findByPhonePwd(params.getPhone(), params.getPassword());
	}
}
