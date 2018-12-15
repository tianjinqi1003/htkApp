package com.htkapp.modules.API.dao;

import java.util.List;

import com.htkapp.modules.API.entity.DaiQiangDan;
import com.htkapp.modules.API.entity.Rider;

public interface RiderMapper {

	Rider findByPhonePwd(String phone, String password);

	int register(Rider rider);

	int checkIfExist(String phone);

	List<DaiQiangDan> getDaiQiangDan();
}
