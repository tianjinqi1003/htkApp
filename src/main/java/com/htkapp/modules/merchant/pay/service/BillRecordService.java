package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.entity.BillRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillRecordService {

    /* =====================接口开始======================= */
    //根据类型获取用户的金额
    double getAmountToBeAccountedByType(String accountShopToken, int type);
    //查找记录集合
    List<BillRecord> getBillRecordListByToken(String accountShopToken, String startTime, String endTime, Integer type, int pageNumber, int pageLimit);
    //插入记录
    void insertBillRecordByToken(BillRecord obj) throws Exception;
    //更改记录状态
    void changeRecordStatusByOrderNumber(String orderNumber, int status) throws Exception;
    //当天的实收入金额
    Double getTodayIncomeByDate(String accountShopToken, String startTime, String endTime);
    //当天的订单收入
    Double getTodayOrderIncomeByDate(String accountShopToken, String startTime, String endTime);
    //当天的订单支出
    Double getSpendingOnOrderByDate(String accountShopToken, String startTime, String endTime);
    //根据日期条件查找记录
    List<BillRecord> getBillRecordListByDate(String accountShopToken, String startTime, String endTime);
    //取消订单要删除记录
    void deleteRecordByOrderNumberAndDate(String accountShopToken, String orderNumber) throws Exception;

    void updateBillStatus(String accountShopToken, String orderNumber,String status)throws Exception;
    /* =====================接口结束======================= */

}
