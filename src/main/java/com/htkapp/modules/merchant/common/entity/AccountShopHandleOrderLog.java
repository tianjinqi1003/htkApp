package com.htkapp.modules.merchant.common.entity;

/**
 * Created by yinqilei on 17-7-10.
 * 商户处理订单日志实体类
 */

public class AccountShopHandleOrderLog {

    private Integer id;

    private String content;  //内容

    private String orderNumber;  //订单号

    private String accountShopToken;  //商户token

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }
}
