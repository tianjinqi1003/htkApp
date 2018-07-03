package com.htkapp.modules.merchant.shop.entity;

/**
 * Created by yinqilei on 17-7-4.
 * 商户登陆日志实体
 */
public class AccountShopLoginLog {

    private Integer id;

    private String loginDate;

    private String signOutDate;

    private Integer currentState;  //当前状态  0代表不在线  1代表在线

    private String accountShopToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getSignOutDate() {
        return signOutDate;
    }

    public void setSignOutDate(String signOutDate) {
        this.signOutDate = signOutDate;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }
}
