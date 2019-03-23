package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;

public interface MerchantAppService {

	APIResponseModel getNewOrderList(Integer shopId, String startDate, String endDate, Integer statusCode);

}
