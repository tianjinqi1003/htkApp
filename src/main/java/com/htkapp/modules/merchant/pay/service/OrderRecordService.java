package com.htkapp.modules.merchant.pay.service;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 */

public interface OrderRecordService {

    /* ===============接口开始============================ */
    //支付成功后插入订单信息
    void paymentSuccessfullyCreatedOrder(OrderRecord orderRecord) throws Exception;

    //根据订单id查询订单信息
    OrderRecord getOrderRecordById(int orderId) throws Exception;

    //根据订单id查询订单信息列表
    List<OrderRecord> getOrderRecordListById(String token, Integer mark, int pageNo, int pageLimit, String orderByDesc) throws Exception;

    //根据订单号查询订单信息
    OrderRecord getOrderRecordByOrderNumber(String orderNumber) throws Exception;

    //根据订单id查询订单信息
    OrderRecord getOrderRecordByOrderId(int orderId) throws Exception;

    //取消支付成功的订单并改变订单状态
    boolean changeOrderStateByOrderNumber(String orderNumber, int orderState) throws OrderException;

    //通过商铺id查询商铺下的所有订单
    List<OrderRecord> getOrderListByShopId(Integer shopId, Integer mark, int pageNo, int pageLimit) throws Exception;

    //通过商铺id和商铺类型mark　查询订单列表
    List<OrderRecord> getOrderListByShopIdAndMark(int shopId, int mark, int pageNo, int pageLimit) throws Exception;

    //商户处理外卖单 接单或拒单
    boolean handlesTakeoutOrder(int shopId, String orderNumber, int stateId) throws RuntimeException;

    //根据订单号和商铺id验证订单信息
    OrderRecord verifyOrderInformation(int shopId, String orderNumber) throws Exception;

    //根据条件查询已处理或未处理的团购订单和外卖订单列表
    List<OrderRecord> getAllProcessedOrderListByIdAndOrderState(int accountShopId, int mark, String orderDesc, int pageNo, int pageLimit) throws Exception;

    //查询已处理或未处理的团购订单
    List<OrderRecord> getGroupBuyOrderListByIdAndOrderState(int shopId, int mark, String orderDesc, int pageNo, int pageLimit) throws Exception;

    //查询已处理或未处理的外卖订单
    List<OrderRecord> getTakeoutOrderListByIdAndOrderState(int shopId, int mark, String orderDesc, int pageNo, int pageLimit) throws Exception;

    //通过订单号删除订单
    void deleteOrderByOrderNumber(String orderNumber, String token);

    //根据订单号和店铺id查找订单
    OrderRecord getOrderRecordByTokenAndShopId(String orderNumber, int shopId);
    /* ==================接口结束=================================== */

    /* ====================JSP页面接口开始========================= */
    //根据商户id和mark查询当天订单数量
    int getOrderListCountByAccountShopIdAndMark(int accountShopId, int mark, String time) throws Exception;

    //根据商户id查询商户当天的收入金额
    Double getTodayIncomeByAccountShopId(int accountShopId, String time) throws Exception;

    //商家外卖管理查找订单列表(全部列表、已处理、未处理)
    List<OrderRecord> getTakeoutOrderListByOrderState(int orderState, int accountShopId, int pageNo, int pageLimit) throws Exception;

    //商家外卖管理查找全部订单列表加条件
    List<OrderRecord> getTakeoutOrderListByCondition(int accountShopId, String orderNumber, int orderMark, int pageNo, int pageLimit) throws Exception;

    //商家团购管理查找订单列表(全部列表、已处理、未处理)
    List<OrderRecord> getGroupBuyOrderListByOrderState(int orderState, int accountShopId, int pageNo, int pageLimit) throws Exception;

    //商家团购管理查找全部订单列表加条件
    List<OrderRecord> getGroupBuyOrderListByCondition(int accountShopId, String orderNumber, int orderMark, int pageNo, int pageLimit) throws Exception;

    //查找当前未接单数量
    int getNewOrderNumber(String accountShopToken) throws Exception;

    //昨日订单数
    int getOrderNumberByDate(String accountShopToken, String startTime, String endTime) throws Exception;

    //昨日营业额
    double getRevenueByDate(String accountShopToken, String startTime, String endTime) throws Exception;

    //查找外卖订单列表
    List<OrderRecord> getTakeoutOrderListByToken(String accountShopToken, int stateId, int pageNo, int pageLimit, String orderNumber) throws Exception;

    //查找团购订单列表
    List<OrderRecord> getGroupBuyOrderListByToken(String accountShopToken, int stateId, int pageNo, int pageLimit, String orderNumber) throws Exception;

    //根据订单号查询订单信息
    OrderRecord getOrderDetailByOrderNumber(String accountShopToken, String orderNumber) throws Exception;

    //外卖订单页面订单详情(筛选条件1：全部，订单序号，下单时间  筛选条件２：日期)
    List<OrderRecord> getOrderPageDataByCondition(int shopId, int mark, int pageNo, int pageLimit, String startDate, String endDate, int status);

    //外卖订单－实时订单条件查询
    List<OrderRecord> getTakeoutRealTimeOrderByCondition(int shopId, String startDate, String endDate, int statusCode) throws Exception;

    //根据日期查找是否已有订单记录（用于插入序号）
    OrderRecord verifyOrderExitByDate(Integer shopId, String startTime, String endTime, int mark);

    //根据时间统计已接订单数量
    int statisticalOrderQuantityByStateIdAndDate(Integer shopId, Integer stateId, String startTime, String endTime);

    //根据时间统计订单总收入
    Double statisticalIncomeByDate(Integer shopId, String startTime, String endTime);

    //根据时间和状态统计当前状态下的订单数量
    int statisticalQuantityByStateIdAndDate(Integer shopId, Integer stateId, String startTime, String endTime);

    //根据商铺id和排序条件查询订单列表
    List<OrderRecord> getOrderRecordListByDescAndShopId(int shopId, String orderDesc, int pageNum, int pageLimit, String keyWord);

    OrderProduct getOrderProduct(Integer id);

    /**
     * @param shopId
     * @param dateStart
     * @param dateEnd   @return
     * @desc 获取最新一月的商家成交数量
     */
    int getOrderQuantities(Integer shopId, String dateStart, String dateEnd);
    /* ====================JSP页面接口结束========================= */
}
