package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 商铺
 */

public interface ShopMapper {

    /* ====================接口开始=====================  */

    List<Shop> getShopListByCategoryList(@Param("categoryIdList") Set<Integer> allCategoryIdSet);

    Set<Integer> getAllChildCategoryIdList(int categoryId);

    //获取所有店铺的经纬度(根据用户token推荐未关注商家)
    List<Shop> getAllShopLatitudeAndLongitudeDAO(int mark, String token, String orderDesc, Date date);
    //推荐所有商家(用户未登陆推荐商家)
    List<Shop> getNotLoginRecommendedBusinessesDAO(int mark, String orderDesc, Date date);
    //根据条件搜索商家
    List<Shop> getShopByConditionDAO(@Param("keyWord") String keyWord, @Param("mark") int mark);
    //根据店铺id查找店铺信息
    Shop getShopDataByIdDAO(int shopId);
    //根据店铺id查找店铺id
    Shop getShopIdByAccountShopId(int accountShopId,int mark);
    //根据店铺id和店铺类型查找店铺信息
    Shop getShopShowInfoByIdDAO(@Param("shopId") int shopId);
    //根据传入的店铺类别id，返回店铺列表
    List<Shop> getShopListByCategoryIdDAO(int categoryId,int mark);
    //通过传入的商户id查询店铺
    List<Shop> getShopByAccountShopIdDAO(int accountShopId);
    //改变店铺营业状态
    int changeShopStateByIdDAO(@Param("accountShopId") int accountShopId, @Param("shopStateId") int shopStateId);
    //根据商户id查询商户下的店铺
    List<Shop> getShopListByAccountShopIdDAO(int accountShopId);
    //通过商户id和mark标识查找商铺信息
    Shop getShopByAccountShopIdAndMarkDAO(@Param("accountShopId") int accountShopId, @Param("mark") int mark);
    //通过分类id查询所有已关注店铺
    List<Shop> getShopListByCategoryIdAndFocusDAO(@Param("mark") int mark, @Param("categoryId") int categoryId, @Param("shopIdList") Set<Integer> shopIdList, @Param("token") String token, @Param("tag") int tag,@Param("nowDate") Date date);
    //通过一级分类id获取所有二级分类店铺
    List<Shop> getShopListByChildCategoryIdsAndFocusDAO(@Param("mark") int mark, @Param("childSId") Set<String> childSId, @Param("shopIdList") Set<Integer> shopIdList, @Param("token") String token, @Param("tag") int tag,@Param("nowDate") Date date);
    //查找一级分类下的用户没有关注的店铺
    List<Shop> getNotFocusShopByCategoryDAO();
    //查找二级分类下的用户没有关注的店铺
    List<Shop> getNotFocusShopByChildCategoryDAO();
    //获取店铺总数量
    int getAllShopCountDAO(int mark);
    //根据index查询店铺
    Shop getShopByIndexDAO(int index,int mark);
    //更改店铺二维码图片url
    int updateShopQRCodeDAO(String qrImgUrl, int shopId);
    /* ====================接口结束=====================  */


    /* ====================JSP页面接口开始============================== */
    //根据商户id查询店铺信息
    Shop getShopDataByAccountShopIdDAO(int accountShopId);
    //通过商户token查找商铺信息
    Shop getShopDataByAccountShopTokenDAO(String accountShopToken);
    //注册商铺
    int insertShopByIdDAO(Shop shop);
    //查找店铺信息
    Shop getShopMessageByIdDAO(int accountShopId, int mark);
    //店铺头像上传修改
    int updateShopImgDAO(Shop shop);
    //营业时间修改
    int updateOpeningTimeDAO(Shop shop);
    //订餐电话修改
    int updatePhoneDAO(Shop shop);
    //店铺公告修改
    int updateIntroDAO(Shop shop);
    //店铺简介修改
    int updateDesDAO(Shop shop);
    //配送费
    int updateDeliveryFeeDAO(Shop shop);
    /* ========================JSP页面接品结束=============================== */
}