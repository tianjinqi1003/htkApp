package com.htkapp.modules.admin.shopCategory.service;

import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.merchant.shop.entity.ShopCategoryData;

import java.util.List;

/**
 * Created by yinqilei on 17-6-26.
 */

public interface ShopCategoryService {

    /* ========================接口开始============================ */
    //获取全部分类表并分页
    List<ShopCategory> getAllCategory(int pageNo, int pageLimit, int mark) throws Exception;
    //根据分类ID和标记查询子分类列表
    List<ShopCategory> getCategoryListByIdAndMark(int categoryId, int mark);
    /* ========================接口结束============================== */


    /* ====================JSP接口开始===================== */
    //根据类别id查找商铺类别信息
    List<ShopCategoryData> getShopCategoryList();
    //根据类别id查找商铺类别信息
    ShopCategory getShopCategoryDataById(int categoryId) throws Exception;
    //查询标识分类列表
    List<ShopCategory> getCategoryListData(int mark);
    //通过id查找子分类
    List<ShopCategory> getChildCategoryById(int mark, int parentId);
    //添加分类下的子分类
    void addChildCategoryById(ShopCategory shopCategory) throws Exception;
    //添加一级分类
    void addCategoryByMark(ShopCategory shopCategory) throws Exception;
    //保存修改的分类信息
    void saveCategoryById(ShopCategory category)throws Exception;
    //根据分类id获取分类信息
    ShopCategory getCategoryById(int categoryId);
    //查询二级分类信息
    List<ShopCategory> getCategoryListById(int categoryId);
    //通过分类id删除分类
    void delCategoryId(int categoryId)throws Exception;
    /* ====================JSP接口结束===================== */
}
