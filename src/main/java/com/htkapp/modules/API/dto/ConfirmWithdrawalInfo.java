package com.htkapp.modules.API.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;

import java.util.List;

//用户请求退菜，封装查询出来数据推送给商家
public class ConfirmWithdrawalInfo {

    private String seatName;  //订座号

    private String paymentMethod;  //支付方式

    private List<BuffetFoodOrderProduct> productList;  //产品集合

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<BuffetFoodOrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<BuffetFoodOrderProduct> productList) {
        this.productList = productList;
    }
}
