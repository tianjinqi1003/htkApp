package com.htkapp.modules.merchant.common.entity;

/**
 * Created by yinqilei on 17-6-29.
 * 广告列表
 */

public class AdvertisingInformation {

    private Integer id;

    private String imgUrl;  //广告图片url

    private String adName;  //广告名字

    private Integer shopId;  //商铺id


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

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
