package com.htkapp.modules.merchant.common.dto;

/**
 * jsp页面团购列表返回实体封状类
 */

public class GroupBuyCommentList {

    private Double commentsStars; //评论星级

    private String content;  //评论内容

    private String orderNumber; //订单号　

    private String nickName; //用户称呢

    private String avatarUrl;  //用户头像

    private String commentTime; //评论时间


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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
