package com.htkapp.modules.merchant.buffetFood.entity;

/**
 * 自助点餐订单产品临时表
 */
public class BuffetFoodOrderProductTemporary {

    private Integer id;

    private Integer productId;  //产品id

    private String productName;  //产品名字

    private Integer quantity;  //数量

    private Double price;   //价格

    private Integer orderId;   //订单表主键id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

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
}
