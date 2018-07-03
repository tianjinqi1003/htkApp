package com.htkapp.modules.API.service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;

import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 * 自助点餐Service
 */
public interface BuffetFoodService {

    /* =============接口开始================= */
    //通过商铺id获取分类列表
    APIResponseModel getCategoryList(String qrKey);
    //通过商铺id获取分类列表
    APIResponseModel getCategoryList(int shopId);
    //根据分类获取菜品列表
    APIResponseModel getGoodsListByCategoryId(APIRequestParams params);
    //获取商铺的座位列表
    APIResponseModel getShopSeatInfoById(Integer shopId);
    //通过自助点餐商品id获取商品详情
    APIResponseModel getProductDetailById(APIRequestParams params);
    //商品点赞，数量固定加1
    APIResponseModel likeProduct(Integer productId);
    //根据用户token获取自助点餐订单列表
    APIResponseModel getBuffetFoodOrderList(String token, Integer shopId, int pageNumber);
    //根据订单id获取订单详情列表
    APIResponseModel getOrderDetailList(String token, Integer orderId);
    //支付成功调起改变订单状态
    APIResponseModel paymentSuccess(String token, Integer orderId,Integer paymentMethodId);
    //扫码确认当前店铺是否收藏
    APIResponseModel confirmCollection(String qrKey, String token);
    //根据用户token,pageNumber获取订单列表
    APIResponseModel getOrderListByTokenAndPageNumber(String token, int pageNumber);
    //用户催单 发送消息给商家
    APIResponseModel reminderFormToMerchant(String token, String orderNumber);
    //退菜请求商家确认接口
    APIResponseModel confirmWithdrawalRequestByOrderNumber(String token, String orderNumber, List<Integer> idLists);
    //商户回复用户退菜请求
    APIResponseModel replyToRefundRequest(String token, String accountShopToken, String result);
    //商户回复催单  发送消息给用户
    APIResponseModel reminderFormToAccount(String token, String accountShopToken, String replyContent);
    //评论列表接口
    APIResponseModel getGoodsCommentListByProductId(APIRequestParams params, int pageNum);
    //商品页面－确认按钮(生成初始订单,登陆、未登陆状态)
    APIResponseModel insertInitialOrder(APIRequestParams params, BuffetFoodOrder order);
    //订单详情页面
    APIResponseModel getOrderDetailsByOrderNumber(APIRequestParams params);
    //订单详情－下单请求接口
    APIResponseModel enterOrderRequest(APIRequestParams params);
    //下单按钮
    APIResponseModel enterOrderButton(APIRequestParams params, BuffetFoodOrder order);
    //确认下单按钮
    APIResponseModel confirmOrderButton(APIRequestParams params, BuffetFoodOrder order);
    //优惠券列表
    APIResponseModel getBuffetFoodCouponList(APIRequestParams params);
    //已点商品列表
    APIResponseModel getLastModifiedProductList(APIRequestParams params);
    //自助支付、支付优惠金额
    APIResponseModel getBuffetDiscount(APIRequestParams params);
    //获取用户信息接口
    APIResponseModel getAccountMes(APIRequestParams params);
    //自助点餐催单接口
    APIResponseModel reminderInterface(APIRequestParams params);
    //调整订单接口
    APIResponseModel adjustOrder(APIRequestParams params, BuffetFoodOrder order);
    //确认调单接口
    APIResponseModel enterAdjustOrder(APIRequestParams params, BuffetFoodOrder order);
    //确认调单页面商品列表
    APIResponseModel getLastModifiedAdjustProductList(APIRequestParams params);
    //搜索商品
    APIResponseModel searchProductByKey(APIRequestParams params);
    //收藏接口
    APIResponseModel addToWishListById(APIRequestParams params);
    //订单状态查询接口
    APIResponseModel getOrderStateByOrderNumber(APIRequestParams params);
    //根据父id查询套餐内详情
    APIResponseModel getPackageDetailById(APIRequestParams params);
    //快捷下单接口
    APIResponseModel quickOrder(BuffetFoodOrder buffetFoodOrder);
    //用户不点餐时删除订单
//    APIResponseModel delOrder(BuffetFoodOrder buffetFoodOrder);
    //确认下单接口(调整后)
//    APIResponseModel enterOrder(APIRequestParams params, BuffetFoodOrder order);
    //查询用户名下是否存在未完成的订单
    APIResponseModel checkOrder(APIRequestParams param);
    //查询用户名下该店铺的所有历史订单
    APIResponseModel getAllOrderList(APIRequestParams param);
    /* =============接口结束================= */
}
