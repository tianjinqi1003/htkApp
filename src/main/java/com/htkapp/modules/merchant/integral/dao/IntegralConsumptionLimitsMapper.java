package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.IntegralConsumptionLimits;

import java.util.List;

/**
 *
 */
public interface IntegralConsumptionLimitsMapper {

    /**
     * @param id 通过商铺ID查找积分消费限度列表
     * @return
     */
    List<IntegralConsumptionLimits> findConsumptionLimitListById(Integer id);

    /**
     * @param integralConsumptionLimits 插入数据
     * @return
     */
    int insertConsumptionLimit(IntegralConsumptionLimits integralConsumptionLimits);

    /**
     * @param integralConsumptionLimits 通过ID更改数据
     * @return
     */
    int updateConsumptionLimitById(IntegralConsumptionLimits integralConsumptionLimits);

    /**
     * @param id 通过ID删除数据
     * @return
     */
    int deleteConsumptionLimitById(Integer id);

    /**
     * @param id  通过ID查找限度信息
     * @return
     */
    IntegralConsumptionLimits findConsumptionLimitById(Integer id);
}
