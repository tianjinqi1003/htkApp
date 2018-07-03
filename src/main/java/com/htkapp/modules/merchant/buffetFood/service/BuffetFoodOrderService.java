package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.modules.API.dto.ReturnBuffetFoodOrderData;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderDetailInfo;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderInfo;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuffetFoodOrderService {


    /* =====================接口开始===================== */
    //新增自助点餐订单
    Map<String,String> insertOrder(BuffetFoodOrder order) throws Exception;
    //改变订单状态(操作订单)
    void changeOrderState(String orderNumber, int stateId) throws Exception;
    //根据订单号查找订单
    BuffetFoodOrder getOrderByOrderNumber( String orderNumber, String token) throws Exception;
    //根据用户token获取订单列表
    List<BuffetFoodOrder> getOrderListByToken(String token, int shopId, int pageNo, int pageLimit) throws Exception;
    //根据订单id获取订单详情列表
    ReturnOrderDetailInfo getOrderDetailListByTokenAndId(String token, Integer orderId) throws Exception;
    //通过订单id改变订单支付状态
    void changeOrderPayState(int orderId,int stateId,String token, int paymentMethodId)throws Exception;
    //根据用户token获取订单列表
    List<ReturnOrderInfo> getOrderListByToken(String token, int pageNo, int pageLimit);
    //根据订单ID删除订单
    void delOrderById(int orderId)throws Exception;
    //通过订单号获取订单信息
    ReturnOrderInfo getOrderByOrderNumberAndToken(String token, String orderNumber);
    //根据订单ID删除订单
    void delOrderByOrderNumber(String orderNumber);
    //根据订单号查找订单
    ReturnBuffetFoodOrderData getOrderByOrderNumber(String orderNumber);
    //根据订单号查找订单
    BuffetFoodOrder getBuffetFoodOrderByOrderNumber(String orderNumber);
    //自助点餐订单催单
    void reminder(String orderNumber, int stateId);
    //确认下单按钮 新增订单信息
    void confirmOrderButton(BuffetFoodOrder buffetFoodOrder);
    //确认调单接口
    void enterAdjustOrder(BuffetFoodOrder buffetFoodOrder);
    //调单接口
    void updateOrderAdjustOrderJson(BuffetFoodOrder order);
    //更新调单状态
    void updataOrderAdjustState(BuffetFoodOrder order);
    //通过店铺id以及token获取所哟订单
    public List<BuffetFoodOrder> getOrderListByTokenAndShopId(String token,int shopId);
    //通过座位名称以及订单状态查询当前座位上的订单
    BuffetFoodOrder getBuffetFoodOrderByOrderStateAndSeatName(String seatName);
    //当座位改变的时候更新数据库的座位名称
    void updateOrderSeatName(BuffetFoodOrder order);
    //给订单添加临时座位名称
    void updateTempSeatName(BuffetFoodOrder order);
    
    
    /* =====================接口结束===================== */

    /* =======================JSP页面接口开始=============================== */
    //查询自助点餐订单列表
    List<BuffetFoodOrder> getBuffetFoodOrderListByToken(String token, int pageNo, int pageLimit) throws Exception;
    //根据订单号和商户token查询订单详情
    BuffetFoodOrder getBuffetFoodOrder(String accountShopToken, String orderNumber)throws Exception;
    //查找自助点餐订单列表，并关联商户入账提现表查看是否提现
    //根据商户token查找数据
    List<BuffetFoodOrder> getBuffetFoodOrderListByToken(String accountShopToken, String startTime, String endTime, int payState, int pageNumber, int pageLimit);
    //更改支付状态
    void changeOrderStateByAccountShopToken(String orderNumber, int stateId) throws Exception;
    //订单结算追加订单中的商品，改变订单总额
    void updateOrderTotalAmount(String orderNumber, double orderAmount) throws Exception;
    //根据商铺id查找自助点餐列表
    List<BuffetFoodOrder> getBuffetFoodOrderListByShopIdAndOrderStateId(String orderDesc, int shopId, int orderStateId, int pageNum, int pageLimit);
    //验证当天有没有生成订单
    BuffetFoodOrder verifyTodayOrder(Integer shopId, String startTime, String endTime);
    //获取最后一条订单信息
    BuffetFoodOrder getLastOrder(int shopId);
    //查询调整订单列表
    List<BuffetFoodOrder> getAdjustOrderList(int shopId, String orderDesc, int pageNumber, int pageLimit);
    //查询催单订单列表
    List<BuffetFoodOrder> getReminderOrderList(int shopId, String orderDesc, int pageNumber, int pageLimit);
    //改变订单状态为未结算订单
    void dealWithNewOrder(String orderNumber, int orderStateId, int payState);
    //回复催单
    void replyReminder(String orderNumber, int reminderId);
    //根据用户token/订单号/店铺号查询相应的订单
    BuffetFoodOrder getBFOLByToken(String orderNumber,int shopId);
    /* =====================JSP页面接口结束================================= */
}
