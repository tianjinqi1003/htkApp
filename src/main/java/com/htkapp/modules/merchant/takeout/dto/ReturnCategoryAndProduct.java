package com.htkapp.modules.merchant.takeout.dto;

import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;

import java.util.List;

//返回分类列表和商品
public class ReturnCategoryAndProduct {

    private String categoryName; //类别名字

    private String description;  //说明

    private Integer categoryId; //分类id

    private List<TakeoutProduct> takeoutProductList;   //产品集合

    public String getCategoryName() {
        return categoryName;
    }

    public ReturnCategoryAndProduct(String categoryName, String description,Integer categoryId, List<TakeoutProduct> productList) {
        this.categoryName = categoryName;
        this.description = description;
        this.categoryId = categoryId;
        this.takeoutProductList = productList;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TakeoutProduct> getTakeoutProductList() {
        return takeoutProductList;
    }

    public void setTakeoutProductList(List<TakeoutProduct> takeoutProductList) {
        this.takeoutProductList = takeoutProductList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
