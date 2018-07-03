package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.AccountTradingRecord;

import java.util.List;

public interface AccountTradingRecordService {

    //获取用户消费记录
    List<AccountTradingRecord> getTradingRecordByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit);
}
