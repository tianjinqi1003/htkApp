package com.htkapp.modules.merchant.shop.dto;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.shop.entity.ShopBulletin;
import com.htkapp.modules.merchant.shop.entity.ShopConsumptionActivities;

import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 * app店铺信息展示页面封装类
 */
public class AppShowShopInfo {

    private String shopName;

    private String logoUrl;   //店铺图片url

    private Double deliveryFee;  //配送费

    private double score;  //评分数

    private Double longitude;   //地址经度

    private Double latitude;   //地址纬度

    private int monthlySalesVolume;  //月售量

    private String locationAddress;  //地址

    private String openingTime;  //营业时间

    private Integer state;  //外卖店铺状态0休息　１营业

    private String categoryName;  //所属类别名

    private ShopBulletin shopBulletin;   //店铺公告

    private Double perCapitaPrice;  //人均价格

    private String phone;  //商铺电话
    
    private String mobilePhone;  //手机号

	private boolean collection;  //是否已收藏

    private List<ShopConsumptionActivities> shopConsumptionActivities;  //店铺消费活动列表

    private List<BuyPackage> buyPackageList;  //套餐列表

    private BuyPackage buyPackage;   //套餐对象

    public String getShopName() {
        return shopName;
    }

    public AppShowShopInfo setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public AppShowShopInfo setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public AppShowShopInfo setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public ShopBulletin getShopBulletin() {
        return shopBulletin;
    }

    public AppShowShopInfo setShopBulletin(ShopBulletin shopBulletin) {
        this.shopBulletin = shopBulletin;
        return this;
    }

    public List<ShopConsumptionActivities> getShopConsumptionActivities() {
        return shopConsumptionActivities;
    }

    public AppShowShopInfo setShopConsumptionActivities(List<ShopConsumptionActivities> shopConsumptionActivities) {
        this.shopConsumptionActivities = shopConsumptionActivities;
        return this;
    }


    public double getScore() {
        return score;
    }

    public AppShowShopInfo setScore(double score) {
        this.score = score;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public AppShowShopInfo setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public AppShowShopInfo setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public int getMonthlySalesVolume() {
        return monthlySalesVolume;
    }

    public AppShowShopInfo setMonthlySalesVolume(int monthlySalesVolume) {
        this.monthlySalesVolume = monthlySalesVolume;
        return this;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public AppShowShopInfo setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public AppShowShopInfo setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public AppShowShopInfo setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public List<BuyPackage> getBuyPackageList() {
        return buyPackageList;
    }

    public AppShowShopInfo setBuyPackageList(List<BuyPackage> buyPackageList) {
        this.buyPackageList = buyPackageList;
        return this;
    }

    public Double getPerCapitaPrice() {
        return perCapitaPrice;
    }

    public AppShowShopInfo setPerCapitaPrice(Double perCapitaPrice) {
        this.perCapitaPrice = perCapitaPrice;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AppShowShopInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

    public boolean getCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public BuyPackage getBuyPackage() {
        return buyPackage;
    }

    public void setBuyPackage(BuyPackage buyPackage) {
        this.buyPackage = buyPackage;
    }

    public Integer getState() {
        return state;
    }

    public AppShowShopInfo setState(Integer state) {
        this.state = state;
        return this;
    }
}
