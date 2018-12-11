package com.htkapp.modules.API.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.RiderMapper;
import com.htkapp.modules.API.entity.Rider;
import com.htkapp.modules.API.service.RiderService;

@Service
public class RiderServiceImpl implements RiderService {

	@Resource
	private RiderMapper riderMapper;
	
	public APIResponseModel findByPhonePwd(String phone, String password) {
		Rider rider = riderMapper.findByPhonePwd(phone, password);
		if(rider!=null) {
			if("0".equals(rider.getState()))
				return new APIResponseModel<String>(Globals.API_FAIL, "用户未审核");
			else
				return new APIResponseModel<String>(Globals.API_SUCCESS, "登录成功");
		}
		else
			return new APIResponseModel<String>(Globals.API_FAIL, "手机号或密码有误");
	}

	@Override
	public APIResponseModel register(Rider rider) {
		// TODO Auto-generated method stub
		int count = 0;
		count = riderMapper.checkIfExist(rider.getPhone());
		if(count==0)
			count = riderMapper.register(rider);
		else
			return new APIResponseModel<String>(Globals.API_FAIL, "该用户已被注册");
		
		if(count==0)
			return new APIResponseModel<String>(Globals.API_FAIL, "用户注册失败");
		else
			return new APIResponseModel<String>(Globals.API_SUCCESS, "用户注册成功");
	}
}
