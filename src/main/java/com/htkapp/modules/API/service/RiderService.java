package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;

public interface RiderService {

	APIResponseModel findByPhonePwd(String phone, String password);
}
