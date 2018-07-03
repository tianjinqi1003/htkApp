package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.BillRecordStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillRecordStatisticsMapper {

    /* ======================接口开始======================== */
    //根据当天查找是否已存在记录
    BillRecordStatistics getBillRecordStatisticsByCurDateDAO(@Param("token") String accountShopToken, @Param("startTime") String startTime, @Param("endTime") String endTime);
    //插入记录
    int insertBillRecordStatisticsByTokenDAO(BillRecordStatistics statistics);
    //更新记录(以一天为一个单位)
    int updateBillRecordStatisticsByIdDAO(BillRecordStatistics statistics);
    //取消订单后，减掉金额记录
    int updateBillRecordStatisticsByDateDAO(@Param("token") String token, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("amount") Double amount);
    //通过时间条件查找数据
    List<BillRecordStatistics> getBillRecordStatisticsListByDateDAO(@Param("accountShopToken") String accountShopToken, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orderDesc") String orderDesc);
    /* ======================接口结束======================== */
}
