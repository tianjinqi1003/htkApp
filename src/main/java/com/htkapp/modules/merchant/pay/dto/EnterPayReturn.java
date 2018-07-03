package com.htkapp.modules.merchant.pay.dto;

/**
 * Created by yinqilei on 17-6-29.
 * 确认支付成功后返回
 */
public class EnterPayReturn {

    private String orderTime;  //下单时间

    private Integer orderId;  //订单id

    private String orderNumber;  //订单号

    public EnterPayReturn(Integer orderId, String orderTime){
        this.orderId = orderId;
        this.orderTime = orderTime;
    }

    public EnterPayReturn(Integer orderId, String orderTime, String orderNumber){
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderNumber = orderNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
