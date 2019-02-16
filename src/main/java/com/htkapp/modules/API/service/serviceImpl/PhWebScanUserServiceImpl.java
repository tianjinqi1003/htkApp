package com.htkapp.modules.API.service.serviceImpl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.PhWebScanUserMapper;
import com.htkapp.modules.API.entity.PhWebScanUser;
import com.htkapp.modules.API.service.PhWebScanUserService;

@Service
public class PhWebScanUserServiceImpl implements PhWebScanUserService {

	private PhWebScanUserMapper phWebScanUserMapper;
	
	@Override
	public APIResponseModel login(String zhuoNo) {
		// TODO Auto-generated method stub

		int count = 0;
		count = phWebScanUserMapper.checkIfExist(zhuoNo);
		if(count==0) {
			PhWebScanUser phWebScanUser=new PhWebScanUser();
			phWebScanUser.setZhuoNo(zhuoNo);
			phWebScanUser.setToken(UUID.randomUUID().toString());
			count = phWebScanUserMapper.register(phWebScanUser);
			if(count>0) {
				return new APIResponseModel<String>(Globals.API_SUCCESS, "用户登录成功",phWebScanUser.getToken());
			}
			else {
				return new APIResponseModel<String>(Globals.API_FAIL, "用户登录失败");
			}
		}
		else {
			PhWebScanUser phWebScanUser=phWebScanUserMapper.findByZhuoNo(zhuoNo);
			return new APIResponseModel<String>(Globals.API_SUCCESS, "用户登录成功",phWebScanUser.getToken());
		}
	}

}
