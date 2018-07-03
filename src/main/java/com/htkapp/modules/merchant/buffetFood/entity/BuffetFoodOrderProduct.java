package com.htkapp.modules.merchant.buffetFood.entity;

/**
 * 自助点餐订单商品详情表
 */
public class BuffetFoodOrderProduct {

    private Integer id;

    private Integer productId;  //产品id

    private String productName;  //产品名字

    private Integer quantity;  //数量

    private Double price;   //价格

    private String imgUrl;  //图片

    private Integer orderId;   //订单表主键id

    private Integer state;  //状态：是否新增  0否  1是

    private Integer categoryId;  //所属分类id

    private String categoryName;  //分类名
    
    private Integer bz;

    public Integer getBz() {
		return bz;
	}

	public void setBz(Integer bz) {
		this.bz = bz;
	}

	public Integer getId() {
        return id == null ? 0 : id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId == null ? 0 : orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getState() {
        return state == null ? 0 : state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCategoryId() {
        return categoryId == null ? 0 : categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getImgUrl() {
        return imgUrl == null ? "" : imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategoryName() {
        return categoryName == null ? "" : categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    @Override
	public String toString() {
		return "BuffetFoodOrderProduct [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", quantity=" + quantity + ", price=" + price + ", imgUrl=" + imgUrl + ", orderId=" + orderId
				+ ", state=" + state + ", categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		BuffetFoodOrderProduct other = (BuffetFoodOrderProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
