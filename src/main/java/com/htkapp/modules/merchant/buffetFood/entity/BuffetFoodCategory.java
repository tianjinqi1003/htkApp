package com.htkapp.modules.merchant.buffetFood.entity;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 * 自助点餐菜品类别实体
 */

public class BuffetFoodCategory {

    private Integer id;

    private String categoryName;  //类别名字

    private Integer shopId;  //商铺id

    private List<BuffetFoodProduct> productList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public List<BuffetFoodProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<BuffetFoodProduct> productList) {
        this.productList = productList;
    }
}
