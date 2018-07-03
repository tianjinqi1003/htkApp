package com.htkapp.modules.API.service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-6-26.
 *
 */

public interface ShopDataService {

    /* =============接口开始================= */
    //获取app首页分类
    APIResponseModel getHomePageCategory(int pageNumber, Integer mark);
    //获取根据传入的经纬度推荐商家
    APIResponseModel getBestShop(int distanceType,Double userLo, Double userLa,String token,int pageNumber, Integer mark);
    //根据条件搜索商家
    APIResponseModel getShopByCondition(String keyWord, int mark, String token,int pageNumber);
    //根据传入的外卖店铺id查询出外卖店铺信息
    APIResponseModel getShopShowInfoById(Integer shopId, String token);
    //通过传入的店铺id获得店铺信息
    APIResponseModel getGoodsListByShopId(Integer shopId);
    //通过传入的店铺id获取店铺下的评论
    APIResponseModel getShopUserReviews(Integer shopId, Integer pageNumber);
    //通过传入的店铺类别id，返回当前类别下的所有店铺列表
    APIResponseModel getShopListByCategoryId(String token,Integer categoryId,Integer pageNumber);
    //根据订单id查询订单详情
    APIResponseModel getOrderRecordDetailById(Integer orderId);
    //根据用户token查询订单列表
    APIResponseModel getOrderRecordList(String token, int pageNumber);
    //获取团购广告列表
    APIResponseModel getRandomlyAdList();
    //查看评价详情
    APIResponseModel viewReviewDetailsById(Integer orderId);
    //评价订单
    APIResponseModel evaluateOrderById(String token, Double commentsStars,String content, Integer orderId);
    //根据团购店铺id获取店铺广告
    APIResponseModel getGroupBuyAdListById(Integer shopId);
    //根据传入的套餐id查询套餐详情
    APIResponseModel getPackageDetailsById(Integer packageId);
    //根据套餐id查询套餐下的所有评论
    APIResponseModel getCommentsUnderThePackage(Integer packageId);
    //根据团购订单查询团购订单信息
    APIResponseModel getGroupBuyOrderDetailById(Integer orderId);
    //套餐详情页面显示滚动图片接口
    APIResponseModel getPackagePicture(Integer packageId);
    //根据分类id查询获取分类下的所有店铺,并筛选店铺
    APIResponseModel getShopListByCategoryIdAndChildIdAndConditions(Integer categoryId,Integer childCId, Double userLo,Double userLa,String token, int pageNumber,Integer mark,Integer distanceType);
    //根据标记和大分类id获取小分类列表(加全部分类)
    APIResponseModel getChildCategoryListByCIdAndMark(Integer mark, Integer categoryId);
    //一级分类下推荐店铺
//    APIResponseModel getRecommendedShops(String token, Integer mark,Integer categoryId, Integer childCId);
    //搜索关键词接口(返回店铺名)
    APIResponseModel getSearchKeywords(Integer mark);
    //外卖催单接口
    APIResponseModel callReminderInterface(String token, String orderNumber);
    //删除订单
    APIResponseModel deleteOrderByOrderNumber(String orderNumber, String token, Integer mark);
    //店铺头像接口
    APIResponseModel getShopImgUrl(APIRequestParams params);
    //店铺相册接口
    APIResponseModel getShopAlbumList(APIRequestParams params);
    //订座订单详情
    APIResponseModel getSeatOrderDetail(APIRequestParams params);
   /* =============接口结束================= */
}
