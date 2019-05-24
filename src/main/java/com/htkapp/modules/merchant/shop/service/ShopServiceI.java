package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.Shop;

import java.util.List;
import java.util.Set;


public interface ShopServiceI {


	/* ================接口开始====================== */

	List<Shop> getShopListByCategoryList(Set<Integer> allCategoryIdSet);

	Set<Integer> getAllChildCategoryIdList(int categoryId);

	//根据条件搜索商家
	List<Shop> getShopByCondition(String keyWord, int mark, int pageNo, int pageLimit)throws Exception;
	//查询出所有商铺的经纬度
	List<Shop> getAllShopLatitudeAndLongitude( int pageNo, int pageLimit, int mark, String token) throws Exception;
	//推荐所有商家(用户未登陆推荐商家)
	List<Shop> notLoginRecommendedBusinesses(int mark, int pageNo, int pageLimit);
	//根据店铺id查找店铺信息
	Shop getShopDataById(int shopId) throws Exception;
	//根据店铺id和店铺类型查找店铺信息
	Shop getShopShowInfoById(int shopId) throws Exception;
	//根据传入的店铺类别id，返回店铺列表
	List<Shop> getShopListByCategoryId(int categoryId,int pageNo, int pageLimit,int mark) throws Exception;
	//通过传入的商户id查度店铺
	Shop getShopByAccountShopId(int accountShopId) throws Exception;
	//改变店铺营业状态
	boolean changeShopStateById(int accountShopId, int shopStateId) throws Exception;
	//根据商户id查询商户下的店铺
	List<Shop> getShopListByAccountShopId(int accountShopId) throws Exception;
	//通过商户id和mark标识查找商铺信息
	Shop getShopByAccountShopIdAndMark(int accountShopId, int mark) throws Exception;
	//通过一级分类id获取所有二级分类店铺
	List<Shop> getShopListByChildCategoryIdsAndFocus(int mark,Set<String> childSId, Set<Integer> shopIdList, String token, int tag, int pageNo, int pageLimit);
	//通过分类id查询所有店铺(未关注tag是0，已关注tag是1)
	List<Shop> getShopListByCategoryIdAndFocus(int mark,int categoryId, Set<Integer> shopIdList, String token, int tag,int pageNo, int pageLimit);
	//查找一级分类下的所有关注店铺数量
	int getFocusCategoryShopListCount(int mark,Set<String> childSId, Set<Integer> shopIdList, String token, int tag);
	//查找二级分类下的所有关注店铺数量
	int getFocusChildCategoryShopListCount(int mark,int categoryId, Set<Integer> shopIdList, String token, int tag);
	//获取店铺总数量
	int getAllShopCount(int mark);
	//根据index查询店铺
	Shop getShopByIndex(int index,int mark);
	//更改店铺二维码图片url
	void updateShopQRCode(String qrImgUrl, int shopId);
	/* ================接口结束====================== */

	/* ===================JSP页面接口开始======================= */
	//根据商户id查询店铺信息
	Shop getShopDataByAccountShopId(int accountShopId) throws Exception;
	//根据商户id查询店铺id
	Shop getShopIdByAccountShopId(int accountShopId,int mark) throws Exception;
	//通过商户token查找商铺信息
	Shop getShopDataByAccountShopToken(String accountShopToken) throws Exception;
	//通过商户ID改变店铺状态
	void changeShopOpenStateById(int stateId, int accountShopId) throws Exception;
	//通过商户ID和mark获取商铺
	Shop getShopDataByAccountShopIdAndMark(int accountShopId, int mark);
	//注册商铺
	int insertShopById(Shop shop);
	//查找店铺信息
	Shop getShopMessageById(int accountShopId, int mark);
	//店铺头像上传修改
	void updateShopImg(Shop shop);
	//营业时间修改
	void updateOpeningTime(Shop shop);
	//订餐电话修改
	void updatePhone(Shop shop);
	//店铺公告修改
	void updateIntro(Shop shop);
	//店铺简介修改
	void updateDes(Shop shop);
	//注册shop_message表
	int initShopMessage(int shopId);
	//满多少元起送
	void updateStartDeliveryPrice(Shop shop);
	//配送费
	void updateDeliveryFee(Shop shop);

	/* ====================JSP页面接口结束========================= */

}
