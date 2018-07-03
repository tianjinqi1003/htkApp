package com.htkapp.modules.merchant.groupBuy.service;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;

import java.util.List;

/**
 * Created by yinqilei on 17-6-30.
 */
public interface BuyPackageService {

    /* ==================接口开始=========================== */
    //获得团购店铺下的套餐列表
    List<BuyPackage> getGroupBuyListById(Integer shopId) throws Exception;
    //通过套餐id查出套餐详情和店铺id
    BuyPackage getPackageInformation(int packageId) throws Exception;

    /* ==================接口结束=========================== */



    /* ===================JSP页面接口开始====================== */
    //通过商户id查询团购产品列表
    List<BuyPackage> getGroupBuyListByAccountShopId(int accountShopId) throws Exception;
    //插入团购套餐
    int insertBuyPackage(BuyPackage buyPackage);
    //通过套餐id删除商品
    void deleteBuyPackageById(int packageId, int shopId);
    //更改保存商品
    void updateBuyPackageById(BuyPackage buyPackage);
    /* ==================JSP页面接口结束======================= */
}
