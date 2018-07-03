package com.htkapp.modules.merchant.common.entity;

/**
 * 外卖店铺评论实体类
 */

public class ShopMessageComment {

    private Integer id;

    private Double commentsStars;  //评论星级

    private String content;  //评论内容

    private Integer shopId;  //店铺id

    private boolean isComment;  //是否评论

    private String accountToken;  //用户token

    private String orderNumber;  //订单号

    private String gmtCreate; //评论时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCommentsStars() {
        return commentsStars;
    }

    public void setCommentsStars(Double commentsStars) {
        this.commentsStars = commentsStars;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public boolean getComment() {
        return isComment;
    }

    public void setComment(boolean comment) {
        isComment = comment;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}