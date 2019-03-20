package com.htkapp.modules.merchant.shop.entity;

public class ShopDeliveryFee {

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getMinRadii() {
		return minRadii;
	}
	public void setMinRadii(Double minRadii) {
		this.minRadii = minRadii;
	}
	public Double getMaxRadii() {
		return maxRadii;
	}
	public void setMaxRadii(Double maxRadii) {
		this.maxRadii = maxRadii;
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
	private Double minRadii;
	private Double maxRadii;
	private Double deliveryFee;
	private Integer shopId;
}
