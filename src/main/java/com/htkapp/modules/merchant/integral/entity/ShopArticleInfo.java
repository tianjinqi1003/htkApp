package com.htkapp.modules.merchant.integral.entity;


//平台资讯显示
public class ShopArticleInfo {
	

    private Integer id;

    private String title;

    private String imgUrl;

    private String describe_;  //说明

    private String htmlContent;

    private Integer shopId;

    private String createTime;  //创建时间

    private String detailRequestUrl = "http://120.27.5.36:8080" + "/htkApp/API/appMemberAPI/articleDetails";  //详情地址

    private int state;

    private String jsonStr;//json字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescribe_() {
        return describe_;
    }

    public void setDescribe_(String describe_) {
        this.describe_ = describe_;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetailRequestUrl() {
        return detailRequestUrl;
    }

    public void setDetailRequestUrl(String detailRequestUrl) {
        this.detailRequestUrl = detailRequestUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
