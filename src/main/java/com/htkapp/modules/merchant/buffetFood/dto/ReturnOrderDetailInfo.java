package com.htkapp.modules.merchant.buffetFood.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;

import java.util.List;

public class ReturnOrderDetailInfo {

    /**
     * [
     订单id int id, 店铺名  String shopName, 座位号  String seatName,
     总价格  double  orderAmount,  付款方式　int  paymentMethod 0微　1支
     下单时间  String orderTime,
     点菜列表  List  productLists {
     [ 菜品名　String productName, 数量 int quantity,  double price ]
     }
     ]
     */
    private Integer id;

    private String orderNumber;  //订单号

    private String shopName;

    private Integer shopId;  //店铺Id

    private String logoUrl;  //店铺头像

    private Integer payState;  //支付状态

    private String seatName;

    private Double orderAmount;

    private Integer orderState; //订单状态id

    private Integer paymentMethod;

    private String orderTime;

    private List<BuffetFoodOrderProduct> productLists;

    public ReturnOrderDetailInfo(Integer id, String shopName, Integer shopId, String logoUrl, Integer payState, String seatName, Double orderAmount, Integer paymentMethod, String orderTime) {
        this.id = id;
        this.shopName = shopName;
        this.shopId = shopId;
        this.logoUrl = logoUrl;
        this.payState = payState;
        this.seatName = seatName;
        this.orderAmount = orderAmount;
        this.paymentMethod = paymentMethod;
        this.orderTime = orderTime;
    }

    public ReturnOrderDetailInfo(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
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

    public List<BuffetFoodOrderProduct> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<BuffetFoodOrderProduct> productLists) {
        this.productLists = productLists;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}
