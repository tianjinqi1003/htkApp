package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.IntegralLog;

public interface IntegralLogMapper {

    /* =====================接口开始========================= */
    //插入积分操作日志
    int insertIntegralLogDAO(IntegralLog log) throws Exception;

    /* =====================接口结束======================== */
}
