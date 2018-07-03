package com.htkapp.core.dto;

/**
 * Created by yinqilei on 17-6-28.
 * 支付宝支付请求封装类
 */

public class AliPayRequest {

    private String orderNumber;  //订单号

    private String orderName;   //支付名称

    private Double amount;  //金额(不能低于1角)

    private String orderSubject;  //订单描述

    private String outBizNo;  //转账单号

    private String payeeType;  //收款方账号类型

    private String payeeAccount;  //收款方账号

    private String payeeRealName;  //收款方真实姓名

    private String remark;  //转账备注


    public AliPayRequest() {
    }

    public AliPayRequest(String orderNumber, Double amount, String outBizNo, String payeeType,
                         String payeeAccount, String remark) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.outBizNo = outBizNo;
        this.payeeType = payeeType;
        this.payeeAccount = payeeAccount;
        this.remark = remark;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getPayeeRealName() {
        return payeeRealName;
    }

    public void setPayeeRealName(String payeeRealName) {
        this.payeeRealName = payeeRealName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
