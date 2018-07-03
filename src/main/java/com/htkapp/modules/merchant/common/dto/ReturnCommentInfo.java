package com.htkapp.modules.merchant.common.dto;

/**
 * Created by yinqilei on 17-6-28.
 * 店铺外卖的用户评论封装类
 */

public class ReturnCommentInfo {

    private Integer accountId;  //用户id

    private String avaUrl;   //用户头像url

    private String nickName;   //用户呢称

    private Double commentsStars;  //评论星级

    private String commentTime; //评论时间

    private String content;  //评论内容

    private String accountToken;  //用户token标识

    private Integer commentId;  //评论id

    private String merchantReply ;

    public String getMerchantReply() {
        return merchantReply;
    }

    public void setMerchantReply(String merchantReply) {
        this.merchantReply = merchantReply;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getCommentsStars() {
        return commentsStars;
    }

    public void setCommentsStars(Double commentsStars) {
        this.commentsStars = commentsStars;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}
