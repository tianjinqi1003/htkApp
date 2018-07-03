package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;

import java.util.List;

//自助点餐－已点列表
public class ReturnHaveSomeList {

    private String categoryName;  //分类名字

    private Integer categoryId;  //分类id

    private List<BuffetFoodOrderProduct> orderProductList;

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

    public List<BuffetFoodOrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<BuffetFoodOrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
