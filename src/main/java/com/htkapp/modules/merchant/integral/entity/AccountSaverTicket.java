package com.htkapp.modules.merchant.integral.entity;

//优惠券
public class AccountSaverTicket {

    private Integer id;

    private String tName;

    private String tExpiration;

    private String tStartTime;

    private String tUsePhone;

    private Double tMoney;

    private Double tUseMoney;

    private Integer integralValue;

    private Integer shopId;

    private String jsonStr;

    /**
     * @author 马鹏昊
     * @desc 保存此优惠券的数量
     */
    private int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettExpiration() {
        return tExpiration;
    }

    public void settExpiration(String tExpiration) {
        this.tExpiration = tExpiration;
    }

    public String gettUsePhone() {
        return tUsePhone;
    }

    public void settUsePhone(String tUsePhone) {
        this.tUsePhone = tUsePhone;
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

    public Integer getShopId() {
        return shopId;
    }

    public String gettStartTime() {
        return tStartTime;
    }

    public void settStartTime(String tStartTime) {
        this.tStartTime = tStartTime;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }
}
