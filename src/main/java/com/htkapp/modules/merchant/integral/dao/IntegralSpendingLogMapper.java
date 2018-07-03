package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.IntegralSpendingLog;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */
public interface IntegralSpendingLogMapper {

    /**
     * @param integralSpendingLog 插入积分消费记录
     * @return
     */
    int insertById(IntegralSpendingLog integralSpendingLog);

    /**
     * @param integralSpendingLog 通过ID查找用户的消费记录
     * @return
     */
    List<IntegralSpendingLog> findSpendingLogById(IntegralSpendingLog integralSpendingLog);

    /**
     * @param integralSpendingLog 通过ID查找用户积分消费总数
     * @return
     */
    int findSpendingIntegralValue(IntegralSpendingLog integralSpendingLog);
}
