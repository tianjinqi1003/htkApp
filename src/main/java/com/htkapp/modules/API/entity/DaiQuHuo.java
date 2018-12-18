package com.htkapp.modules.API.entity;

public class DaiQuHuo {
	
	private String qhAddress;
    private String price;
    public String getQhAddress() {
        return qhAddress;
    }

    public void setQhAddress(String qhAddress) {
        this.qhAddress = qhAddress;
    }

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

    public String getShAddress() {
        return shAddress;
    }

    public void setShAddress(String shAddress) {
        this.shAddress = shAddress;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    private String shAddress;
    private String orderNumber;

}
