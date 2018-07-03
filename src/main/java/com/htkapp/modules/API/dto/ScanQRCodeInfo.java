package com.htkapp.modules.API.dto;

public class ScanQRCodeInfo {

    private Integer shopId;

    private boolean result;

    public ScanQRCodeInfo(Integer shopId, boolean result) {
        this.shopId = shopId;
        this.result = result;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
