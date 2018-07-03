package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.IntegralSpendingLog;

import java.util.List;

/**
 *
 */
public interface IntegralSpendingLogService {

    /**
     * @param integralSpendingLog 插入积分消费记录
     * @return
     */
    int insertById(IntegralSpendingLog integralSpendingLog);

    /**
     * @param integralSpendingLog
     * @return
     */
    List<IntegralSpendingLog> getSpendingLogById(int pageSize, int limit, IntegralSpendingLog integralSpendingLog);

    /**
     * @param integralSpendingLog
     * @return
     */
    int getSpendingIntegralValue(IntegralSpendingLog integralSpendingLog);
}
