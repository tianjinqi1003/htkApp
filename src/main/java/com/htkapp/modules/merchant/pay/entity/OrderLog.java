package com.htkapp.modules.merchant.pay.entity;

/**
 * Created by yinqilei on 17-6-29.
 * 订单日志实体类
 */

public class OrderLog {

    private Integer id;

    private Integer orderId;  //订单表id

    private String content;  //记录内容

    private Integer shopId;  //商铺id

    private String accountToken;  //用户token

    public Integer getId() {
        return id;
    }

    public OrderLog setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderLog setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OrderLog setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getShopId() {
        return shopId;
    }

    public OrderLog setShopId(Integer shopId) {
        this.shopId = shopId;
        return this;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public OrderLog setAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }
}
