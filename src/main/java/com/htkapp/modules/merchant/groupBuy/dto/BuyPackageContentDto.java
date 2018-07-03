package com.htkapp.modules.merchant.groupBuy.dto;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;

import java.util.List;

public class BuyPackageContentDto {

    private List<BuyPackageContent> list;

    public List<BuyPackageContent> getList() {
        return list;
    }

    public void setList(List<BuyPackageContent> list) {
        this.list = list;
    }
}
