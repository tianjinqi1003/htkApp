package com.htkapp.modules.API.web;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.service.ShopDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-6-25.
 * 商铺信息类
 */

@RestController
@RequestMapping("/API/shopDataAPI")
public class ShopDataAPI {

    @Resource
    private ShopDataService shopDataService;

    //获取app首页的商铺分类信息
    @RequestMapping("/category")
    public APIResponseModel getHomePageCategory(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber, Integer mark) {
        return shopDataService.getHomePageCategory(pageNumber, mark);
    }

    //获取推荐商家(只显示未收藏的店铺)
    @RequestMapping("/getBestShop")
    public APIResponseModel getBestShop(Integer distanceType, String token, Integer mark, Double userLo, Double userLa,
                                        @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
        //distanceType 距离 1：1千米 2 ：3千米  3： 5千米
        if (distanceType == null) distanceType = 2;
        return shopDataService.getBestShop(distanceType, userLo, userLa, token, pageNumber, mark);
    }


    //根据条件搜索商家
    @RequestMapping("/getShopByCondition")
    public APIResponseModel getShopByCondition(String keyWord, String token, @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                                               @RequestParam(value = "mark", defaultValue = "0", required = false) Integer mark) {
        return shopDataService.getShopByCondition(keyWord, mark, token, pageNumber);
    }

    //通过店铺id获得店铺信息
    @RequestMapping("/getShopShowInfoById")
    public APIResponseModel getShopShowInfoById(Integer shopId, String token) {
        //先根据传入的店铺id查询出店铺信息
        return shopDataService.getShopShowInfoById(shopId, token);
    }

    //通过传入的店铺id获得店铺商品信息
    @RequestMapping("/getGoodsListByShopId")
    public APIResponseModel getGoodsListByShopId(Integer shopId) {
        return shopDataService.getGoodsListByShopId(shopId);
    }

    //根据店铺id获取店铺信息下的评论
    @RequestMapping("/getShopUserReviews")
    public APIResponseModel getShopUserReviews(Integer shopId,
                                               @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
        return shopDataService.getShopUserReviews(shopId, pageNumber);
    }

    //根据标记和大分类id获取小分类列表(返回数据加:全部分类字符串)
    @RequestMapping("/getChildCategoryListByCIdAndMark")
    public APIResponseModel getChildCategoryListByCIdAndMark(Integer mark, Integer categoryId) {
        return shopDataService.getChildCategoryListByCIdAndMark(mark, categoryId);
    }

    //通过传入的商铺类别id,获取店铺列表
    @RequestMapping("/getFocusShopListByCategoryId")
    public APIResponseModel getShopListByCategoryId(String token, Integer categoryId, Integer childCId, Integer mark, Double userLo, Double userLa,
                                                    @RequestParam(value = "distanceType", required = false, defaultValue = "1") Integer distanceType,
                                                    @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
        //根据分类id,查询分类下的子类，再查询子类下的所有店铺
        //筛选:根据距离，
        //distanceType 距离 1：1千米 2 ：3千米  3： 5千米
        //条件
        //1:500米  2:1000米 3:2000米 distanceType
        //用户经纬度
        //二级分类是0 则代表全部
        //分类id为二个：大分类ID,小分类id
        //mark标记  0外卖　1团购
        //分页
//        if (distanceType == null) distanceType = 2;
//        return shopDataService.getShopListByCategoryId(token, categoryId, pageNumber);
        return shopDataService.getShopListByCategoryIdAndChildIdAndConditions(categoryId, childCId, userLo, userLa, token, pageNumber, mark, distanceType);
    }

    //通过传入的订单id查找订单详情
    @RequestMapping("/getOrderRecordDetailById")
    public APIResponseModel getOrderRecordDetailById(Integer orderId) {
        return shopDataService.getOrderRecordDetailById(orderId);
    }

    //根据用户token查询订单列表　(外卖、团购、订座订单)
    @RequestMapping("/getOrderRecordList")
    public APIResponseModel getOrderRecordList(String token, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
        return shopDataService.getOrderRecordList(token, pageNumber);
    }

    //随机获取五条团购广告列表
    @RequestMapping("/getRandomlyAdList")
    public APIResponseModel getRandomlyAdList() {
        return shopDataService.getRandomlyAdList();
    }

    //广告
    @RequestMapping("/getTakeoutAdList")
    public APIResponseModel getTakeoutAdList(){
        return shopDataService.getRandomlyAdList();
    }

    //根据团购店铺id获取店铺广告
    @RequestMapping("/getGroupBuyAdListById")
    public APIResponseModel getGroupBuyAdListById(Integer shopId) {
        return shopDataService.getGroupBuyAdListById(shopId);
    }

    //根据订单id查看订单评论详情
    @RequestMapping("/viewReviewDetailsById")
    public APIResponseModel viewReviewDetailsById(Integer orderId) {
        return shopDataService.viewReviewDetailsById(orderId);
    }

    //评价订单
    @RequestMapping("/commentOrder")
    public APIResponseModel commentOrder(String token, Double commentsStars, String content, Integer orderId) {
        return shopDataService.evaluateOrderById(token, commentsStars, content, orderId);
    }

    //通过套餐id获取套餐详情
    @RequestMapping("/getPackageDetailsById")
    public APIResponseModel getPackageDetailsById(Integer packageId) {
        return shopDataService.getPackageDetailsById(packageId);
    }

    //通过套餐id查看套餐下的所有评论
    @RequestMapping("/getCommentsUnderThePackage")
    public APIResponseModel getCommentsUnderThePackage(Integer packageId) {
        return shopDataService.getCommentsUnderThePackage(packageId);
    }

    //根据团购订单查询团购订单信息
    @RequestMapping("/getGroupBuyOrderDetailById")
    public APIResponseModel getGroupBuyOrderDetailById(Integer orderId) {
        return shopDataService.getGroupBuyOrderDetailById(orderId);
    }

    //搜索关键词接口(返回店铺名)
    @RequestMapping("/getSearchKeywords")
    public APIResponseModel getSearchKeywords(Integer mark) {
        return shopDataService.getSearchKeywords(mark);
    }

    //外卖催单接口
    @RequestMapping("/callReminderInterface")
    public APIResponseModel callReminderInterface(String token, String orderNumber) {
        //传入订单号
        return shopDataService.callReminderInterface(token, orderNumber);
    }

    //订单删除接口
    @RequestMapping("deleteOrderByOrderNumber")
    public APIResponseModel deleteOrderByOrderNumber(APIRequestParams params){
        return shopDataService.deleteOrderByOrderNumber(params.getOrderNumber(),params.getToken(), params.getMark());
    }

    //店铺头像接口
    @RequestMapping(value = "/getShopImgUrl", method = RequestMethod.POST)
    public APIResponseModel getShopImgUrl(APIRequestParams params){
        return shopDataService.getShopImgUrl(params);
    }

    //店铺相册接口
    @RequestMapping(value = "/getShopAlbumList")
    public APIResponseModel getShopAlbumList(APIRequestParams params){
        return shopDataService.getShopAlbumList(params);
    }

    //订座订单详情
    @RequestMapping(value = "/getSeatOrderDetail")
    public APIResponseModel getSeatOrderDetail(APIRequestParams params){
        return shopDataService.getSeatOrderDetail(params);
    }
}
