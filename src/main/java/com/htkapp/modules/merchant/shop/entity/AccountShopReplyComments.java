package com.htkapp.modules.merchant.shop.entity;

/**
 * 商户回复评论表
 */

public class AccountShopReplyComments {

    private Integer id;

    private String replyContent;  //回复内容

    private Integer commentId;  //用户评论表id

    private String accountShopToken;  //商户token

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }
}
