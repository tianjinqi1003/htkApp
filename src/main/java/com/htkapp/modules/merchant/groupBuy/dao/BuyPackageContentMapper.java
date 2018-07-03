package com.htkapp.modules.merchant.groupBuy.dao;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;

import java.util.List;

public interface BuyPackageContentMapper {

    /* ==========================接口开始================================ */
    //插入套餐详情内容(套餐内商品)
    int insertPackageItemDAO(BuyPackageContent buyPackageContent);
    //通过packageId 获取套餐内商品
    List<BuyPackageContent> getPackageItemListByIdDAO(int packageId);
    //通过packageId删除套餐内商品
    int deletePackageContentListByIdDAO(int packageId);
    /* ==========================接口结束============================== */
}
