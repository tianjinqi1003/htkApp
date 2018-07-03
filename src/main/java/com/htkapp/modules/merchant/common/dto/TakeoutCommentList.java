package com.htkapp.modules.merchant.common.dto;


/**
 * jsp页面外卖评论列表返回结果封类
 */
public class TakeoutCommentList {

    private Double commentsStars; //评论星级

    private String content;  //评论内容

    private String orderNumber; //订单号　

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

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
