package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;

import java.util.List;

//自助点餐订单数据
public class ReturnBuffetFoodOrderData {

    private String orderNumber;

    private String orderTime;

    private List<BuffetFoodOrderProduct> productList;

    private String commitTime;  //已提交时间

    private Integer orderState;  //订单状态  0初始订单   1已下单   2已完成

    private Integer reminderState;  //催单状态  0没有催过单， 1已催单，商家正处理

    private Integer adjustState;  //调单状态   0当前状态没有调单   1正在调单

    private String seatName;

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

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

    public List<BuffetFoodOrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<BuffetFoodOrderProduct> productList) {
        this.productList = productList;
    }

    public String getCommitTime() {
        return commitTime == null ? "5分钟" : commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getReminderState() {
        return reminderState == null ? 0 : reminderState;
    }

    public void setReminderState(Integer reminderState) {
        this.reminderState = reminderState;
    }

    public Integer getAdjustState() {
        return adjustState == null ? 0 : 1;
    }

    public void setAdjustState(Integer adjustState) {
        this.adjustState = adjustState;
    }
}
