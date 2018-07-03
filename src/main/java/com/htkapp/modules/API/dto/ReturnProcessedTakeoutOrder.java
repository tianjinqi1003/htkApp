package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.List;

/**
 * Created by yinqilei on 17-7-6.
 * 返回已处理的外卖订单封装类
 */
public class ReturnProcessedTakeoutOrder extends ProcessedOrderList {

    private String receiptName;  //收货人名字

    private String receivingCall;  //收货人电话

    private String shippingAddress;  //收货地址

    private Double longitude;  //经度

    private Double latitude;  //纬度

    private Integer orderState;  //订单状态

    private String deliveryTime;  //送达时间

    private String shipperName;  //送货人姓名

    private String deliveryPhone;  //送货人电话

    private List<OrderProduct> productList;  //购买的产品列表

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceivingCall() {
        return receivingCall;
    }

    public void setReceivingCall(String receivingCall) {
        this.receivingCall = receivingCall;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public List<OrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProduct> productList) {
        this.productList = productList;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String getOrderNumber() {
        return super.getOrderNumber();
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        super.setOrderNumber(orderNumber);
    }

    @Override
    public String getOrderTime() {
        return super.getOrderTime();
    }

    @Override
    public void setOrderTime(String orderTime) {
        super.setOrderTime(orderTime);
    }

    @Override
    public Integer getMark() {
        return super.getMark();
    }

    @Override
    public void setMark(Integer mark) {
        super.setMark(mark);
    }
}
