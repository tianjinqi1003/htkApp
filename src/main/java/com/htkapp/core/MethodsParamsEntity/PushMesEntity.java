package com.htkapp.core.MethodsParamsEntity;

public class PushMesEntity {

    private String nTitle;  //通知标题

    private String nMsgCategory;  //通知类别

    private String nMsgContent;  //通知内容

    private String nToken;  //通知人token

    private char classifyId;  //页面消息类别

    private int statusCode;  //页面消息状态码

    private String message;  //页面消息内容

    private int accountShopId;  //页面消息接收人id

    public PushMesEntity(){
        super();
    }

    public PushMesEntity(String nTitle, String nMsgCategory, String nMsgContent, String nToken, char classifyId, int statusCode, String message, int accountShopId) {
        this.nTitle = nTitle;
        this.nMsgCategory = nMsgCategory;
        this.nMsgContent = nMsgContent;
        this.nToken = nToken;
        this.classifyId = classifyId;
        this.statusCode = statusCode;
        this.message = message;
        this.accountShopId = accountShopId;
    }

    public String getNTitle() {
        return nTitle;
    }

    public void setNTitle(String nTitle) {
        this.nTitle = nTitle;
    }

    public String getNMsgCategory() {
        return nMsgCategory;
    }

    public void setNMsgCategory(String nMsgCategory) {
        this.nMsgCategory = nMsgCategory;
    }

    public String getNMsgContent() {
        return nMsgContent;
    }

    public void setNMsgContent(String nMsgContent) {
        this.nMsgContent = nMsgContent;
    }

    public String getNToken() {
        return nToken;
    }

    public void setNToken(String nToken) {
        this.nToken = nToken;
    }

    public char getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(char classifyId) {
        this.classifyId = classifyId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAccountShopId() {
        return accountShopId;
    }

    public void setAccountShopId(int accountShopId) {
        this.accountShopId = accountShopId;
    }
}
