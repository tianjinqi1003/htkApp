package com.htkapp.modules.merchant.integral.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 积分表实体
 */

public class Integral {

    private Integer id;

    private Integer val;  //积分值

    private Integer shopId;   //商铺ID

    private String accountToken;  //用户token

    private String joinTime;  //创建时间

    private String userPhone;  //用户手机号

    private String lastConsumeTime;  //最新积分消费时间

    private String lastGetTime;  //最新积分获得时间

    public String getLastConsumeTime() {
        return lastConsumeTime;
    }

    public void setLastConsumeTime(String lastConsumeTime) {
        this.lastConsumeTime = lastConsumeTime;
    }

    public String getLastGetTime() {
        return lastGetTime;
    }

    public void setLastGetTime(String lastGetTime) {
        this.lastGetTime = lastGetTime;
    }

    private Integer flag;  //状态标记: 1可见，已关注或加入店铺会员　　0: 之前关注过，取消关注后积分记录保存，但状态为0不可见

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
