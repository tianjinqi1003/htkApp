package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.entity.BillRecordStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillRecordStatisticsService {

    /* =========================接口开始============================ */
    //根据当天查找是否已存在记录
    BillRecordStatistics getBillRecordStatisticsByCurDate(String accountShopToken,String startTime,String endTime);
    //插入记录
    void insertBillRecordStatisticsByToken(BillRecordStatistics statistics) throws Exception;
    //更新记录(以一天为一个单位)
    void updateBillRecordStatisticsById(BillRecordStatistics statistics) throws Exception;
    //添加记录
    void keepRecordByAccountShopToken(BillRecordStatistics statistics) throws Exception;
    //取消订单后，减掉金额记录
    void updateBillRecordStatisticsByDate(String token, String startTime, String endTime, Double amount) throws Exception;
    //通过时间条件查找数据
    List<BillRecordStatistics> getBillRecordStatisticsListByDate(String accountShopToken, String startTime, String endTime, int pageNumber, int pageLimit) throws Exception;
    /* =========================接口结束============================ */
}
