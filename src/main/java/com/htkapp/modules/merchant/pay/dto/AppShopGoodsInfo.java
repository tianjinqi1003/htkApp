package com.htkapp.modules.merchant.pay.dto;

import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;

import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 * app端获取外卖页面的数据封装类
 */

public class AppShopGoodsInfo {

    private Integer id;

    private String categoryName; //类别名字

    private List<TakeoutProduct> takeoutProductList;  //产品集合


    public Integer getId() {
        return id;
    }

    public AppShopGoodsInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public AppShopGoodsInfo setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public List<TakeoutProduct> getTakeoutProductList() {
        return takeoutProductList;
    }

    public AppShopGoodsInfo setTakeoutProductList(List<TakeoutProduct> takeoutProductList) {
        this.takeoutProductList = takeoutProductList;
        return this;
    }
}
