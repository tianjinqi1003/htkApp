package com.htkapp.modules.merchant.integral.entity;

import java.sql.Timestamp;

/**
 * Created by yinqilei on 17-5-18.
 * 积分管理　－－　赠送积分　抵扣积分　　记录表实体类
 */
public class IntegralManageRecord {

    private Integer tradeId;  //主键ID

    private String tradeDescription; //交易说明

    private String sponsor;   //发起人标识

    private String passive;  //被动者标识

    private Integer integralValue;  //积分值

    private Integer state;   //状态表示:  -1:无效  0:成功

    private String gmt_create;

    private String gmt_modified;

    public IntegralManageRecord(String tradeDescription, String sponsor, String passive, Integer integralValue) {
        this.tradeDescription = tradeDescription;
        this.sponsor = sponsor;
        this.passive = passive;
        this.integralValue = integralValue;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeDescription() {
        return tradeDescription;
    }

    public void setTradeDescription(String tradeDescription) {
        this.tradeDescription = tradeDescription;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getPassive() {
        return passive;
    }

    public void setPassive(String passive) {
        this.passive = passive;
    }

    public Integer getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Integer integralValue) {
        this.integralValue = integralValue;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }

    public String getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(String gmt_modified) {
        this.gmt_modified = gmt_modified;
    }
}
