package com.htkapp.modules.merchant.takeout.service;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;

import java.util.List;

public interface TakeoutProductServiceI {

	/* ====================接口开始======================= */

	//通过查出来的店铺类别id查找出店铺下的商品
	TakeoutProduct getTakeoutProductByProductId(int productId) throws Exception;

	//通过查出来的店铺类别id查找出店铺下的商品
	List<TakeoutProduct> getTakeoutProductById(int categoryId) throws Exception;
	//减库存操作(外卖产品id,产品数量)
	void productReduceNumber(int productId, int quantity) throws OrderException;
	/* ====================接口结束======================= */


	/* ====================JSP页面接口开始========================== */
	//查询店铺下的外卖产品
	List<TakeoutProduct> getTakeoutProductListByAccountShopId(int accountShopId) throws Exception;
	//通过类别id查找产品
	List<TakeoutProduct> getTakeoutProductListByCategoryId(int categoryId, int accountShopId) throws Exception;
	//商品数量置满
	void filledUpProductInventory( int accountShopId, int productId) throws Exception;
	//商品数量沽清
	void emptyProductInventory( int accountShopId, int productId) throws Exception;
	//通过产品id查找商品
	TakeoutProduct getTakeoutProductById(int accountShopId, int productId);
	//添加外卖商品
	void addTakeoutProduct(TakeoutProduct product)throws Exception;
	//通过商品id删除商品
	void delProductById(int productId) throws Exception;
	//修改商品
	void editProductById(TakeoutProduct product) throws Exception;
	//通过分类ID删除商品
	void delProductByCId(int categoryId) throws Exception;
	//关联团购添加产品表，只查询出未添加到团购产品表中的数据
	List<TakeoutProduct> getNotInGroupBuyProductListData(int shopId);

    List<TakeoutProduct> getTakeoutProductByCategoryIdAndIfCanBuy(Integer id);

	AjaxResponseModel setProductTakeOn(List<Integer> idInts);

	AjaxResponseModel setProductTakeOff(List<Integer> idInts);
	/* ====================JSP页面接口结束========================== */
}
