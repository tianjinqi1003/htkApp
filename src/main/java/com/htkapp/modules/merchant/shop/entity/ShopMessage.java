package com.htkapp.modules.merchant.shop.entity;

/**
 * Created by terabithia on 11/30/17.
 */
public class ShopMessage {

    private Integer id;

    private Double score;

    private Integer monthlySalesVolume;

    private Double perCapitaPrice;

    private Double deliveryFee;

    private Integer shopId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getMonthlySalesVolume() {
        return monthlySalesVolume;
    }

    public void setMonthlySalesVolume(Integer monthlySalesVolume) {
        this.monthlySalesVolume = monthlySalesVolume;
    }

    public Double getPerCapitaPrice() {
        return perCapitaPrice;
    }

    public void setPerCapitaPrice(Double perCapitaPrice) {
        this.perCapitaPrice = perCapitaPrice;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
