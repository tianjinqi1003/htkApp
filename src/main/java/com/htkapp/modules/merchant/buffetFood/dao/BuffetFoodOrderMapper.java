package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.API.dto.ReturnBuffetFoodOrderData;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderDetailInfo;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderInfo;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuffetFoodOrderMapper {

    /* ===================接口开始======================= */
    //新增自助点餐订单
    int insertOrderDAO(BuffetFoodOrder order);
    //改变订单状态(操作订单)
    int changeOrderStateDAO(@Param("orderNumber") String orderNumber, @Param("stateId") int stateId);
    //根据订单号查找订单
    BuffetFoodOrder getOrderByOrderNumberDAO(@Param("orderNumber") String orderNumber, @Param("token") String token);
    //根据用户token获取订单列表
    List<BuffetFoodOrder> getOrderListByTokenDAO(String token, int shopId, String orderDesc);
    //根据订单id获取订单详情列表
    ReturnOrderDetailInfo getOrderDetailListByTokenAndIdDAO(String token, Integer orderId);
    //通过订单id改变订单支付状态
    int changeOrderPayStateDAO(int orderId,int stateId,String token, int paymentMethodId);
    //根据用户token获取订单列表
    List<ReturnOrderInfo> getOrderListByTokenAndPageDAO(String token, String orderDesc);
    //根据订单ID删除订单
    int delOrderByIdDAO(int orderId);
    //通过订单号获取订单信息
    ReturnOrderInfo getOrderByOrderNumberAndTokenDAO(String token, String orderNumber);
    //根据订单ID删除订单
    int delOrderByOrderNumberDAO(String orderNumber);
    //根据订单号查找订单
    ReturnBuffetFoodOrderData getOrderByOrderNumberADAO(String orderNumber);
    //根据订单号查找订单
    BuffetFoodOrder getBuffetFoodOrderByOrderNumberDAO(String orderNumber);
    //确认下单按钮 新增订单信息
    int confirmOrderButtonDAO(BuffetFoodOrder buffetFoodOrder);
    //自助点餐订单催单
    int reminderDAO(String orderNumber, int stateId);
    //确认调单接口
    int enterAdjustOrderDAO(BuffetFoodOrder order);
    //调单接口
    int updateOrderAdjustOrderJsonDAO(BuffetFoodOrder order);
    //修改订单的调单状态
    int updataOrderAdjustState(BuffetFoodOrder order);
    //根据用户token以及店铺查询订单
    List<BuffetFoodOrder> getOrderListByTokenAndShopId(@Param("token")String token,@Param("shopId")int shopId);
    //通过座位名称以及订单状态查询座位上的订单信息
    BuffetFoodOrder getBuffetFoodOrderByOrderStateAndSeatName(String seatName,int orderstate);
    //当座位改变的时候更新数据库的座位名称
    int updateOrderSeatName(BuffetFoodOrder order);
    //给订单添加临时座位名称
    int updateTempSeatName(BuffetFoodOrder order);
    /* ===================接口结束======================= */


    /* ==========================JSP页面接口开始================================= */
    //更改支付状态
    int changeOrderStateByAccountShopTokenDAO(String orderNumber, int stateId);
    //查询自助点餐订单列表
    List<BuffetFoodOrder> getBuffetFoodOrderListByTokenDAO(String accountToken);
    //根据订单号和商户token查询订单详情
    BuffetFoodOrder getBuffetFoodOrderDAO(@Param("accountShopToken") String accountShopToken, @Param("orderNumber") String orderNumber);
    //根据商户token查找数据
    List<BuffetFoodOrder> getBuffetFoodOrderListByTokenAndConditionDAO(@Param("accountShopToken") String accountShopToken, @Param("orderState") int payState, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orderDesc") String orderDesc);
    //订单结算追加订单中的商品，改变订单总额
    int updateOrderTotalAmountDAO(String orderNumber, double orderAmount);
    //根据商铺id查找自助点餐列表
    List<BuffetFoodOrder> getBuffetFoodOrderListByShopIdAndOrderStateIdDAO(@Param("orderDesc") String orderDesc, @Param("shopId") int shopId, @Param("orderStateId") int orderStateId);
    //验证当天有没有生成订单
    BuffetFoodOrder verifyTodayOrderDAO(Integer shopId, String startTime, String endTime);
    //获取最后一条订单信息
    BuffetFoodOrder getLastOrderDAO(int shopId);
    //查询调整订单列表
    List<BuffetFoodOrder> getAdjustOrderListDAO(@Param("shopId") int shopId, @Param("orderDesc") String orderDesc);
    //查询催单订单列表
    List<BuffetFoodOrder> getReminderOrderListDAO(@Param("shopId") int shopId, @Param("orderDesc") String orderDesc);
    //改变订单状态为未结算订单（修改订单状态，结算订单）
    int dealWithNewOrderDAO(String orderNumber, int orderStateId, int payState);
    //回复催单
    int replyReminderDAO(String orderNumber, int reminderId);
    //核退调单
    int replyFalseDAO(String orderNumber, String adjustJson);
    //通过令牌查询用户的所有订单
    BuffetFoodOrder getBFOLByToken(String orderNumber,int shopId);
    /* ==========================JSP页面接口结束================================= */
}
