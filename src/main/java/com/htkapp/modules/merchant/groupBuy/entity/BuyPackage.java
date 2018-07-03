package com.htkapp.modules.merchant.groupBuy.entity;

import java.util.List;

/**
 * Created by yinqilei on 17-6-30.
 * 团购套餐实体类
 */

public class BuyPackage {

    private Integer id;

    private String packageName;  //套餐名

    private String imgUrl;   //套餐图片地址

    private String usageTime;  //使用时间

    private boolean reservation;  //是否需要预约

    private Double price;   //价格

    private Double retailPrice;  //门市价格

    private Integer soldQuantity;  //数量

    private Double score; //套餐总订单计算出的评分数

    private String useDetails;  //使用详情

    private String useRules;  //使用规则

    private Integer peopleUsedNumber;  //使用人数

    private Integer shopId;  //店铺id

    private String validityTime;  //有效时间

    private List<BuyPackageContent> contentList;

    private String jsonStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getUseDetails() {
        return useDetails;
    }

    public void setUseDetails(String useDetails) {
        this.useDetails = useDetails;
    }

    public String getUseRules() {
        return useRules;
    }

    public void setUseRules(String useRules) {
        this.useRules = useRules;
    }

    public Integer getPeopleUsedNumber() {
        return peopleUsedNumber;
    }

    public void setPeopleUsedNumber(Integer peopleUsedNumber) {
        this.peopleUsedNumber = peopleUsedNumber;
    }

    public Double getScore() {
        return score = Double.valueOf(3);
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(String validityTime) {
        this.validityTime = validityTime;
    }

    public List<BuyPackageContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<BuyPackageContent> contentList) {
        this.contentList = contentList;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
