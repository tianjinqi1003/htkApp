package com.htkapp.modules.merchant.pay.dto;

/**
 * Created by yinqilei on 17-6-23.
 * 用户信息类
 */
public class AppAccountInfo {

    private String token;  //token

    private Short loginWay;  //登陆方式

    private String nickName;  //称呢

    private String phone;  //手机号

    private boolean passwordStatus;  //是否修改过密码

    private String avaUrl;

    private boolean qqStatus;

    private boolean weChatStatus;

    public String getToken() {
        return token;
    }

    public AppAccountInfo setToken(String token) {
        this.token = token;
        return this;
    }

    public Short getLoginWay() {
        return loginWay;
    }

    public AppAccountInfo setLoginWay(Short loginWay) {
        this.loginWay = loginWay;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public AppAccountInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AppAccountInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean getPasswordStatus() {
        return passwordStatus;
    }

    public AppAccountInfo setPasswordStatus(boolean passwordStatus) {
        this.passwordStatus = passwordStatus;
        return this;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public AppAccountInfo setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
        return this;
    }

    public boolean getQqStatus() {
        return qqStatus;
    }

    public AppAccountInfo setQqStatus(boolean qqStatus) {
        this.qqStatus = qqStatus;
        return this;
    }

    public boolean getWeChatStatus() {
        return weChatStatus;
    }

    public AppAccountInfo setWeChatStatus(boolean weChatStatus) {
        this.weChatStatus = weChatStatus;
        return this;
    }
}
