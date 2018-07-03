package com.htkapp.modules.admin.shopCategory.dao;

import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.merchant.shop.entity.ShopCategoryData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yinqilei on 17-6-26.
 *
 */

public interface ShopCategoryMapper {

    /* ================接口开始================== */

    List<ShopCategoryData> getShopCategoryList();


    //查找所有商铺类别信息
    List<ShopCategory> getShopCategoryDAO(int mark);
    //根据分类id获取分类信息
    ShopCategory getCategoryByIdDAO(int categoryId);
    //查询二级分类信息
    List<ShopCategory> getCategoryListByIdDAO(int categoryId);
    //根据分类ID和标记查询子分类列表
    List<ShopCategory> getCategoryListByIdAndMarkDAO(int categoryId, int mark);
    /* ================接口结束================== */

    /* ====================JSP接口开始===================== */
    //根据类别id查找商铺类别信息
    ShopCategory getShopCategoryDataByIdDAO(int categoryId);
    //查询标识分类列表
    List<ShopCategory> getCategoryListDataDAO(@Param("mark") int mark);
    //通过id查找子分类
    List<ShopCategory> getChildCategoryByIdDAO(int mark, int parentId);
    //添加分类下的子分类
    int addChildCategoryByIdDAO(ShopCategory shopCategory);
    //添加一级分类
    int addCategoryByMarkDAO(ShopCategory shopCategory);
    //保存修改的分类信息
    int saveCategoryByIdDAO(ShopCategory category);
    //通过分类id删除分类
    int delCategoryIdDAO(int categoryId);
    /* ====================JSP接口结束===================== */



//    int insertShopCategory(ShopCategory shopCategory);
//
//    int deleteByPrimaryKeyShopCategory(Integer shopCategoryId);
//
//    ShopCategory selectByPrimaryKeyShopCategory(Integer shopCategoryId);
//
//    int updateByPrimaryKeySelectiveShopCategory(ShopCategory shopCategory);
//
//    List<ShopCategory> findAllShopCategoryByPage();
//
//    List<ShopCategory> findAllShopCategory();
//
//    ShopCategory findShopCategoryNameById(Integer shopCategoryId);
}
