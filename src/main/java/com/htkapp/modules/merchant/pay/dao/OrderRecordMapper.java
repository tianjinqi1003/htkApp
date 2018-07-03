package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.dto.AllOrderList;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 */
public interface OrderRecordMapper {

    /* =================接口开始======================= */
    //支付成功后插入订单信息
    int paymentSuccessfullyCreatedOrderDAO(OrderRecord orderRecord);
    //根据订单id查询订单信息
    OrderRecord getOrderRecordByIdDAO(int orderId);
    //根据订单id查询订单信息列表
    List<OrderRecord> getOrderRecordListByIdDAO(@Param("token") String token, @Param("mark") Integer mark, @Param("orderByDesc") String orderByDesc);
    //根据订单号查询订单信息
    OrderRecord getOrderRecordByOrderNumberDAO(String orderNumber);
    //根据订单id查询订单信息
    OrderRecord getOrderRecordByOrderIdDAO(int orderId);
    //取消支付成功的订单并改变订单状态
    int changeOrderStateByOrderNumberDAO(@Param("orderNumber") String orderNumber, @Param("orderState") int orderState);
    //通过商铺id查询商铺下的所有订单
    List<OrderRecord> getOrderListByShopIdDAO(@Param("shopId") Integer shopId, @Param("mark") int mark);
    //通过商铺id和商铺类型mark　查询订单列表
    List<OrderRecord> getOrderListByShopIdAndMarkDAO(@Param("shopId") int shopId, @Param("mark") int mark);
    //商户处理外卖单 接单或拒单
    int handlesTakeoutOrderDAO(@Param("shopId") int shopId, @Param("orderNumber") String orderNumber, @Param("stateId") int stateId);
    //根据订单号和商铺id验证订单信息
    OrderRecord verifyOrderInformationDAO(@Param("shopId") int shopId, @Param("orderNumber") String orderNumber);
    //根据条件查询已处理或未处理的团购订单和外卖订单列表
    List<OrderRecord> getAllProcessedOrderListByIdAndOrderStateDAO(@Param("accountShopId") int accountShopId, @Param("mark") int mark, @Param("orderDesc") String orderDesc);
    //查询已处理或未处理的团购订单
    List<OrderRecord> getGroupBuyOrderListByIdAndOrderStateDAO(@Param("shopId") int shopId, @Param("mark") int mark, @Param("orderDesc") String orderDesc);
    //查询已处理或未处理的外卖订单
    List<OrderRecord> getTakeoutOrderListByIdAndOrderStateDAO(@Param("shopId") int shopId, @Param("mark") int mark, @Param("orderDesc") String orderDesc);
    //根据日期查找是否已有订单记录（用于插入序号）
    OrderRecord verifyOrderExitByDateDAO(Integer shopId, String startTime, String endTime, int mark);
    //获取总列表订单序号
    Integer getAllNumberByLimitDAO(Integer shopId);
    //通过订单号删除订单
    int deleteOrderByOrderNumberDAO(String orderNumber, String token);
    //根据订单号和店铺id查找订单
    OrderRecord getOrderRecordByTokenAndShopIdDAO(String orderNumber, int shopId);
    /* =================接口结束================================== */

    /* =========================共用接口开始=========================== */
    //关闭5分钟内未支付订单
    int cancelUnpaidOrderByDate(String dateParams);
    /* =========================共用接口结束=========================== */

    /* =====================JSP页面接口开始====================== */
    //根据商户id和mark查询当天订单数量
    int getOrderListCountByAccountShopIdAndMarkDAO(@Param("accountShopId") int accountShopId,@Param("mark") int mark, @Param("time") String time);
    //根据商户id查询商户当天的收入金额
    Double getTodayIncomeByAccountShopIdDAO(@Param("accountShopId") int accountShopId, @Param("time") String time);
    //商家外卖管理查找订单列表(全部列表、已处理、未处理)
    List<OrderRecord> getTakeoutOrderListByOrderStateDAO(@Param("orderState") int orderState, @Param("accountShopId") int accountShopId);
    //商家外卖管理查找全部订单列表加条件
    List<OrderRecord> getTakeoutOrderListByConditionDAO(@Param("accountShopId") int accountShopId, @Param("orderNumber") String orderNumber, @Param("orderMark") int orderMark);
    //商家团购管理查找订单列表(全部列表、已处理、未处理)
    List<OrderRecord> getGroupBuyOrderListByOrderStateDAO(@Param("orderState") int orderState, @Param("accountShopId") int accountShopId);
    //商家团购管理查找全部订单列表加条件
    List<OrderRecord> getGroupBuyOrderListByConditionDAO(@Param("accountShopId") int accountShopId, @Param("orderNumber") String orderNumber, @Param("orderMark") int orderMark);
    //查找当前未接单数量
    int getNewOrderNumberDAO(String accountShopToken);
    //昨日订单数
    int getOrderNumberByDate(@Param("accountShopToken") String accountShopToken, @Param("startTime") String startTime, @Param("endTime") String endTime);
    //昨日营业额
    Double getRevenueByDate(@Param("accountShopToken") String accountShopToken, @Param("startTime") String startTime, @Param("endTime") String endTime);
    //查找外卖订单列表
    List<OrderRecord> getTakeoutOrderListByTokenDAO(@Param("accountShopToken") String accountShopToken,@Param("stateId") int stateId, @Param("orderDesc") String orderDesc, @Param("orderNumber") String orderNumber);
    //查找外卖订单列表
    List<OrderRecord> getGroupBuyOrderListByTokenDAO(@Param("accountShopToken") String accountShopToken, @Param("stateId") int stateId, @Param("orderDesc") String orderDesc, @Param("orderNumber") String orderNumber);
    //根据订单号查询订单信息
    OrderRecord getOrderDetailByOrderNumberDAO(@Param("accountShopToken") String accountShopToken, @Param("orderNumber") String orderNumber);
    //通过订单号查询订单详情
    OrderRecord getOrderDetailByOrderNumberDAO(String orderNumber);
    //通过店铺id查找订单(外卖历史订单)
    List<OrderRecord> getOrderPageDataByConditionDAO(@Param("shopId") int shopId,@Param("mark") int mark,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") int status, @Param("orderDesc") String orderDesc);
    //外卖订单－实时订单条件查询
    List<OrderRecord> getTakeoutRealTimeOrderByConditionDAO(@Param("shopId") int shopId, @Param("startDate")String startDate, @Param("endDate") String endDate, @Param("statusCode") int statusCode);
    //根据时间统计已接订单数量
    int statisticalOrderQuantityByStateIdAndDateDAO(Integer shopId, Integer stateId, String startTime, String endTime);
    //根据时间统计订单总收入
    Double statisticalIncomeByDateDAO(Integer shopId, String startTime, String endTime);
    //根据时间和状态统计当前状态下的订单数量
    int statisticalQuantityByStateIdAndDateDAO(Integer shopId, Integer stateId, String startTime, String endTime);
    //根据商铺id和排序条件查询订单列表
    List<OrderRecord> getOrderRecordListByDescAndShopIdDAO(@Param("shopId") int shopId, @Param("orderDesc") String orderDesc, @Param("keyWord") String keyWord);

    OrderProduct getOrderProduct(Integer id);

    int getOrderHasDealedQuantities(@Param("shopId") Integer shopId, @Param("startDate") String dateStart, @Param("endDate") String dateEnd);
    /* =====================JSP页面接口结束====================== */
}
