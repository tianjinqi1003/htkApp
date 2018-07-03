package com.htkapp.modules.merchant.groupBuy.dao;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;

import java.util.List;

/**
 * Created by yinqilei on 17-6-30.
 */
public interface BuyPackageMapper {

    /* ==============接口开始================== */
    //获得团购店铺下的套餐列表
    List<BuyPackage> getGroupBuyListByIdDAO(Integer shopId);
    //通过套餐id查出套餐详情和店铺id
    BuyPackage getPackageInformationDAO(int packageId);

    /* ==============接口结束================== */


    /* =================JSP页面接口开始==================== */
    //通过商户id查询团购产品列表
    List<BuyPackage> getGroupBuyListByAccountShopIdDAO(int accountShopId);
    //插入团购套餐
    int insertBuyPackageDAO(BuyPackage buyPackage);
    //通过套餐id删除商品
    int deleteBuyPackageByIdDAO(int packageId, int shopId);
    //更改保存商品
    int updateBuyPackageByIdDAO(BuyPackage buyPackage);
    /* =================JSP页面接口结束====================== */
}
