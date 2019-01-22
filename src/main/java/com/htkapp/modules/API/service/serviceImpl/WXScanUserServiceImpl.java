package com.htkapp.modules.API.service.serviceImpl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.WXScanUserMapper;
import com.htkapp.modules.API.entity.WXScanUser;
import com.htkapp.modules.API.service.WXScanUserService;

@Service
public class WXScanUserServiceImpl implements WXScanUserService {
	
	@Resource
	private WXScanUserMapper wxScanUserMapper;

	@Override
	public APIResponseModel login(String openID) {
		// TODO Auto-generated method stub
		
		int count = 0;
		count = wxScanUserMapper.checkIfExist(openID);
		if(count==0) {
			WXScanUser wxScanUser=new WXScanUser();
			wxScanUser.setOpenID(openID);
			wxScanUser.setToken(UUID.randomUUID().toString());
			count = wxScanUserMapper.register(wxScanUser);
			if(count>0) {
				return new APIResponseModel<String>(Globals.API_SUCCESS, "用户登录成功",wxScanUser.getToken());
			}
			else {
				return new APIResponseModel<String>(Globals.API_FAIL, "用户登录失败");
			}
		}
		else {
			WXScanUser wxScanUser=wxScanUserMapper.findByOpenID(openID);
			return new APIResponseModel<String>(Globals.API_SUCCESS, "用户登录成功",wxScanUser.getToken());
		}
	}

}
