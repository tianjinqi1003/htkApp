package com.htkapp.modules.merchant.buffetFood.entity;

//自助点餐套餐内产品列表
public class BuffetFoodProductPackage {

    private Integer id;

    private String cImgUrl;  //套餐内产品地址

    private String cName;   //产品名

    private Integer cQuantity;  //产品数量

    private Double cPrice;   //产品价格

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getcQuantity() {
        return cQuantity;
    }

    public void setcQuantity(Integer cQuantity) {
        this.cQuantity = cQuantity;
    }

    public Double getcPrice() {
        return cPrice;
    }

    public void setcPrice(Double cPrice) {
        this.cPrice = cPrice;
    }

    public Integer getParentId() {
        return parentId == null ? 0 : parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getcImgUrl() {
        return cImgUrl;
    }

    public void setcImgUrl(String cImgUrl) {
        this.cImgUrl = cImgUrl;
    }
}
