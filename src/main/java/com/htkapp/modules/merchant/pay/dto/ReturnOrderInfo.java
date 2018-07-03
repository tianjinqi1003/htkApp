package com.htkapp.modules.merchant.pay.dto;

import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 * 订单详情封装类
 */

public class ReturnOrderInfo {

    private Integer orderId;  //订单id

    private Integer orderState;  //订单状态  1:用户下单成功   2:商家接单成功   3:派送中   4:用户收货成功 5取消订单

    private String logoUrl;   //店铺图片url

    private Integer shopId;  //店铺id

    private String shopName;   //店铺名字

    private String shopMobilePhone;  //商铺手机号码

    private List<OrderProduct> productList;  //产品集合

    private Double orderAmount;  //订单金额

    private String receiptName;  //收货人姓名

    private Integer sex;   //收货人性别

    private String receivingCall;  //收货电话

    private String shippingAddress;  //收货地址

    private String orderNumber;  //订单号

    private Integer paymentMethod;   //0微信    1支付宝

    private String orderTime;   //下单时间

    private String oneProductName;

    private String remark; //备注

    public String getRemark() {
        return remark;
    }

    public ReturnOrderInfo setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    private Double deliveryFee;  //配送费

    private Integer commentStatus;  //评价状态

    private int mark;

    private long timeLeft;  //剩余时间

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public ReturnOrderInfo setOrderState(Integer orderState) {
        this.orderState = orderState;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public ReturnOrderInfo setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public ReturnOrderInfo setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public List<OrderProduct> getProductList() {
        return productList;
    }

    public ReturnOrderInfo setProductList(List<OrderProduct> productList) {
        this.productList = productList;
        return this;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public ReturnOrderInfo setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public ReturnOrderInfo setReceiptName(String receiptName) {
        this.receiptName = receiptName;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public ReturnOrderInfo setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getReceivingCall() {
        return receivingCall;
    }

    public ReturnOrderInfo setReceivingCall(String receivingCall) {
        this.receivingCall = receivingCall;
        return this;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public ReturnOrderInfo setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public ReturnOrderInfo setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public ReturnOrderInfo setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public ReturnOrderInfo setOrderTime(String orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public String getShopMobilePhone() {
        return shopMobilePhone;
    }

    public void setShopMobilePhone(String shopMobilePhone) {
        this.shopMobilePhone = shopMobilePhone;
    }


    public String getOneProductName() {
        return oneProductName;
    }

    public void setOneProductName(String oneProductName) {
        this.oneProductName = oneProductName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public ReturnOrderInfo setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getShopId() {
        return shopId;
    }

    public ReturnOrderInfo setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }
}
