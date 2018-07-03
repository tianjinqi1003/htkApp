package com.htkapp.modules.common.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统登录用的
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String password;

    private String email;

    private String token;

    private String saltToken;

    private String encryptToken;

    private boolean pcLoginState;  //电脑网页登陆状态  true在线　　false离线

    private String role;

    private Integer accountStatus;

    private short loginWay;

    private String avatarImg; //头像url

    private String useStartTime;  //商户使用时间

    private String useEndTime;  //商户使用时间

    private String nickName; //呢称

    private boolean rememberMe;  //是否住当前登陆用户

    private String shopName; //店铺名字

    private int state;  //店铺状态
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;


    public LoginUser(LoginUser user) {
        super();
    }

    public LoginUser() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public LoginUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginUser setToken(String token) {
        this.token = token;
        return this;
    }

    public String getEncryptToken() {
        return encryptToken;
    }

    public LoginUser setEncryptToken(String encryptToken) {
        this.encryptToken = encryptToken;
        return this;
    }

    public String getRole() {
        return role;
    }

    public LoginUser setRole(String role) {
        this.role = role;
        return this;
    }

    public String getSaltToken() {
        return saltToken;
    }

    public LoginUser setSaltToken(String saltToken) {
        this.saltToken = saltToken;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public LoginUser setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public LoginUser setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public short getLoginWay() {
        return loginWay;
    }

    public LoginUser setLoginWay(short loginWay) {
        this.loginWay = loginWay;
        return this;
    }

    public boolean getPcLoginState() {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

//	@Override
//	public String toString() {
//		return "LoginUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
//				+ ", token=" + token + ", saltToken=" + saltToken + ", encryptToken=" + encryptToken + ", pcLoginState="
//				+ pcLoginState + ", role=" + role + ", accountStatus=" + accountStatus + ", loginWay=" + loginWay
//				+ ", avatarImg=" + avatarImg + ", useStartTime=" + useStartTime + ", useEndTime=" + useEndTime
//				+ ", nickName=" + nickName + ", rememberMe=" + rememberMe + ", shopName=" + shopName + ", state="
//				+ state + ", createTime=" + createTime + ", lastLoginTime=" + lastLoginTime + "]";
//	}


}
