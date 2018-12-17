package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.entity.Rider;

public interface RiderService {

	APIResponseModel findByPhonePwd(String phone, String password);

	APIResponseModel register(Rider rider);

	APIResponseModel getDaiQiangDan();

	APIResponseModel getDaiQiangDanDetail(String orderNumber);
}
