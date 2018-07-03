package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.AccountIntegralRecordMapper;
import com.htkapp.modules.merchant.integral.entity.AccountIntegralRecord;
import com.htkapp.modules.merchant.integral.service.AccountIntegralRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountIntegralRecordServiceImpl implements AccountIntegralRecordService {

    @Resource
    private AccountIntegralRecordMapper integralRecordDao;

    //根据用户token 和 店铺id 获取积分记录
    @Override
    public List<AccountIntegralRecord> getIntegralRecordByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit) {
        String orderBy = "gmt_create desc";
        PageHelper.startPage(pageNumber, pageLimit);
        List<AccountIntegralRecord> resultList = integralRecordDao.getIntegralRecordByTokenAndShopIdDAO(token,shopId, orderBy);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //插入积分记录
    @Override
    public void insertIntegralRecord(AccountIntegralRecord integralRecord) {
        int row = integralRecordDao.insertIntegralRecordDAO(integralRecord);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
}
