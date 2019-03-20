package com.htkapp.modules.API.service;

import java.util.List;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.entity.Rider;

public interface RiderService {

	APIResponseModel findByPhonePwd(String phone, String password);

	APIResponseModel register(Rider rider);

	APIResponseModel getDaiQiangDan();

	APIResponseModel getDaiQiangDanDetail(String orderNumber);

	APIResponseModel getDaiQuHuo(Integer riderId);

	APIResponseModel getDaiSongDa();

	APIResponseModel confirmQiangDan(String orderNumber, Integer riderId);

	List<Rider> getRiderList(int pageNumber, int pageLimit);
}
