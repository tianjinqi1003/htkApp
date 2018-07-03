package com.htkapp.modules.merchant.common.entity;

public class AccountShopNotice {

    private int id;

    private String title;

    private String msgCategory;

    private String msgContent;

    private int msgStatus;

    private String accountShopToken;

    private String gmtCreate;

//    public AccountShopNotice(String title, String msgCategory, String msgContent, String accountShopToken) {
//        this.title = title;
//        this.msgCategory = msgCategory;
//        this.msgContent = msgContent;
//        this.accountShopToken = accountShopToken;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgCategory() {
        return msgCategory;
    }

    public void setMsgCategory(String msgCategory) {
        this.msgCategory = msgCategory;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(int msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
