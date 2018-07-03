package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCategory;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 */
public interface BuffetFoodProductMapper {

    /* =============接口开始================= */
    //根据分类id获取菜品列表
    List<BuffetFoodProduct> getGoodsListByCategoryIdDAO(int categoryId);
    //通过商品id获取商品详情
    BuffetFoodProduct getBuffetFoodProductDetailByIdDAO(int productId);
    //商品点赞，数量固定加1
    int likeProductAddOneByIdDAO(int productId);
    //产品名匹配搜索
    List<BuffetFoodProduct> matchSearchingByProductNameDAO(String key);
    /* =============接口结束================= */

    /* ==========================JSP页面接口开始=============================== */
    //通过查出来的店铺类别id查找出店铺下的商品
    List<BuffetFoodProduct> getBuffetFoodProductByIdDAO(int categoryId);
    //查询店铺下的外卖产品
    List<BuffetFoodProduct> getBuffetFoodProductListByAccountShopIdDAO(int accountShopId);
    //通过类别id查找产品
    List<BuffetFoodProduct> getBuffetFoodProductByCategoryIdDAO(@Param("categoryId") int categoryId, @Param("accountShopId") int accountShopId);
    //通过产品id查找商品
    BuffetFoodProduct getBuffetFoodProductByPIdDAO(int accountShopId, int productId);
    //添加自助点餐商品
    int addBuffetFoodProductDAO(BuffetFoodProduct product);
    //通过商品id删除商品
    int delProductByIdDAO(int productId);
    //修改商品
    int editProductByIdDAO(BuffetFoodProduct product);
    //通过分类ID删除商品
    int delProductByCIdDAO(int categoryId);
    //关联团购添加产品表，只查询出未添加到团购产品表中的数据
    List<BuffetFoodProduct> getNotInGroupBuyProductListDataDAO(int shopId);
//通过店铺id查询店铺下的所有自助点餐产品列表
    List<BuffetFoodProduct> getAllProductByShopId(@Param("shopId")int shopId,@Param("orderBy")String orderBy);
    //通过商品id下架商品(修改商品状态)
    int buffetFoodOff(@Param("productIds") List<Integer> idInts);
    //通过商品id上架商品
    int buffetFoodOn(@Param("productIds") List<Integer> idInts);
    /* ==========================JSP页面接口结束=============================== */
}
