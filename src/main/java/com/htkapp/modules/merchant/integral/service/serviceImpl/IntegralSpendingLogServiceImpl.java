package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.merchant.integral.dao.IntegralSpendingLogMapper;
import com.htkapp.modules.merchant.integral.entity.IntegralSpendingLog;
import com.htkapp.modules.merchant.integral.service.IntegralSpendingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分消费信息
 */
@Service
public class IntegralSpendingLogServiceImpl implements IntegralSpendingLogService {

    @Autowired
    private IntegralSpendingLogMapper integralSpendingLogDao;

    /* ==========================接口开始=========================== */
    //通过ID查询用户的消费记录
    @Override
    public List<IntegralSpendingLog> getSpendingLogById(int pageSize, int limit, IntegralSpendingLog integralSpendingLog) {
        PageHelper.startPage(pageSize, limit);
        return integralSpendingLogDao.findSpendingLogById(integralSpendingLog);
    }
    /* ========================接口结束============================== */

    /**
     * @param integralSpendingLog 插入积分消费记录
     * @return
     */
    @Override
    public int insertById(IntegralSpendingLog integralSpendingLog) {
        return integralSpendingLogDao.insertById(integralSpendingLog);
    }

    /**
     * @param integralSpendingLog  查询用户的积分消费总数
     * @return
     */
    @Override
    public int getSpendingIntegralValue(IntegralSpendingLog integralSpendingLog) {
        return integralSpendingLogDao.findSpendingIntegralValue(integralSpendingLog);
    }
}
