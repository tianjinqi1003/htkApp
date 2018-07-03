package com.htkapp.modules.admin.shopCategory.dto;

import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;

import java.util.List;

public class CategoryObj {

    private List<ShopCategory> takeoutCategory;

    private List<ShopCategory> groupCategory;

    public List<ShopCategory> getTakeoutCategory() {
        return takeoutCategory;
    }

    public void setTakeoutCategory(List<ShopCategory> takeoutCategory) {
        this.takeoutCategory = takeoutCategory;
    }

    public List<ShopCategory> getGroupCategory() {
        return groupCategory;
    }

    public void setGroupCategory(List<ShopCategory> groupCategory) {
        this.groupCategory = groupCategory;
    }
}
