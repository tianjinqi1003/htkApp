package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.PhWebScanUser;

public interface PhWebScanUserMapper {

	int checkIfExist(String zhuoNo);

	int register(PhWebScanUser phWebScanUser);

	PhWebScanUser findByZhuoNo(String zhuoNo);

}
