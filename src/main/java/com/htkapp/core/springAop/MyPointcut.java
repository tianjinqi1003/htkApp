package com.htkapp.core.springAop;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.API.dto.ReturnCallUpWeChatData;
import com.htkapp.modules.merchant.pay.dto.CallUpAliPayReturnData;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import org.aspectj.lang.JoinPoint;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class MyPointcut {

    @Resource
    MoreMethodsUtils methodsUtils;
    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private AccountShopServiceI accountShopService;

    public void beforeMethod(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        List<Object> args = Arrays.asList(joinPoint.getArgs());
//        System.out.println("ValidationAspect this method " + methodName + " begin. param<" + args + ">");
        System.out.println("前置通知");
    }

    //通知消息切入点方法
    public void notifyMeg(JoinPoint joinPoint, Object retVal) throws Exception {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        int orderId = 0;
        if (retVal instanceof APIResponseModel) {
            APIResponseModel apiResponseModel = (APIResponseModel) retVal;
            if (apiResponseModel.getData() instanceof CallUpAliPayReturnData) {
                CallUpAliPayReturnData ali = (CallUpAliPayReturnData) apiResponseModel.getData();
                orderId = ali.getOrderId();
            } else if (apiResponseModel.getData() instanceof ReturnCallUpWeChatData) {
                ReturnCallUpWeChatData wx = (ReturnCallUpWeChatData) apiResponseModel.getData();
                orderId = wx.getOrderId();
            } else if (apiResponseModel.getData() instanceof Integer) {
                orderId = (Integer) apiResponseModel.getData();
            }
        } else if (retVal instanceof AjaxResponseModel) {
            //取参数值
            String orderNumber = (String) args.get(0);
            OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
            orderId = order.getId();
        }
        if(orderId == 0){
            return;
        }
        OrderRecord order = orderRecordService.getOrderRecordById(orderId);
        String mesTitle = "";
        String mesCategory = "";
        JSONObject jsonObject = new JSONObject();
        if (order != null) {
            mesTitle = order.getOrderState() >= 10 ? "团购" : "外卖";
            char category = order.getOrderState() >= 10 ? 'g' : 't';
            if (order.getOrderState() != 0) {
                switch (order.getOrderState()) {
                    case 1:
                        mesCategory = "支付成功，等待接单";
                        break;
                    case 2:
                        mesCategory = "已接单";
                        break;
                    case 3:
                        mesCategory = "已开始配送";
                        break;
                    case 4:
                        mesCategory = "已确认收货";
                        break;
                    case 5:
                        mesCategory = "取消成功";
                        break;
                    case 6:
                        mesCategory = "催单成功";
                        break;
                    case 10:
                        mesCategory = "下单成功,等待消费";
                        break;
                    case 11:
                        mesCategory = "已消费";
                        break;
                    case 12:
                        mesCategory = "已取消";
                        break;
                    default:
                        break;
                }
                jsonObject.put("orderNumber", order.getOrderNumber());
                jsonObject.put("orderState", order.getOrderState());
                jsonObject.put("orderId", orderId);
                AccountShop accountShop = accountShopService.getAccountShopDataById(shopService.getShopDataById(order.getShopId()).getAccountShopId());
                String mToken = accountShop.getToken();
                int accountShopId = accountShop.getId();
                methodsUtils.jPushToMerAndAccount(order.getToken(), mesTitle + mesCategory, jsonObject.toJSONString(), mToken, order.getOrderNumber() + mesCategory, jsonObject.toJSONString(), 2);
//                if(order.getOrderState() == 1 || order.getOrderState() == 4 || order.getOrderState() ==){
                methodsUtils.pushMesToManagePage(new PushMesEntity(mesTitle + "订单消息", String.valueOf(category), "有订单" + mesCategory + "了", mToken, category, order.getOrderState(), "您有一个的" + mesTitle + "订单消息", accountShopId));
//                }
            } else {
                return;
            }
        }
    }


}
