package com.htkapp.modules.merchant.takeout.dao;

import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TakeoutProductMapper {

    /* ===============接口开始======================= */
    //通过查出来的店铺类别id查找出店铺下的商品
    List<TakeoutProduct> getTakeoutProductByIdDAO(int categoryId);
    //减库存操作(外卖产品id,产品数量)
    int productReduceNumberDAO(@Param("productId") int productId, @Param("quantity") int quantity);

    /* ===============接口结束======================= */

    /* ====================JSP页面接口开始========================= */
    //查询店铺下的外卖产品
    List<TakeoutProduct> getTakeoutProductListByAccountShopIdDAO(int accountShopId);
    //通过类别id查找产品
    List<TakeoutProduct> getTakeoutProductListByCategoryIdDAO(@Param("categoryId") int categoryId, @Param("accountShopId") int accountShopId);
    //商品数量置满
    int filledUpProductInventoryDAO(@Param("accountShopId") int accountShopId, @Param("productId") int productId);
    //商品数量沽清
    int emptyProductInventoryDAO(@Param("accountShopId") int accountShopId, @Param("productId") int productId);
    //通过产品id查找商品
    TakeoutProduct getTakeoutProductByPIdDAO(int accountShopId, int productId);
    //添加外卖商品
    int addTakeoutProductDAO(TakeoutProduct product);
    //通过商品id删除商品
    int delProductByIdDAO(int productId);
    //修改商品
    int editProductByIdDAO(TakeoutProduct product);
    //通过分类ID删除商品
    int delProductByCIdDAO(int categoryId);
    //关联团购添加产品表，只查询出未添加到团购产品表中的数据
    List<TakeoutProduct> getNotInGroupBuyProductListDataDAO(int shopId);

    TakeoutProduct getTakeoutProductByProductId(int productId);

    List<TakeoutProduct> getTakeoutProductByCategoryIdAndIfCanBuy(int categoryId);

    int setProductTakeOn(@Param("productIds") List<Integer> idInts);

    int setProductTakeOff(@Param("productIds") List<Integer> idInts);
    /* ====================JSP页面接口结束========================== */
}	