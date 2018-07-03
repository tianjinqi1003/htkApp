package com.htkapp.modules.merchant.shop.entity;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺公告实体类
 */

public class ShopBulletin {

    private Integer id;  //主键

    private String content;  //公告内容

    private Integer shopId;  //店铺id


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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
