package com.htkapp.modules.merchant.takeout.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;

import java.util.List;


public interface TakeoutCategoryServiceI {


    /* ==============接口开始==================== */
    //通过店铺id查找外卖类别
    List<TakeoutCategory> getTakeoutCategoryById(int shopId) throws Exception;

    /* ==============接口结束==================== */

    /* ===================JSP页面接口开始============================ */
    //通过商户id查找商铺下的外卖类别
    List<TakeoutCategory> getTakeoutCategoryListByAccountShopId(int accountShopId) throws Exception;
    //通过分类id修改分类名称
    void editCategoryNameById(TakeoutCategory category) throws Exception;
    //商户新增分类
    void addCategoryById(String categoryName, int accountShopId,int mark) throws Exception;
    //通过分类id查询分类信息
    TakeoutCategory getCategoryById(int categoryId);
    //删除分类
    void delCategoryById(int categoryId) throws Exception;
    /* ===================JSP页面接口结束============================ */
}
