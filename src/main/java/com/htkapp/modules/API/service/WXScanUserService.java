package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;

public interface WXScanUserService {

	APIResponseModel login(String openID);

	APIResponseModel getShopShowInfoById(Integer shopId);

}
