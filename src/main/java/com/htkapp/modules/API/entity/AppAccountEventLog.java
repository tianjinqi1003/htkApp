package com.htkapp.modules.API.entity;

import java.util.Date;

/**
 * Created by yinqilei on 17-6-22.
 *
 */

public class AppAccountEventLog {

    private String token;  //用户登陆的身份标识

    private String eventTime;  //事件时间

    private short loginWay;  //app用户登陆方式

    private String content;  //记录内容

    public String getToken() {
        return token;
    }

    public AppAccountEventLog setToken(String token) {
        this.token = token;
        return this;
    }

    public String getEventTime() {
        return eventTime;
    }

    public AppAccountEventLog setEventTime(String eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public short getLoginWay() {
        return loginWay;
    }

    public AppAccountEventLog setLoginWay(short loginWay) {
        this.loginWay = loginWay;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AppAccountEventLog setContent(String content) {
        this.content = content;
        return this;
    }
}
