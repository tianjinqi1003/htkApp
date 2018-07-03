package com.htkapp.modules.merchant.pay.dto;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;

import java.util.List;

/**
 * Created by yinqilei on 17-7-3.
 * 团购订单详情返回封装类
 * { 订单状态   ，  订单id  ， 套餐id ， 支付方式 ， 下单时间
 * 商铺名称  ， 商铺id ， 商铺头像 ， 套餐名称，实付款 ，商铺电话  }
 */
public class ReturnGroupBuyOrderDetails {

    private Integer orderState; //订单状态

    private Integer orderId;  //订单id

    private String orderNumber; //订单号

    private Integer packageId;  //套餐id

    private Integer quantity;  //数量

    private Integer paymentMethod;   //0微信    1支付宝

    private String orderTime;  //下单时间

    private String shopName;  //商铺名字

    private Integer shopId;  //商铺id

    private String logoUrl;  //店铺logo

    private String packageName;  //套餐名称

    private Double orderAmount;  //实付款

    private String shopPhone;  //店铺电话

    private String shopAddress;  //商家地址

    private Double retailPrice;  //门市价

    private Double longitude;   //地址经度

    private Double latitude;   //地址纬度

    private long voucherNumber;  //消费券码

    private String validityTime;  //有效时间

    private String userPhone; //用户手机号

    private List<BuyPackageContent> buyPackageContentList;  //团购套餐内详情

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
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

    public long getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(long voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public List<BuyPackageContent> getBuyPackageContentList() {
        return buyPackageContentList;
    }

    public void setBuyPackageContentList(List<BuyPackageContent> buyPackageContentList) {
        this.buyPackageContentList = buyPackageContentList;
    }

    public String getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(String validityTime) {
        this.validityTime = validityTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
