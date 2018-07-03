package com.htkapp.modules.merchant.integral.entity;

public class AccountUseTicketList {
	
    private Integer id;

    private String tName;

    private Integer ticketId;
    
    private String tExpiration;
    
    private String gmtCreate;

	private Integer quantity;

    private String usePhone;

    private Integer shopId;

    private String accountToken;

    private Double tMoney;

    private Double tUseMoney;

    private Integer integralValue;
    
    public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String gettExpiration() {
		return tExpiration;
	}

	public void settExpiration(String tExpiration) {
		this.tExpiration = tExpiration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUsePhone() {
		return usePhone;
	}

	public void setUsePhone(String usePhone) {
		this.usePhone = usePhone;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public Double gettMoney() {
		return tMoney;
	}

	public void settMoney(Double tMoney) {
		this.tMoney = tMoney;
	}

	public Double gettUseMoney() {
		return tUseMoney;
	}

	public void settUseMoney(Double tUseMoney) {
		this.tUseMoney = tUseMoney;
	}

	public Integer getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Integer integralValue) {
		this.integralValue = integralValue;
	}

	@Override
	public String toString() {
		return "AccountUseTicketList [id=" + id + ", tName=" + tName + ", ticketId=" + ticketId + ", tExpiration="
				+ tExpiration + ", gmtCreate=" + gmtCreate + ", quantity=" + quantity + ", usePhone=" + usePhone
				+ ", shopId=" + shopId + ", accountToken=" + accountToken + ", tMoney=" + tMoney + ", tUseMoney="
				+ tUseMoney + ", integralValue=" + integralValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountToken == null) ? 0 : accountToken.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
		result = prime * result + ((usePhone == null) ? 0 : usePhone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountUseTicketList other = (AccountUseTicketList) obj;
		if (accountToken == null) {
			if (other.accountToken != null)
				return false;
		} else if (!accountToken.equals(other.accountToken))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ticketId == null) {
			if (other.ticketId != null)
				return false;
		} else if (!ticketId.equals(other.ticketId))
			return false;
		if (usePhone == null) {
			if (other.usePhone != null)
				return false;
		} else if (!usePhone.equals(other.usePhone))
			return false;
		return true;
	}

	
}
