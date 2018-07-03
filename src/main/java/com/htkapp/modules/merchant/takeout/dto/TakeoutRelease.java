package com.htkapp.modules.merchant.takeout.dto;

import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;

import java.util.List;

public class TakeoutRelease {

    private List<TakeoutCategory> categoryList;

    private List<TakeoutProduct> productList;

    private TakeoutCategory takeoutCategory;

    private int productSize; //产品数量

    public List<TakeoutCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<TakeoutCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<TakeoutProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<TakeoutProduct> productList) {
        this.productList = productList;
    }

    public TakeoutCategory getTakeoutCategory() {
        return takeoutCategory;
    }

    public void setTakeoutCategory(TakeoutCategory takeoutCategory) {
        this.takeoutCategory = takeoutCategory;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }
}
