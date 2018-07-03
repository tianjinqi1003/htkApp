package com.htkapp.modules.merchant.pay.entity;

/**
 * Created by yinqilei on 17-6-29.
 * 订单关联的产品实体类
 */

public class OrderProduct {

    private Integer id;  //主键

    private String productName;  //产品名字

    private Integer quantity;  //产品数量

    private Double price;   //价格

    private Integer productId;  //产品id

    private Integer orderId;  //订单id

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
