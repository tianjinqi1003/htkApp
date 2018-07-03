package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.modules.API.dto.ReturnHaveSomeList;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCategory;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 */
public interface BuffetFoodCategoryService {

    /* =============接口开始================= */
    //根据店铺id获取店铺下的自助点餐菜品类别列表
    List<BuffetFoodCategory> getBuffetFoodCategoryListByShopId(int shopId) throws Exception;
    //通过分类id查找分类数据
    ReturnHaveSomeList getBuffetFoodCategoryByCategoryId(int categoryId);
    //分类名匹配搜索
    List<BuffetFoodCategory> matchSearchingByCategoryName(String key);
    /* =============接口结束================= */


    /* ========================JSP页面接口开始============================ */
    //通过商户id查找商铺下的外卖类别
    List<BuffetFoodCategory> getBuffetFoodCategoryListByAccountShopId(int accountShopId) throws Exception;
    //通过分类id修改分类名称
    void editCategoryNameById(BuffetFoodCategory category) throws Exception;
    //通过分类id查询分类信息
    BuffetFoodCategory getCategoryById(int categoryId);
    //新增分类
    void addCategoryById(String categoryName, int accountShopId,int mark) throws Exception;
    //通过分类ID　删除分类
    void delCategoryById(int categoryId) throws Exception;
    /* ========================JSP页面接口结束============================ */
}
