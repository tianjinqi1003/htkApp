package com.htkapp.modules.merchant.integral.dto;

/**
 * 用户积分列表数据封装实体类
 */

public class AccountIntegralList {

    private String userName;

    private Integer val;

    private String joinTime;  //加入时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
}
