package com.htkapp.modules.merchant.pay.dto;

/**
 * Created by yinqilei on 17-6-28.
 *
 */
public class CallUpAliPayReturnData {

    private String orderNumber;

    private String aliPayResponseBody;

    private String orderBody;

    private String orderTime;

    private Integer orderId;

    public CallUpAliPayReturnData(String orderNumber, String aliPayResponseBody, String orderBody){
        this.orderNumber = orderNumber;
        this.aliPayResponseBody = aliPayResponseBody;
        this.orderBody = orderBody;
    }

    public CallUpAliPayReturnData(){

    }

    public CallUpAliPayReturnData(String orderNumber, String aliPayResponseBody){
        this.orderNumber = orderNumber;
        this.aliPayResponseBody = aliPayResponseBody;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAliPayResponseBody() {
        return aliPayResponseBody;
    }

    public void setAliPayResponseBody(String aliPayResponseBody) {
        this.aliPayResponseBody = aliPayResponseBody;
    }

    public String getOrderBody() {
        return orderBody;
    }

    public void setOrderBody(String orderBody) {
        this.orderBody = orderBody;
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
}
