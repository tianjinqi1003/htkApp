package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yinqilei on 17-7-6.
 */
public class ProcessedOrderList {

    private Double orderAmount;  //订单总金额

    private String orderNumber;  //订单号

    private String orderTime;  //下单时间

    private Integer orderState;  //订单状态码

    private Integer mark;  //标识

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}
