package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.AccountTradingRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountTradingRecordMapper {

    //获取用户消费记录
    List<AccountTradingRecord> getTradingRecordByTokenAndShopIdDAO(@Param("token") String token, @Param("shopId") int shopId, @Param("orderBy") String orderBy);
}
