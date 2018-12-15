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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	private String qcAddress;
	private String scAddress;
	private String orderNumber;

}
