package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.AccountIntegralRecord;

import java.util.List;

public interface AccountIntegralRecordService {

    //根据用户token 和 店铺id 获取积分记录
    List<AccountIntegralRecord> getIntegralRecordByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit);
    //插入积分记录
    void insertIntegralRecord(AccountIntegralRecord integralRecord);
}
