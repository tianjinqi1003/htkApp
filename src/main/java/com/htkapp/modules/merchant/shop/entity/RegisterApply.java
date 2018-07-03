package com.htkapp.modules.merchant.shop.entity;

public class RegisterApply {

    private Integer id;

    private Integer shopId;

    private Integer accountShopId;

    private String phone;

    private String identity;  //身份证号

    private String shopName;  //店铺名

    private String address;  //位置

    private String applyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
