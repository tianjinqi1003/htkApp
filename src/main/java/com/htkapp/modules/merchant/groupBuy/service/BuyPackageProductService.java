package com.htkapp.modules.merchant.groupBuy.service;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageProduct;

import java.util.List;

public interface BuyPackageProductService {

    /* =========================接口开始============================== */
    //查找加入团购的商品列表
    List<BuyPackageProduct> getBuyPackageProductListByShopId(int shopId, int pageNumber, int pageLimit);
    //插入添加的团购商品
    void insertBuyPackageProductByShopId(BuyPackageProduct buyPackageProduct);
    /* ========================接口结束============================ */


    /* ================JSP页面接口开始==================== */
    //获取加入团购的商品列表
    List<BuyPackageProduct> getProductListById(int shopId);
    //删除添加到团购的商品
    void deleteAddProductById(int id);
    /* ================JSP页面接口结束==================== */
}
