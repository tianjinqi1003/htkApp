package com.htkapp.modules.merchant.pay.entity;


/**
 * 订单退款表实体
 */

public class OrderRefund {

    private Integer id;

    private String outRefundNo;  //退款单号

    private String totalFee;  //订单金额

    private String refundFee;  //退款金额

    private String orderNumber;  //订单号

    private String content;   //退款内容

    private Integer payWay;  //支付方式

    public Integer getId() {
        return id;
    }

    public OrderRefund setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public OrderRefund setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
        return this;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public OrderRefund setTotalFee(String totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public OrderRefund setRefundFee(String refundFee) {
        this.refundFee = refundFee;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public OrderRefund setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public OrderRefund setPayWay(Integer payWay) {
        this.payWay = payWay;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OrderRefund setContent(String content) {
        this.content = content;
        return this;
    }
}
