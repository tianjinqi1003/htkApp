package com.htkapp.modules.merchant.shop.entity;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺消费活动实体类
 */

public class ShopConsumptionActivities {

    private Integer id;

    private String content;  //活动内容

    private Integer shopId;  //商铺id


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
