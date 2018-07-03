package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.merchant.integral.dao.AccountTradingRecordMapper;
import com.htkapp.modules.merchant.integral.entity.AccountTradingRecord;
import com.htkapp.modules.merchant.integral.service.AccountTradingRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountTradingRecordServiceImpl implements AccountTradingRecordService {

    @Resource
    private AccountTradingRecordMapper tradingRecordDao;


    //获取用户消费记录
    @Override
    public List<AccountTradingRecord> getTradingRecordByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit) {
        String orderBy = "gmt_create desc";
        PageHelper.startPage(pageNumber, pageLimit);
        List<AccountTradingRecord> resultList = tradingRecordDao.getTradingRecordByTokenAndShopIdDAO(token, shopId, orderBy);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
}
