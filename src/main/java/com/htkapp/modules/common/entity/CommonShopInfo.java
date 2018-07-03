package com.htkapp.modules.common.entity;

public class CommonShopInfo {

    private Integer shopId; //店铺id

    private String shopName; //店铺名字

    private int state;  //店铺状态

    private String logoUrl;  //店铺logoUrl

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
