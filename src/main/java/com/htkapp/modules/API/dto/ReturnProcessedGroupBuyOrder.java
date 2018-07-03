package com.htkapp.modules.API.dto;

/**
 * Created by yinqilei on 17-7-6.
 * 返回已处理的团购订单封装类
 */
public class ReturnProcessedGroupBuyOrder extends ProcessedOrderList {

    private String packageName; //套餐名

    private String logoUrl; //套餐图片

    private String phone; //手机号

    @Override
    public String getOrderNumber() {
        return super.getOrderNumber();
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        super.setOrderNumber(orderNumber);
    }

    @Override
    public String getOrderTime() {
        return super.getOrderTime();
    }

    @Override
    public void setOrderTime(String orderTime) {
        super.setOrderTime(orderTime);
    }

    @Override
    public Double getOrderAmount() {
        return super.getOrderAmount();
    }

    @Override
    public void setOrderAmount(Double orderAmount) {
        super.setOrderAmount(orderAmount);
    }

    @Override
    public Integer getOrderState() {
        return super.getOrderState();
    }

    @Override
    public void setOrderState(Integer orderState) {
        super.setOrderState(orderState);
    }

    @Override
    public Integer getMark() {
        return super.getMark();
    }

    @Override
    public void setMark(Integer mark) {
        super.setMark(mark);
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
