package com.htkapp.modules.merchant.pay.entity;

/**
 * Created by yinqilei on 17-7-1.
 * 订单关联的团购套餐实体
 */
public class OrderBuyPackage {

    private Integer id;  //自增

    private String logoUrl;  //套餐图片

    private String packageName;  //套餐名字

    private Integer quantity;  //套餐数量

    private Double price;   //价格

    private Integer packageId;  //套餐id

    private Integer orderId;  //订单id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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


    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
