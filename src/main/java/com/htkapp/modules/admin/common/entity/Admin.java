package com.htkapp.modules.admin.common.entity;

import java.io.Serializable;

/**
 * 管理员用户实体类
 */

public class Admin implements Serializable{

    /**
     * id, user_name, password, email, sex, register_time, token, salt_token,
     encrypt_token, account_status, pc_login_state, avatar_img
     */

    private Integer id;

    private String userName;

    private String password;

    private String email;

    private Integer sex;

    private String registerTime;  //注册时间

    private String token;   //账号token

    private String saltToken;  //MD5加密混淆值

    private String encryptToken;  //MD5加混淆值加密后的数据

    private Integer accountStatus;  //账号状态

    private boolean pcLoginState;  //账号登陆状态

    private String avatarImg;    //头像

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSaltToken() {
        return saltToken;
    }

    public void setSaltToken(String saltToken) {
        this.saltToken = saltToken;
    }

    public String getEncryptToken() {
        return encryptToken;
    }

    public void setEncryptToken(String encryptToken) {
        this.encryptToken = encryptToken;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isPcLoginState() {
        return pcLoginState;
    }

    public void setPcLoginState(boolean pcLoginState) {
        this.pcLoginState = pcLoginState;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }
}