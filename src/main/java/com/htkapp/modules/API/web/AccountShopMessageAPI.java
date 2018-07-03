package com.htkapp.modules.API.web;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.API.service.AccountShopMessageAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by yinqilei on 17-7-5.
 * 商户信息api
 */

@RestController
@RequestMapping("/API/accountShopMessageAPI/")
public class AccountShopMessageAPI {

    @Resource
    private AccountShopMessageAPIService accountShopMessageAPIService;

    public static int accountShopLoginLogId = 0;

    //商户app端登陆接口
    @RequestMapping("/appAccountShopLoginByUserName")
    public APIResponseModel appAccountShopLoginByUserName(APIRequestParams params) {
        return accountShopMessageAPIService.appAccountShopLoginByUserName(params.getUserName(), params.getPassword(), params.getRole());
    }
    	
    //忘记密码 通过手机短信找回密码
    @RequestMapping("/forgetPasswordBySMS")
    public APIResponseModel forgetPasswordBySMS(APIRequestParams params) {
        return accountShopMessageAPIService.forgetPasswordBySMS(params.getPhone(), params.getCode().toString(), params.getPassword());
    }

    //个人信息
    @RequestMapping("/getAppAccountShopData")
    public APIResponseModel getAppAccountShopData(APIRequestParams params) {
        return accountShopMessageAPIService.getAppAccountShopData(params.getToken());
    }

    //改变店铺状态
    @RequestMapping("/changeShopState")
    public APIResponseModel changeShopStateByToken(String token, Integer shopStateId) {
        return accountShopMessageAPIService.changeShopStateByToken(token, shopStateId);
    }

    //获取已处理订单总列表(团购、外卖)
//    @RequestMapping("/getAllProcessedOrderList")
//    public APIResponseModel getAllProcessedOrderList(String token,
//                                                     @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
//        //根据商户token查询商户下的外卖店铺和团购店铺
//        //如果有外卖和团购店铺　再根据店铺id查询店铺下的订单数据
//        //根据查询到的订单列表　再筛选出是否已处理订单
//        //把查询到的数据按时间排序，返回数据
//        return accountShopMessageAPIService.getAllProcessedOrderList(token, pageNumber);
//    }

    //商户已处理的团购订单列表
    //团购订单状态:10未使用   11使用  12
//    @RequestMapping("/getGroupBuyOrderListByAccountShopToken")
//    public APIResponseModel getGroupBuyOrderListByAccountShopToken(String token, Integer mark,
//                                                                   @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
//        return accountShopMessageAPIService.getGroupBuyOrderListByAccountShopToken(token, mark, pageNumber);
//    }

    //TODO 商户已处理的外卖订单列表
    @RequestMapping("/getTakeoutOrderListByAccountShopToken")
    public APIResponseModel getTakeoutOrderListByAccountShopToken(String token, Integer mark,
                                                                  @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
        return accountShopMessageAPIService.getTakeoutOrderListByAccountShopToken(token, mark, pageNumber);
    }

    //商户未处理的订单总列表
//    @RequestMapping("/getAllUntreatedOrderList")
//    public APIResponseModel getAllUntreatedOrderList(String token, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
//        //根据商户token查询商户下的外卖店铺和团购店铺
//        //如果有外卖和团购店铺　再根据店铺id查询店铺下的订单数据
//        //根据查询到的订单列表　再筛选出是否已处理订单
//        //把查询到的数据按时间排序，返回数据
//        return accountShopMessageAPIService.getAllUntreatedOrderList(token, pageNumber);
//    }

    //TODO 商户未处理的外卖订单列表
    @RequestMapping("/getUntreatedTakeoutOrderList")
    public APIResponseModel getUntreatedTakeoutOrderList(String token, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
        return accountShopMessageAPIService.getUntreatedTakeoutOrderList(token, pageNumber);
    }

    //商户未处理的团购订单列表
//    @RequestMapping("/getUntreatedGroupBuyOrderList")
//    public APIResponseModel getUntreatedGroupBuyOrderList(String token, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
//        return accountShopMessageAPIService.getUntreatedGroupBuyOrderList(token, pageNumber);
//    }

    //商户处理外卖订单(接单、拒单, 传入不同状态id)
    @RequestMapping("/handlesTakeoutOrders")
    public APIResponseModel handlesTakeoutOrders(String token, String orderNumber, Integer orderStateId, String content) {
        //用户下单后，商户处理订单操作
        //是否接单：是或否
        //如果接单成功后，显示是否配送，商户准备成功后，更改配送状态
        //当用户确认接单，一单外卖就确认完成
        return accountShopMessageAPIService.handlesTakeoutOrders(token, orderNumber, orderStateId, content);
    }

    //商户备货成功　确认配送外卖
//    @RequestMapping("/deliveryTakeout")
//    public APIResponseModel deliveryTakeout(String token, String orderNumber) {
//        return accountShopMessageAPIService.deliveryTakeout(token, orderNumber);
//    }

    //验证用户消费团购订单
//    @RequestMapping("/groupBuyConsumption")
//    public APIResponseModel groupBuyConsumption(String token, String orderNumber) {
//        return accountShopMessageAPIService.groupBuyConsumption(token, orderNumber);
//    }

//    回复催单接口
//    @RequestMapping("/replyReminder")
//    public AjaxResponseModel replyReminder(String orderNumber) {
//        return accountShopMessageAPIService.replyReminder(orderNumber);
//    }

    //商家app确认收货
    @RequestMapping("/confirmReceipt")
    public APIResponseModel confirmReceipt(APIRequestParams params){
        return accountShopMessageAPIService.confirmReceipt(params.getOrderNumber(),params.getToken());
    }


    //取消订单
    @RequestMapping("/cancelOrder")
    public APIResponseModel cancelOrder(APIRequestParams params){
        return accountShopMessageAPIService.cancelOrder(params.getOrderNumber(),params.getToken());
    }

    //配送按钮
    @RequestMapping("/deliveryOrder")
    public APIResponseModel deliveryOrder(APIRequestParams params){
        return accountShopMessageAPIService.deliveryOrder(params);
    }

}

