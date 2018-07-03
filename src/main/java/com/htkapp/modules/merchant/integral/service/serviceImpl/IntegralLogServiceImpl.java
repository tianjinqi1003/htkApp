package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.IntegralLogMapper;
import com.htkapp.modules.merchant.integral.entity.IntegralLog;
import com.htkapp.modules.merchant.integral.service.IntegralLogService;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegralLogServiceImpl implements IntegralLogService {

    @Autowired
    private IntegralLogMapper integralLogDao;

    /* =========================接口开始============================ */
    //插入积分操作日志
    @Override
    public void insertIntegralLog(IntegralLog log) throws Exception {
        try {
            int row = integralLogDao.insertIntegralLogDAO(log);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }


    /* =========================接口结束============================== */
}
