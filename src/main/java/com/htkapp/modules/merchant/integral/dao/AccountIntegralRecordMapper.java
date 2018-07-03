package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.AccountIntegralRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountIntegralRecordMapper {

    //根据用户token 和 店铺id 获取积分记录
    List<AccountIntegralRecord> getIntegralRecordByTokenAndShopIdDAO(@Param("token") String token, @Param("shopId") int shopId, @Param("orderBy") String orderBy);
    //插入积分记录
    int insertIntegralRecordDAO(AccountIntegralRecord integralRecord);
}
