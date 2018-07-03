package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.shop.entity.Shop;

import java.util.List;

/**
 * 返回分类下店铺数据,
 * 如果店铺数理小于或等于3条,则推荐店铺,否则不推荐
 */
public class RecommendedShopInfo {

    private List<Shop> shopList;  //分类下的店铺列表

    private List<Shop> recommendShopList;  //推荐的分类列表


    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    public List<Shop> getRecommendShopList() {
        return recommendShopList;
    }

    public void setRecommendShopList(List<Shop> recommendShopList) {
        this.recommendShopList = recommendShopList;
    }
}
