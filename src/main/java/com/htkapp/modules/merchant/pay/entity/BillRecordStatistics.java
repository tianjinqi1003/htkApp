package com.htkapp.modules.merchant.pay.entity;

//账单记录统计表
public class BillRecordStatistics {

    private Integer id;

    private Integer status;

    private Double orderIncome;

    private Double spendingOnOrder;

    private Double amount;

    private String accountShopToken;

    private String gmtCreate;

    private String gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getOrderIncome() {
        return orderIncome;
    }

    public void setOrderIncome(Double orderIncome) {
        this.orderIncome = orderIncome;
    }

    public Double getSpendingOnOrder() {
        return spendingOnOrder;
    }

    public void setSpendingOnOrder(Double spendingOnOrder) {
        this.spendingOnOrder = spendingOnOrder;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAccountShopToken() {
        return accountShopToken;
    }

    public void setAccountShopToken(String accountShopToken) {
        this.accountShopToken = accountShopToken;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
