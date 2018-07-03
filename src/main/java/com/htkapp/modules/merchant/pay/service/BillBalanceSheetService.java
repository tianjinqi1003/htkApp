package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.entity.BillBalanceSheet;

import java.util.List;

public interface BillBalanceSheetService {

    /* ====================接口开始====================== */
    //获取账户余额
    double getAccountBalance(String accountShopToken);
    //更新账户余额
    int updateAccountBalance(String accountShopToken,double newBalance);
    //查找收支记录
    List<BillBalanceSheet> getBalanceSheetRecordListByAccountShopToken(String token, String startTime, String endTime, Integer type, int pageNum, int pageLimit);
    //记录帐单支出、收入记录
    void keepRecordByAccountShopToken(BillBalanceSheet balanceSheet) throws Exception;
    /* ====================接口结束====================== */
}
