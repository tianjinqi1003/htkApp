package com.htkapp.modules.merchant.groupBuy.service;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;

import java.util.List;

public interface BuyPackageContentService {

    /* ==========================接口开始================================ */
    //插入套餐详情内容(套餐内商品)
    void insertPackageItem(BuyPackageContent buyPackageContent);
    //通过packageId 获取套餐内商品
    List<BuyPackageContent> getPackageItemListById(int packageId);
    //通过packageId删除套餐内商品
    void deletePackageContentListById(int packageId);
    /* ==========================接口结束============================== */
}
