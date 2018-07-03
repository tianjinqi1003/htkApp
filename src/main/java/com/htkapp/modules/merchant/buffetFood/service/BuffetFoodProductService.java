package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 */
public interface BuffetFoodProductService {

    /* =============接口开始================= */
    //根据分类id获取菜品列表
    List<BuffetFoodProduct> getGoodsListByCategoryId(int categoryId, int pageNumber, int pageLimit) throws Exception;
    //通过商品id获取商品详情
    BuffetFoodProduct getBuffetFoodProductDetailById(int productId) throws Exception;
    //商品点赞，数量固定加1
    void likeProductAddOneById(int productId) throws Exception;
    //产品名匹配搜索
    List<BuffetFoodProduct> matchSearchingByProductName(String key);
    /* =============接口结束================= */

    /* ==========================JSP接口开始================================ */
    //通过查出来的店铺类别id查找出店铺下的商品
    List<BuffetFoodProduct> getBuffetFoodProductById(int categoryId) throws Exception;
    //查询店铺下的外卖产品
    List<BuffetFoodProduct> getBuffetFoodProductListByAccountShopId(int accountShopId) throws Exception;
    //通过类别id查找产品
    List<BuffetFoodProduct> getBuffetFoodProductByCategoryId(int categoryId, int accountShopId) throws Exception;
    //通过产品id查找商品
    BuffetFoodProduct getBuffetFoodProductByPId(int accountShopId, int productId);
    //添加自助点餐商品
    void addBuffetFoodProduct(BuffetFoodProduct product) throws Exception;
    //通过商品id删除商品
    void delProductById(int productId) throws Exception;
    //修改商品
    void editProductById(BuffetFoodProduct product) throws Exception;
    //通过分类ID删除商品
    void delProductByCId(int categoryId) throws Exception;
    //关联团购添加产品表，只查询出未添加到团购产品表中的数据
    List<BuffetFoodProduct> getNotInGroupBuyProductListData(int shopId);
    //通过商品id下架商品
    AjaxResponseModel buffetFoodOff(String selectedIds);
    //通过商品id上架商品
    AjaxResponseModel buffetFoodOn(String selectedIds);

    //根据店铺id查询其名下所有的产品详情
    List<BuffetFoodProduct> getAllProductByShopId(int shopId,String orderBy);

    /* ==========================JSP接口结束=============================== */
}
