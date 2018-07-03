package com.htkapp.modules.merchant.groupBuy.dao;

import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageProduct;

import java.util.List;

public interface BuyPackageProductMapper {

    /* ==================接口开始======================= */
    //查找加入团购的商品列表
    List<BuyPackageProduct> getBuyPackageProductListByShopIdDAO(int shopId);
    //插入添加的团购商品
    int insertBuyPackageProductByShopIdDAO(BuyPackageProduct buyPackageProduct);
    /* ==================接口结束======================= */

    /* ================JSP页面接口开始==================== */
    //删除添加到团购的商品
    int deleteAddProductDAO(int id);
    /* ================JSP页面接口结束==================== */
}
