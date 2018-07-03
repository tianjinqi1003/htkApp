package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.API.dto.ReturnHaveSomeList;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCategory;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 */

public interface BuffetFoodCategoryMapper {

    /* =============接口开始================= */
    //根据店铺id获取店铺下的自助点餐菜品类别列表
    List<BuffetFoodCategory> getBuffetFoodCategoryListByShopIdDAO(int shopId);
    //通过分类id查找分类数据
    ReturnHaveSomeList getBuffetFoodCategoryByCategoryIdDAO(int categoryId);
    //分类名匹配搜索
    List<BuffetFoodCategory> matchSearchingByCategoryNameDAO(String key);
    /* =============接口结束================= */

    /* ======================JSP页面接口开始======================== */
    //通过商户id查找商铺下的外卖类别
    List<BuffetFoodCategory> getBuffetFoodCategoryListByAccountShopIdDAO(int accountShopId);
    //通过分类id修改分类名称
    int editCategoryNameByIdDAO(BuffetFoodCategory category);
    //通过分类id查询分类信息
    BuffetFoodCategory getCategoryByIdDAO(int categoryId);
    //新增分类
    int addCategoryByIdDAO(String categoryName, int accountShopId,int mark);
    //通过分类ID　删除分类
    int delCategoryByIdDAO(int categoryId);
    /* ======================JSP页面接口结束======================== */
}
