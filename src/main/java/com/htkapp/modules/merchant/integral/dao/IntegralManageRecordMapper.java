package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.IntegralManageRecord;

/**
 * Created by yinqilei on 17-5-18.
 */
public interface IntegralManageRecordMapper {


    /* ======================接口开始=========================== */
    //插入交易记录
    int insertRecordByTokenDAO(IntegralManageRecord record);
    /* =======================接口结束============================ */
}
