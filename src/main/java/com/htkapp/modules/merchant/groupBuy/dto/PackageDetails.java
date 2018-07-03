package com.htkapp.modules.merchant.groupBuy.dto;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;

import java.util.List;

/**
 * Created by yinqilei on 17-7-1.
 */
public class PackageDetails {

    private String shopName;

    private Double longitude;   //地址经度

    private Double latitude;   //地址纬度

    private String locationAddress;  //地址

    private Integer commentCount;  //评论数

    private String phone; //商铺电话

    private BuyPackage buyPackage;  //套餐详情对象

    private List<BuyPackageContent> buyPackageContentList;  //套餐内商品

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public BuyPackage getBuyPackage() {
        return buyPackage;
    }

    public void setBuyPackage(BuyPackage buyPackage) {
        this.buyPackage = buyPackage;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BuyPackageContent> getBuyPackageContentList() {
        return buyPackageContentList;
    }

    public void setBuyPackageContentList(List<BuyPackageContent> buyPackageContentList) {
        this.buyPackageContentList = buyPackageContentList;
    }
}
