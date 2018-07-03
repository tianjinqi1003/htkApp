package com.htkapp.modules.merchant.integral.entity;

/**
 * 积分操作日志实体类
 */
public class IntegralLog {

    private Integer id;

    private String description;

    private Integer val;

    private Integer shopId;

    private String accountToke;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getAccountToke() {
        return accountToke;
    }

    public void setAccountToke(String accountToke) {
        this.accountToke = accountToke;
    }
}
