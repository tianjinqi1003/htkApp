package com.htkapp.modules.API.web;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.service.BuffetFoodService;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 * 自助点餐api控制类
 */

@RestController
@RequestMapping("/API/buffetFoodAPI/")
public class BuffetFoodAPI {

    @Resource
    private BuffetFoodService buffetFoodService;

    //通过商铺id获取分类列表
//    @RequestMapping(value = "/getCategoryList", method = RequestMethod.POST)
//    public APIResponseModel getCategoryList(String qrKey) {
//        return buffetFoodService.getCategoryList(qrKey);
//    }

    //通过商铺id获取分类列表
    @RequestMapping(value = "/getCategoryList", method = RequestMethod.POST)
    public APIResponseModel getCategoryList(int shopId) {
        return buffetFoodService.getCategoryList(shopId);
    }

    //根据分类id获取菜品列表
    @RequestMapping(value = "/getGoodsListByCategoryId", method = RequestMethod.POST)
    public APIResponseModel getGoodsListByCategoryId(APIRequestParams params, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        params.setPageNumber(pageNum);
        return buffetFoodService.getGoodsListByCategoryId(params);
    }

    //通地自助点餐产品id获取产品详情
    @RequestMapping(value = "/getProductDetailById", method = RequestMethod.POST)
    public APIResponseModel getProductDetailById(APIRequestParams params) {
        return buffetFoodService.getProductDetailById(params);
    }

    //收藏接口 取消收藏接口
    @RequestMapping(value = "/addToWishListById", method = RequestMethod.POST)
    public APIResponseModel addToWishListById(APIRequestParams params){
        return buffetFoodService.addToWishListById(params);
    }

    //商品－评论列表接口
    @RequestMapping(value = "/getGoodsCommentListByProductId", method = RequestMethod.POST)
    public APIResponseModel getGoodsCommentListByProductId(APIRequestParams params, @RequestParam(value = "pgaNum", required = false, defaultValue = "1") Integer pageNum){
        return buffetFoodService.getGoodsCommentListByProductId(params, pageNum);
    }

    //商品页面－确认按钮(生成初始订单,登陆、未登陆状态)
    //初始化创建一个空的订单
    @RequestMapping(value = "/insertInitialOrder", method = RequestMethod.POST)
    public APIResponseModel insertInitialOrder(APIRequestParams params, BuffetFoodOrder buffetFoodOrder){
        return null;
    }

    //订单详情接口
    @RequestMapping(value = "/getOrderDetailsByOrderNumber", method = RequestMethod.POST)
    public APIResponseModel getOrderDetailsByOrderNumber(APIRequestParams params){
        return buffetFoodService.getOrderDetailsByOrderNumber(params);
    }

    //订单状态：0初始订单，1下单成功，2已结算

    //订单详情－已点商品请求接口
    @RequestMapping(value = "/enterOrderRequest", method = RequestMethod.POST)
    public APIResponseModel enterOrderRequest(APIRequestParams params){
        return buffetFoodService.enterOrderRequest(params);
    }

    //获取商铺自助点餐的座位列表
    @RequestMapping(value = "/getShopSeatInfoById", method = RequestMethod.POST)
    public APIResponseModel getShopSeatInfoById(Integer shopId) {
        return buffetFoodService.getShopSeatInfoById(shopId);
    }

    //优惠券列表
    @RequestMapping(value = "/getBuffetFoodCouponList", method = RequestMethod.POST)
    public APIResponseModel getBuffetFoodCouponList(APIRequestParams params){
        return buffetFoodService.getBuffetFoodCouponList(params);
    }

    //自助支付、支付优惠金额
    @RequestMapping(value = "/getBuffetDiscount",method = RequestMethod.POST)
    public APIResponseModel getBuffetDiscount(APIRequestParams params){
        return buffetFoodService.getBuffetDiscount(params);
    }

    //已点商品列表
    @RequestMapping(value = "/getLastModifiedProductList", method = RequestMethod.POST)
    public APIResponseModel getLastModifiedProductList(APIRequestParams params){
        return buffetFoodService.getLastModifiedProductList(params);
    }


    //确认调单页面商品列表
    @RequestMapping(value = "/getLastModifiedAdjustProductList", method = RequestMethod.POST)
    public APIResponseModel getLastModifiedAdjustProductList(APIRequestParams params){
    	  String a="1111";
        return buffetFoodService.getLastModifiedAdjustProductList(params);
    }

    //订单详情－催单请求接口
    @RequestMapping(value = "/reminderInterface", method = RequestMethod.POST)
    public APIResponseModel reminderInterface(APIRequestParams params){
        return buffetFoodService.reminderInterface(params);
    }
    

    //订单详情－调单请求接口
    //废弃
    @RequestMapping(value = "/adjustOrder", method = RequestMethod.POST)
    public APIResponseModel adjustOrder(APIRequestParams params, BuffetFoodOrder order){
        return buffetFoodService.adjustOrder(params,order);
    }

    //订单详情－确认调单接口
    @RequestMapping(value = "/enterAdjustOrder", method = RequestMethod.POST)
    public APIResponseModel enterAdjustOrder(APIRequestParams params, BuffetFoodOrder order){
        System.out.printf(params.toString());
        System.out.printf(order.toString());
        return buffetFoodService.enterAdjustOrder(params, order);
    }

    //获取用户信息接口
    @RequestMapping(value = "/getAccountMes", method = RequestMethod.POST)
    public APIResponseModel getAccountMes(APIRequestParams params){
        return buffetFoodService.getAccountMes(params);
    }

    //自助点餐商品点赞接口
    @RequestMapping(value = "/likeProduct", method = RequestMethod.POST)
    public APIResponseModel likeProduct(Integer productId) {
        return buffetFoodService.likeProduct(productId);
    }

