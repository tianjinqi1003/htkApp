package com.htkapp.modules.API.entity;

public class DaiSongDa {

	private String qhAddress;
    private String price;
    private String accountToken;
    public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

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
    public Double getQhLongitude() {
		return qhLongitude;
	}

	public void setQhLongitude(Double qhLongitude) {
		this.qhLongitude = qhLongitude;
	}

	public Double getQhLatitude() {
		return qhLatitude;
	}

	public void setQhLatitude(Double qhLatitude) {
		this.qhLatitude = qhLatitude;
	}

	public Double getShLongitude() {
		return shLongitude;
	}

	public void setShLongitude(Double shLongitude) {
		this.shLongitude = shLongitude;
	}

	public Double getShLatitude() {
		return shLatitude;
	}

	public void setShLatitude(Double shLatitude) {
		this.shLatitude = shLatitude;
	}

	private Double qhLongitude;
	private Double qhLatitude;
	private Double shLongitude;
	private Double shLatitude;

}
