package com.htkapp.modules.merchant.shop.entity;

/**
 * 商铺
 */

public class Shop {

    private Integer shopId;   //主键

    private String shopName;   //店铺名字

    private String logoUrl;   //店铺图片url

    private String openingTime;  //营业时间

    private Double longitude;   //地址经度

    private Double latitude;   //地址纬度

    private String address;   //详情地址

    private String location;  //地址

    private String intro;  //简介

    private double score;  //评分数

    private Double perCapitaPrice;  //人均价格
    
    private Double startDeliveryPrice;//满多少元起送

	private int monthlySalesVolume;  //月售量

    private boolean collection;   //是否收藏

    private Double deliveryFee;  //配送费

    private String categoryName;  //所属类别名

    private String phone;  //电话

    private String mobilePhone;  //手机号

    private Integer state;   //状态  1:营业中  0:休息中

    private int mark;   //店铺类型   0代表外卖   1代表团购

    private Integer accountShopId;  //商户账号id

    private Integer shopCategoryId;   //商铺类别id

    private double withDistance;  //和用户的距离

    private String shopJoinTime;  //加入时间

    private String shopQrCodeUrl;  //店铺二维码url

    private Integer shopState;

    private String shopCategoryName;  //所属分类名

    private String des;  //店铺简介

    private String bufImg; //自助点餐二维码

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Integer getAccountShopId() {
        return accountShopId;
    }

    public void setAccountShopId(Integer accountShopId) {
        this.accountShopId = accountShopId;
    }

    public Integer getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Integer shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getMonthlySalesVolume() {
        return monthlySalesVolume;
    }

    public void setMonthlySalesVolume(int monthlySalesVolume) {
        this.monthlySalesVolume = monthlySalesVolume;
    }

    public boolean getCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public double getWithDistance() {
        return withDistance;
    }

    public void setWithDistance(double withDistance) {
        this.withDistance = withDistance;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Double getPerCapitaPrice() {
        return perCapitaPrice;
    }

    public void setPerCapitaPrice(Double perCapitaPrice) {
        this.perCapitaPrice = perCapitaPrice;
    }

    public Double getStartDeliveryPrice() {
		return startDeliveryPrice;
	}

	public void setStartDeliveryPrice(Double startDeliveryPrice) {
		this.startDeliveryPrice = startDeliveryPrice;
	}

    public String getShopJoinTime() {
        return shopJoinTime;
    }

    public void setShopJoinTime(String shopJoinTime) {
        this.shopJoinTime = shopJoinTime;
    }

    public String getShopQrCodeUrl() {
        return shopQrCodeUrl;
    }

    public void setShopQrCodeUrl(String shopQrCodeUrl) {
        this.shopQrCodeUrl = shopQrCodeUrl;
    }

    public Integer getShopState() {
        return shopState = state;
    }

    public void setShopState(Integer shopState) {
        this.shopState = shopState;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", intro='" + intro + '\'' +
                ", score=" + score +
                ", perCapitaPrice=" + perCapitaPrice +
                ", monthlySalesVolume=" + monthlySalesVolume +
                ", collection=" + collection +
                ", deliveryFee=" + deliveryFee +
                ", categoryName='" + categoryName + '\'' +
                ", phone='" + phone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", state=" + state +
                ", mark=" + mark +
                ", accountShopId=" + accountShopId +
                ", shopCategoryId=" + shopCategoryId +
                ", withDistance=" + withDistance +
                ", shopJoinTime='" + shopJoinTime + '\'' +
                ", shopQrCodeUrl='" + shopQrCodeUrl + '\'' +
                ", shopState=" + shopState +
                ", shopCategoryName='" + shopCategoryName + '\'' +
                ", des='" + des + '\'' +
                '}';
    }

    public String getBufImg() {
        return bufImg;
    }

    public void setBufImg(String bufImg) {
        this.bufImg = bufImg;
    }
}