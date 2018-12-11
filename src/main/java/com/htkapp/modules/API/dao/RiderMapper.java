package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.Rider;

public interface RiderMapper {

	Rider findByPhonePwd(String phone, String password);
}
