package com.htkapp.modules.API.entity;

public class DaiQiangDan {
	
	private String qcShopName;
	public String getQcShopName() {
		return qcShopName;
	}
	public void setQcShopName(String qcShopName) {
		this.qcShopName = qcShopName;
	}
	public String getQcAddress() {
		return qcAddress;
	}
	public void setQcAddress(String qcAddress) {
		this.qcAddress = qcAddress;
	}
	public String getScAddress() {
		return scAddress;
	}
	public void setScAddress(String scAddress) {
		this.scAddress = scAddress;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	private String qcAddress;
	private String scAddress;
	private String orderTime;
	private String orderNumber;
	private Double qcLongitude;
	private Double qcLatitude;
	private Double scLongitude;
	private Double scLatitude;
	public Double getQcLongitude() {
		return qcLongitude;
	}
	public void setQcLongitude(Double qcLongitude) {
		this.qcLongitude = qcLongitude;
	}
	public Double getQcLatitude() {
		return qcLatitude;
	}
	public void setQcLatitude(Double qcLatitude) {
		this.qcLatitude = qcLatitude;
	}
	public Double getScLongitude() {
		return scLongitude;
	}
	public void setScLongitude(Double scLongitude) {
		this.scLongitude = scLongitude;
	}
	public Double getScLatitude() {
		return scLatitude;
	}
	public void setScLatitude(Double scLatitude) {
		this.scLatitude = scLatitude;
	}

}
