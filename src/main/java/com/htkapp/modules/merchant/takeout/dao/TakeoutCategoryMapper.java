package com.htkapp.modules.merchant.takeout.dao;

import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;

import java.util.List;

/**
 * 外卖类别
 */

public interface TakeoutCategoryMapper {

    /* ====================接口开始============================ */
    //通过店铺id查找外卖类别
    List<TakeoutCategory> getTakeoutCategoryByIdDAO(int shopId);

    /* ====================接口结束============================ */

    /* ===================JSP页面接口开始============================ */
    //通过商户id查找商铺下的外卖类别
    List<TakeoutCategory> getTakeoutCategoryListByAccountShopIdDAO(int accountShopId);
    //通过分类id修改分类名称
    int editCategoryNameByIdDAO(TakeoutCategory category);
    //通过分类id查询分类信息
    TakeoutCategory getCategoryByIdDAO(int categoryId);
    //新增分类
    int addCategoryByIdDAO(String categoryName, int accountShopId,int mark);
    //通过分类ID　删除分类
    int delCategoryByIdDAO(int categoryId);
    /* ===================JSP页面接口结束============================ */

}