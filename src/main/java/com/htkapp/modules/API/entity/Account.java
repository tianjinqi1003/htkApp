package com.htkapp.modules.API.entity;

public class Account {
    private Integer accountId;  //主键

    private String userName;   //用户名

    private String password;  //密码

    private String email;    //邮箱

    private String nickName;  //呢称

    private String avatarUrl;  //头像url地址

    private String phone;   //手机号

    private Integer sex;  //性别

    private String registerTime;   //注册时间

    private String token;   //Token

    private String saltToken;   //加密值

    private String encryptToken;  //MD5加密后的加密值
    
    private Integer accountStatus;   //用户状态  0：未激活   1：已激活   9：禁用

    private boolean changePassword;  //是否修改过密码

    private Short loginWay;  //登陆方式

    private boolean changeNickName;  //更改呢称

    private String accountLoginWayQq; //qq登陆

    private String accountLoginWayWechat; //微信登陆

    public Integer getAccountId() {
        return accountId;
    }

    public Account setAccountId(Integer accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Account setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Account setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public Account setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Account setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Account setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public Account setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Account setToken(String token) {
        this.token = (token == null ? null : token.trim());
        return this;
    }

    public String getSaltToken() {
        return saltToken;
    }

    public Account setSaltToken(String saltToken) {
        this.saltToken = (saltToken == null ? null : saltToken.trim());
        return this;
    }

    public String getEncryptToken() {
        return encryptToken;
    }

    public Account setEncryptToken(String encryptToken) {
        this.encryptToken = (encryptToken == null ? null : encryptToken.trim());
        return this;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public Account setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public boolean getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public Short getLoginWay() {
        return loginWay;
    }

    public void setLoginWay(Short loginWay) {
        this.loginWay = loginWay;
    }

    public boolean getChangeNickName() {
        return changeNickName;
    }

    public void setChangeNickName(boolean changeNickName) {
        this.changeNickName = changeNickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAccountLoginWayQq() {
        return accountLoginWayQq;
    }

    public void setAccountLoginWayQq(String accountLoginWayQq) {
        this.accountLoginWayQq = accountLoginWayQq;
    }

    public String getAccountLoginWayWechat() {
        return accountLoginWayWechat;
    }

    public void setAccountLoginWayWechat(String accountLoginWayWechat) {
        this.accountLoginWayWechat = accountLoginWayWechat;
    }
}