    //搜索商品接口
    @RequestMapping(value = "/searchProductByKey", method = RequestMethod.POST)
    public APIResponseModel searchProductByKey(APIRequestParams params){
        return buffetFoodService.searchProductByKey(params);
    }

    //订单状态查询接口
    @RequestMapping(value = "/getOrderStateByOrderNumber", method = RequestMethod.POST)
    public APIResponseModel getOrderStateByOrderNumber(APIRequestParams params){
        return buffetFoodService.getOrderStateByOrderNumber(params);
    }


    //根据父id查询套餐内详情
    @RequestMapping(value = "/getPackageDetailById", method = RequestMethod.POST)
    public APIResponseModel getPackageDetailById(APIRequestParams params){
        return buffetFoodService.getPackageDetailById(params);
    }

    //快捷下单接口
    @RequestMapping(value = "/quickOrder", method = RequestMethod.POST)
    public APIResponseModel quickOrder(BuffetFoodOrder buffetFoodOrder) {
        return buffetFoodService.quickOrder(buffetFoodOrder);
    }
    
//    //订单未下单时退出，删除订单
//    @RequestMapping(value = "/delOrder", method = RequestMethod.POST)
//    public APIResponseModel delOrder(BuffetFoodOrder buffetFoodOrder) {
//        return buffetFoodService.delOrder(buffetFoodOrder);
//    }

//    //下单按钮
    //
    @RequestMapping(value = "/enterOrderButton", method = RequestMethod.POST)
    public APIResponseModel enterOrderButton(APIRequestParams params, BuffetFoodOrder buffetFoodOrder){
        return buffetFoodService.enterOrderButton(params,buffetFoodOrder);
    }

    //确认下单按钮
    //废弃
    @RequestMapping(value = "/confirmOrderButtn", method = RequestMethod.POST)
    public APIResponseModel confirmOrderButton(APIRequestParams params, BuffetFoodOrder order){
        return buffetFoodService.confirmOrderButton(params, order);
    }
    
    //确认下单接口(修改位置后）
    @RequestMapping(value = "/confirmOrderButton", method = RequestMethod.POST)
    public APIResponseModel enterOrder(APIRequestParams params, BuffetFoodOrder order){
        return buffetFoodService.enterOrderButton(params, order);
    }
    //查询用户名下是否存在未完成的订单
    @RequestMapping(value = "/checkIfAlreadyExistOrder", method = RequestMethod.POST)
    public APIResponseModel checkOrder(APIRequestParams params){
        return buffetFoodService.checkOrder(params);
    }
    //查询用户名下的所有订单
    @RequestMapping(value = "/getAllOrderList", method = RequestMethod.POST)
    public APIResponseModel getAllOrderList(APIRequestParams params){
        return buffetFoodService.getAllOrderList(params);
    }



    //扫码确认当前店铺是否收藏
//    @RequestMapping("/confirmCollection")
//    public APIResponseModel confirmCollection(String qrKey, String token) {
//        return buffetFoodService.confirmCollection(qrKey, token);
//    }

    //自助点餐订单列表(改动:显示自助点餐总订单列表)
    @RequestMapping("/getBuffetFoodOrderList")
    public APIResponseModel getBuffetFoodOrderList(String token,
                                                   @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber) {
        return buffetFoodService.getOrderListByTokenAndPageNumber(token, pageNumber);
    }

    //订单详情列表
    @RequestMapping("/getOrderDetailList")
    public APIResponseModel getOrderDetailList(String token, Integer orderId) {
        return buffetFoodService.getOrderDetailList(token, orderId);
    }

    //支付成功调起改变订单状态
    @RequestMapping("/paymentSuccess")
    public APIResponseModel paymentSuccess(String token, Integer orderId, Integer paymentMethodId) {
        return buffetFoodService.paymentSuccess(token, orderId, paymentMethodId);
    }

    //用户催单 发送消息给商家
    @RequestMapping("/reminderFormToMerchant")
    public APIResponseModel reminderFormToMerchant(String token, String orderNumber) {
        //用户token ,订单信息  确定是哪单
        //app端根据订单状态，判断是否显示催单按钮(后台只完成催单功能,不做判断)
        //app记录催单次数（限制摧单时间）
        return buffetFoodService.reminderFormToMerchant(token, orderNumber);
    }

    //商户回复催单  发送消息给用户
    @RequestMapping("/reminderFormToAccount")
    public APIResponseModel reminderFormToAccount(String token, String accountShopToken, String replyContent) {
        return buffetFoodService.reminderFormToAccount(token, accountShopToken, replyContent);
    }

    //商户回复用户退菜请求
    @RequestMapping("/replyToRefundRequest")
    public APIResponseModel replyToRefundRequest(String token, String accountShopToken, String result) {
        return buffetFoodService.replyToRefundRequest(token, accountShopToken, result);
    }

    //用户退菜请求接口
    @RequestMapping("/confirmWithdrawalRequest")
    public APIResponseModel confirmWithdrawalRequest(String token, String orderNumber, String idList) {
        //根据用户token 和 orderNumber 查找商家信息,提示商家是否允许用户退菜
        //退菜接口，用户发起退菜请求，传参订单号，自增ID号,
        //推送给商家确认
        //商家确认允许后，则退菜请求成功 推送给app退菜成功
        //商家确认拒绝后，则退菜请求失败 推送给app退菜失败原因
        if (StringUtils.isEmpty(idList)) {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
        Gson gson = new Gson();
        List<Integer> idLists = gson.fromJson(idList, new TypeToken<List<Integer>>() {
        }.getType());
        return buffetFoodService.confirmWithdrawalRequestByOrderNumber(token, orderNumber, idLists);
    }
}
