package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.BillBalanceSheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillBalanceSheetMapper {


    /* ============================JSP接口开始================================== */
    //查找收支记录
    List<BillBalanceSheet> getBalanceSheetRecordListByAccountShopTokenDAO(@Param("token") String token, @Param("type") Integer type, @Param("starTime") String startTime, @Param("endTime") String endTime, @Param("orderDesc") String orderDesc);
    //获取账户余额
    Double getAccountBalanceDAO(String accountShopToken);
    //根据日期查找记录
    BillBalanceSheet getBalanceSheetRecordByDateDAO(@Param("token") String token, @Param("startTime") String startTime, @Param("endTime") String endTime);
    //根据id追加当天日期记录值
    int updateBalanceSheetRecordByIdDAO(BillBalanceSheet balanceSheet);
    //插入收支记录
    int insetBalanceSheetRecordDAO(BillBalanceSheet obj);
    //查找数据库中的最新的一条记录
    BillBalanceSheet getBalanceSheetRecordByLastDateDAO(String token);

    int updateAccountBalance(String accountShopToken, double newBalance);
    /* ============================JSP接口结束================================ */
}
