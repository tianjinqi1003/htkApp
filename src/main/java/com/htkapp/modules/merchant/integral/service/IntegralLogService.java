package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.IntegralLog;

public interface IntegralLogService {

    /* ========================接口开始============================ */
    //插入积分操作日志
    void insertIntegralLog(IntegralLog log) throws Exception;


    /* ========================接口结束============================ */
}
