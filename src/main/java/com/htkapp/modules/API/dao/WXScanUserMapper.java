package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.WXScanUser;

public interface WXScanUserMapper {

	int checkIfExist(String openID);

	int register(WXScanUser wxScanUser);

	WXScanUser findByOpenID(String openID);

}
