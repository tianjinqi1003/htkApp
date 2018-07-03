package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.IntegralConsumptionLimits;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */
public interface IntegralConsumptionLimitsService {

    /**
     * @param pageSize 查找数据列表
     * @param limit
     * @param id
     * @return
     */
    List<IntegralConsumptionLimits> getConsumptionLimitListById(int pageSize, int limit, Integer id);

    /**
     * @param integralConsumptionLimits 插入数据
     * @return
     */
    int insertConsumptionLimit(IntegralConsumptionLimits integralConsumptionLimits);

    /**
     * @param integralConsumptionLimits 更新数据
     * @return
     */
    int updateConsumptionLimitById(IntegralConsumptionLimits integralConsumptionLimits);

    /**
     * @param id 删除数据
     * @return
     */
    int deleteConsumptionLimitById(Integer id);

    /**
     * @param id 通过ID查找限度信息
     * @return
     */
    IntegralConsumptionLimits getConsumptionLimitById(Integer id);
}
