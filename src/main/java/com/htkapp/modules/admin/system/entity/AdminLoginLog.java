package com.htkapp.modules.admin.system.entity;

/**
 * Created by yinqilei on 17-7-8.
 * 管理员账号登陆日志实体类
 */

public class AdminLoginLog {

    private Integer id;

    private String loginDate;

    private String signOutDate;

    private Integer currentState;  //当前状态  0代表不在线  1代表在线

    private String adminToken;  //管理员token


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

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }
}
