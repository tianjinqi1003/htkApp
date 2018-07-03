package com.htkapp.modules.merchant.shop.entity;

/**
 * Created by terabithia on 11/19/17.
 * 相册
 */
public class ShopAlbum {

    private Integer id;

    private String imgUrl; //图片地址

    private Integer shopId; //商铺id

    private Integer flag;  //标识

    private String accountShopToken;  //商户token

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }
}
