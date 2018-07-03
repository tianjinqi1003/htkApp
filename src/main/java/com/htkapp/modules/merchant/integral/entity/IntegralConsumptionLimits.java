package com.htkapp.modules.merchant.integral.entity;

import java.sql.Timestamp;

/**
 *
 */
public class IntegralConsumptionLimits {

    private Integer id;

    private Integer beginConfine;

    private Integer endConfine;

    private Integer userExp;

    private Integer shopId;

    private Integer accountShopId;

    private Timestamp gmt_create;

    private Timestamp gmt_modifiedn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeginConfine() {
        return beginConfine;
    }

    public void setBeginConfine(Integer beginConfine) {
        this.beginConfine = beginConfine;
    }

    public Integer getEndConfine() {
        return endConfine;
    }

    public void setEndConfine(Integer endConfine) {
        this.endConfine = endConfine;
    }

    public Integer getUserExp() {
        return userExp;
    }

    public void setUserExp(Integer userExp) {
        this.userExp = userExp;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAccountShopId() {
        return accountShopId;
    }

    public void setAccountShopId(Integer accountShopId) {
        this.accountShopId = accountShopId;
    }

    public Timestamp getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Timestamp gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Timestamp getGmt_modifiedn() {
        return gmt_modifiedn;
    }

    public void setGmt_modifiedn(Timestamp gmt_modifiedn) {
        this.gmt_modifiedn = gmt_modifiedn;
    }
}
