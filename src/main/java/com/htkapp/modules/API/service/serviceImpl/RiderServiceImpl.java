package com.htkapp.modules.API.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.RiderMapper;
import com.htkapp.modules.API.entity.DaiQiangDan;
import com.htkapp.modules.API.entity.DaiQuHuo;
import com.htkapp.modules.API.entity.DaiSongDa;
import com.htkapp.modules.API.entity.Rider;
import com.htkapp.modules.API.service.RiderService;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;

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

	@Override
	public APIResponseModel getDaiQiangDan() {
		// TODO Auto-generated method stub
		
		List<DaiQiangDan> list = riderMapper.getDaiQiangDan();
		return new APIResponseModel<List<DaiQiangDan>>(Globals.API_SUCCESS, "查询待抢单成功", list);
	}

	@Override
	public APIResponseModel getDaiQiangDanDetail(String orderNumber) {
		// TODO Auto-generated method stub
		
		List<OrderProduct> list = riderMapper.getDaiQiangDanDetail(orderNumber);
		return new APIResponseModel<List<OrderProduct>>(Globals.API_SUCCESS, "查询待抢单明细成功", list);
	}

	@Override
	public APIResponseModel getDaiQuHuo(Integer riderId) {
		// TODO Auto-generated method stub
		
		List<DaiQuHuo> list = riderMapper.getDaiQuHuo(riderId);
		return new APIResponseModel<List<DaiQuHuo>>(Globals.API_SUCCESS, "查询待取货成功", list);
	}

	@Override
	public APIResponseModel getDaiSongDa() {
		// TODO Auto-generated method stub

		List<DaiSongDa> list = riderMapper.getDaiSongDa();
		return new APIResponseModel<List<DaiSongDa>>(Globals.API_SUCCESS, "查询待送达成功", list);
	}

	@Override
	public APIResponseModel confirmQiangDan(String orderNumber, Integer riderId) {
		// TODO Auto-generated method stub
		int count = 0;
		count = riderMapper.confirmQiangDan(orderNumber,riderId);
		if(count==0)
			return new APIResponseModel<String>(Globals.API_FAIL, "抢单失败");
		else
			return new APIResponseModel<String>(Globals.API_SUCCESS, "抢单成功");
	}
}
