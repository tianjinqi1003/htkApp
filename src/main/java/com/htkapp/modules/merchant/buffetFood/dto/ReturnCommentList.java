package com.htkapp.modules.merchant.buffetFood.dto;

public class ReturnCommentList {

    private Integer commentStart;  //评论星级

    private String commentContent;  //评论内容

    private String accountUserName;  //用户名

    private String commentTime;  //评论时间

    public Integer getCommentStart() {
        return commentStart;
    }

    public void setCommentStart(Integer commentStart) {
        this.commentStart = commentStart;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
