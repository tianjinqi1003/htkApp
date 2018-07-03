package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.IntegralManageRecordMapper;
import com.htkapp.modules.merchant.integral.entity.IntegralManageRecord;
import com.htkapp.modules.merchant.integral.service.IntegralManageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yinqilei on 17-5-18.
 */
@Service
public class IntegralManageRecordServiceImpl implements IntegralManageRecordService {

    @Resource
    private IntegralManageRecordMapper integralManageRecordDao;


    /* ==========================接口开始============================ */
    //插入记录
    @Override
    public void insertRecordByToken(IntegralManageRecord record) {
       int row = integralManageRecordDao.insertRecordByTokenDAO(record);
       if(row <= 0){
           throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
       }
    }
    /* ==========================接口结束=========================== */

}
