package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.merchant.integral.dao.IntegralConsumptionLimitsMapper;
import com.htkapp.modules.merchant.integral.entity.IntegralConsumptionLimits;
import com.htkapp.modules.merchant.integral.service.IntegralConsumptionLimitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class IntegralConsumptionLimitsServiceImpl implements IntegralConsumptionLimitsService {

    @Autowired
    private IntegralConsumptionLimitsMapper integralConsumptionLimitsDao;

    /**
     * @param pageSize 查找数据列表
     * @param limit
     * @param id
     * @return
     */
    @Override
    public List<IntegralConsumptionLimits> getConsumptionLimitListById(int pageSize, int limit, Integer id) {
        PageHelper.startPage(pageSize, limit);
        return integralConsumptionLimitsDao.findConsumptionLimitListById(id);
    }

    /**
     * @param integralConsumptionLimits 插入数据
     * @return
     */
    @Override
    public int insertConsumptionLimit(IntegralConsumptionLimits integralConsumptionLimits) {
        return integralConsumptionLimitsDao.insertConsumptionLimit(integralConsumptionLimits);
    }

    /**
     * @param integralConsumptionLimits 更新数据
     * @return
     */
    @Override
    public int updateConsumptionLimitById(IntegralConsumptionLimits integralConsumptionLimits) {
        return integralConsumptionLimitsDao.updateConsumptionLimitById(integralConsumptionLimits);
    }

    /**
     * @param id 删除数据
     * @return
     */
    @Override
    public int deleteConsumptionLimitById(Integer id) {
        return integralConsumptionLimitsDao.deleteConsumptionLimitById(id);
    }

    /**
     * @param id 通过ID查找限度信息
     * @return
     */
    @Override
    public IntegralConsumptionLimits getConsumptionLimitById(Integer id) {
        return integralConsumptionLimitsDao.findConsumptionLimitById(id);
    }
}
