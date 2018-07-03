package com.htkapp.modules.merchant.pay.dto;

/**
 * Created by yinqilei on 17-7-1.
 * 团购订单列表返回数据封装类
 */
public class ReturnGroupBuyOrderInfo {

    private Integer orderId;  //订单id

    private String shopName;  //店铺名

    private String logoUrl;  //店铺logo

    private Integer shopId;  //店铺id

    private String orderTime;   //下单时间

    private Integer orderState;  //订单状态

    private Double orderAmount;  //价格

    private String packageName;  //套餐名

    private Integer packageId;  //套餐id

    private String orderNumber; //订单号

    private Long voucherNumber; //消费券

    private int mark;

    private long timeLeft;  //剩余时间

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(Long voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }
}
