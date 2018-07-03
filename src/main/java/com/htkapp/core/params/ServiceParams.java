package com.htkapp.core.params;

/**
 * Created by terabithia on 10/20/17.
 * service 内方法参数
 */
public class ServiceParams {

    private String accountShopToken;

    private String accountToken;

    private String startTime;

    private String endTime;

    private String orderNumber;

    private Double orderAmount;

    private int mark;

    //bill构造器
    public ServiceParams(String accountShopToken, String startTime, String endTime, String orderNumber, Double orderAmount) {
        this.accountShopToken = accountShopToken;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderNumber = orderNumber;
        this.orderAmount = orderAmount;
    }

    //插入账单参数构造器
    public ServiceParams(String orderNumber, int mark) {
        this.orderNumber = orderNumber;
        this.mark = mark;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
