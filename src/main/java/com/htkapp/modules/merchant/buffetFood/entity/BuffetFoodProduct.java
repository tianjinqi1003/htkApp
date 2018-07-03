package com.htkapp.modules.merchant.buffetFood.entity;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 * 自助点餐菜品实体
 */
public class BuffetFoodProduct {

    private Integer mark;

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    private Integer id;

    private String productName;  //产品名字

    private Double price;   //价格

    private String imgUrl;  //图片url

    private Integer monthlySalesVolume = 100;  //月售量

    private Integer categoryId;   //类别id

    private String productDetail;  //商品详情

    private Integer grade = 4;  //评分

    private Integer shopId;  //店铺id

    private Integer integral; //积分

    private String description;  //描述

    private int collectState;  //收藏状态

    private String categoryName; //分类名

    private List<BuffetFoodProductPackage> pgProductList;   //套餐内产品
    
    private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getMonthlySalesVolume() {
        return monthlySalesVolume;
    }

    public void setMonthlySalesVolume(Integer monthlySalesVolume) {
        this.monthlySalesVolume = monthlySalesVolume;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public int getCollectState() {
        return collectState;
    }

    public void setCollectState(int collectState) {
        this.collectState = collectState;
    }

    public List<BuffetFoodProductPackage> getPgProductList() {
        return pgProductList;
    }

    public void setPgProductList(List<BuffetFoodProductPackage> pgProductList) {
        this.pgProductList = pgProductList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
