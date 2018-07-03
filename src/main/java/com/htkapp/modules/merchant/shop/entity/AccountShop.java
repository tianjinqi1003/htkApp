package com.htkapp.modules.merchant.shop.entity;

public class AccountShop {

    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private Integer id;

    private String userName;

    private String password;

    private String nickName;

    private String email;

    private Integer sex;  //性别

    private String phone;

    private String registerTime;

    private String token;

    private String saltToken;

    private String encryptToken;

    private Integer accountStatus;

    private Integer pcLoginState;

    private boolean appLoginState;

    private String avatarImg;

    private String useStartTime;  //会员使用开始时间

    private String useEndTime;  //会员使用结束时间

    private String alipayAccount; //支付宝提现账号

    private String alipayAccountType;  //支付宝提现账号类型

    private String shopListStr;   //商铺列表字符串

    private String roleStr;  //权限

    private String identity;

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
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getPcLoginState() {
        return pcLoginState;
    }

    public void setPcLoginState(Integer pcLoginState) {
        this.pcLoginState = pcLoginState;
    }

    public boolean getAppLoginState() {
        return appLoginState;
    }

    public void setAppLoginState(boolean appLoginState) {
        this.appLoginState = appLoginState;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(String useStartTime) {
        this.useStartTime = useStartTime;
    }

    public String getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(String useEndTime) {
        this.useEndTime = useEndTime;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getAlipayAccountType() {
        return alipayAccountType;
    }

    public void setAlipayAccountType(String alipayAccountType) {
        this.alipayAccountType = alipayAccountType;
    }

    @Override
    public String toString() {
        return "AccountShop{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", token='" + token + '\'' +
                ", saltToken='" + saltToken + '\'' +
                ", encryptToken='" + encryptToken + '\'' +
                ", accountStatus=" + accountStatus +
                ", pcLoginState=" + pcLoginState +
                ", appLoginState=" + appLoginState +
                ", avatarImg='" + avatarImg + '\'' +
                ", useStartTime='" + useStartTime + '\'' +
                ", useEndTime='" + useEndTime + '\'' +
                ", alipayAccount='" + alipayAccount + '\'' +
                ", alipayAccountType='" + alipayAccountType + '\'' +
                '}';
    }

    public String getShopListStr() {
        return shopListStr;
    }

    public void setShopListStr(String shopListStr) {
        this.shopListStr = shopListStr;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}