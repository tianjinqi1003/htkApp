package com.htkapp.modules.merchant.buffetFood.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;

import java.util.List;

public class ReturnCategoryAndProductList {

    private String categoryName; //类别名字

    private Integer categoryId; //分类id

    private List<BuffetFoodProduct> buffetFoodProductList;   //产品集合

    public ReturnCategoryAndProductList(String categoryName,Integer categoryId, List<BuffetFoodProduct> productList) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.buffetFoodProductList = productList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<BuffetFoodProduct> getBuffetFoodProductList() {
        return buffetFoodProductList;
    }

    public void setBuffetFoodProductList(List<BuffetFoodProduct> buffetFoodProductList) {
        this.buffetFoodProductList = buffetFoodProductList;
    }
}
