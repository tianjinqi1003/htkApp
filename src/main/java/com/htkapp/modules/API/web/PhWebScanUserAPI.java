package com.htkapp.modules.API.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.service.PhWebScanUserService;

@Controller
@RequestMapping("/API/phWebScanUserAPI")
public class PhWebScanUserAPI {

	@Resource
	private PhWebScanUserService phWebScanUserService;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public APIResponseModel login(String shopId, String seatName) {
		
		String zhuoNo=shopId+"_"+seatName;
		return phWebScanUserService.login(zhuoNo);
	}
}
