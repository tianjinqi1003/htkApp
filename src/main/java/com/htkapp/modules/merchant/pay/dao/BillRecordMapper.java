package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.BillRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillRecordMapper {

    /* =====================接口开始===================== */
    //插入记录
    int insertBillRecordByTokenDA0(BillRecord obj);
    //修改记录
    int updateBillRecordByIdDA0(BillRecord obj);
    //查找记录集合
    List<BillRecord> getBillRecordListByTokenDAO(@Param("accountShopToken") String accountShopToken, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("type") Integer type, @Param("descStr") String descStr);
    //查找单条记录
    BillRecord getBillRecordByIdDAO(int recordId);
    //根据类型获取用户的金额
    Double getAmountToBeAccountedByTypeDAO(@Param("accountShopToken") String accountShopToken, @Param("type") int type);
    //更改记录状态
    int changeRecordStatusByOrderNumberDAO(@Param("orderNumber") String orderNumber, @Param("status") int status);
    //当天的实收入金额
    Double getTodayIncomeByDateDAO(String accountShopToken, String startTime, String endTime);
    //当天的订单收入
    Double getTodayOrderIncomeByDateDAO(String accountShopToken, String startTime, String endTime);
    //当天的订单支出
    Double getSpendingOnOrderByDateDAO(String accountShopToken, String startTime, String endTime);
    //根据日期条件查找记录
    List<BillRecord> getBillRecordListByDateDAO(String accountShopToken, String startTime, String endTime);
    //取消订单要删除记录
    int deleteRecordByOrderNumberAndDateDAO(String accountShopToken, String orderNumber);
    //查找当天是否存在记录
    BillRecord getBillRecordByDateAndTokenDAO(String accountShopToken, String startTime, String endTime);

    int updateBillStatus(@Param("accountShopToken") String accountShopToken, @Param("orderNumber") String orderNumber,@Param("status") String status);
    /* =====================接口结束===================== */
}
