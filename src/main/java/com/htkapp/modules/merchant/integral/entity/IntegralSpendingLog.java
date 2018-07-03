package com.htkapp.modules.merchant.integral.entity;

import java.sql.Timestamp;

/**
 * 积分消费信息表
 */

public class IntegralSpendingLog {

    private Integer id;

    private String goodsName;

    private Integer goodsCount;

    private Integer spendingIntegral;   //消费积分

    private Integer shopId;

    private Integer accountId;

    private Integer activityId;

    private String spendingDescription;  //消费说明

    private String spendingType;    //消费类型

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getSpendingIntegral() {
        return spendingIntegral;
    }

    public void setSpendingIntegral(Integer spendingIntegral) {
        this.spendingIntegral = spendingIntegral;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getSpendingDescription() {
        return spendingDescription;
    }

    public void setSpendingDescription(String spendingDescription) {
        this.spendingDescription = spendingDescription;
    }

    public String getSpendingType() {
        return spendingType;
    }

    public void setSpendingType(String spendingType) {
        this.spendingType = spendingType;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
