package com.htkapp.modules.common.entity;

import java.sql.Timestamp;

/**
 * 优惠券列表
 */

public class Coupon {

    private Integer couponId;   //主键

    private String couponTitle;   //优惠券标题

    private Double couponAmount;  //优惠券金额

    private String couponPhone;  //优惠券使用者手机号

    private Timestamp couponUseDate;  //优惠券截止日期

    private String couponShopBusinessHours;  //优惠券发布商家的营业时间

    private Integer couponShopId;  //优惠券发布商家ID

    private Integer couponAccountId;  //优惠券使用者ID

    private String shopName;   //商空店铺名字


    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponPhone() {
        return couponPhone;
    }

    public void setCouponPhone(String couponPhone) {
        this.couponPhone = couponPhone;
    }

    public Timestamp getCouponUseDate() {
        return couponUseDate;
    }

    public void setCouponUseDate(Timestamp couponUseDate) {
        this.couponUseDate = couponUseDate;
    }

    public String getCouponShopBusinessHours() {
        return couponShopBusinessHours;
    }

    public void setCouponShopBusinessHours(String couponShopBusinessHours) {
        this.couponShopBusinessHours = couponShopBusinessHours;
    }

    public Integer getCouponShopId() {
        return couponShopId;
    }

    public void setCouponShopId(Integer couponShopId) {
        this.couponShopId = couponShopId;
    }

    public Integer getCouponAccountId() {
        return couponAccountId;
    }

    public void setCouponAccountId(Integer couponAccountId) {
        this.couponAccountId = couponAccountId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
