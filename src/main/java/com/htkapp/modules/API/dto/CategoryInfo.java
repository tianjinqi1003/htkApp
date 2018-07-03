package com.htkapp.modules.API.dto;

import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.merchant.shop.entity.Shop;

import java.util.List;

public class CategoryInfo {

    private List<ShopCategory> childCategoryList;

    private List<Shop> shopList;

    public List<ShopCategory> getChildCategoryList() {
        return childCategoryList;
    }

    public void setChildCategoryList(List<ShopCategory> childCategoryList) {
        this.childCategoryList = childCategoryList;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
