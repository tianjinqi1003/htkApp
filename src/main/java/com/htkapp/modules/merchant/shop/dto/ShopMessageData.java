package com.htkapp.modules.merchant.shop.dto;

public class ShopMessageData {

    private String shopName;  //店铺名字

    private String shopAddress;  //店铺地址

    private String shopCategory;  //店铺分类名

    private String shopJoinTime;  //加入时间

    private String remainingUseTime; //剩余使用时间

    private Long shopNumber; //店铺编号

    private String shopQRCodeUrl;  //店铺二维码图片url

    private String shopAvatarUrl;  //店铺图片url

    private String shopAccountName; //店铺账号名  类似(18660706071_htk)

    private String businessHours;  //营业时间

    private String phone;  //订餐电话

    private String shopBulletin;  //店铺公告

    private String shopIntroduction;  //店铺简介

    private Integer shopState;

    public ShopMessageData(){
        //无参构造函数
    }

    public ShopMessageData(String shopName, String shopAddress, String shopCategory,
                           String shopJoinTime, String remainingUseTime, Long shopNumber,
                           String shopQRCodeUrl, String shopAvatarUrl,
                           String shopAccountName, String businessHours, String phone,
                           String shopBulletin, String shopIntroduction,
                           Integer shopState) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopCategory = shopCategory;
        this.shopJoinTime = shopJoinTime;
        this.remainingUseTime = remainingUseTime;
        this.shopNumber = shopNumber;
        this.shopQRCodeUrl = shopQRCodeUrl;
        this.shopAvatarUrl = shopAvatarUrl;
        this.shopAccountName = shopAccountName;
        this.businessHours = businessHours;
        this.phone = phone;
        this.shopBulletin = shopBulletin;
        this.shopIntroduction = shopIntroduction;
        this.shopState = shopState;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getShopJoinTime() {
        return shopJoinTime;
    }

    public void setShopJoinTime(String shopJoinTime) {
        this.shopJoinTime = shopJoinTime;
    }

    public String getRemainingUseTime() {
        return remainingUseTime;
    }

    public void setRemainingUseTime(String remainingUseTime) {
        this.remainingUseTime = remainingUseTime;
    }

    public Long getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(Long shopNumber) {
        this.shopNumber = shopNumber;
    }

    public String getShopQRCodeUrl() {
        return shopQRCodeUrl;
    }

    public void setShopQRCodeUrl(String shopQRCodeUrl) {
        this.shopQRCodeUrl = shopQRCodeUrl;
    }

    public String getShopAvatarUrl() {
        return shopAvatarUrl;
    }

    public void setShopAvatarUrl(String shopAvatarUrl) {
        this.shopAvatarUrl = shopAvatarUrl;
    }

    public String getShopAccountName() {
        return shopAccountName;
    }

    public void setShopAccountName(String shopAccountName) {
        this.shopAccountName = shopAccountName;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopBulletin() {
        return shopBulletin;
    }

    public void setShopBulletin(String shopBulletin) {
        this.shopBulletin = shopBulletin;
    }

    public String getShopIntroduction() {
        return shopIntroduction;
    }

    public void setShopIntroduction(String shopIntroduction) {
        this.shopIntroduction = shopIntroduction;
    }

    public Integer getShopState() {
        return shopState;
    }

    public void setShopState(Integer shopState) {
        this.shopState = shopState;
    }
}
