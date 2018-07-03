package com.htkapp.modules.merchant.buffetFood.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.List;

public class ReturnOrderInfo {

    /**
     * id, buffet_food_order.gmt_create as order_time, seat_name,shop.shop_name,
     shop.logo_url,shop.shop_id,pay_state
     */

    private Integer id;

    private String orderTime;

    private String seatName;

    private String shopName;

    private String logoUrl;

    private Integer shopId;

    private Integer payState;

    private int mark;
    
    private int orderState;

    private String orderNumber;  //订单号

    private Double orderAmount;  //订单金额


    private List<BuffetFoodOrderProduct> productList; //产品集合

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

    public List<BuffetFoodOrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<BuffetFoodOrderProduct> productLists) {
        this.productList = productLists;
    }

    public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }
}
