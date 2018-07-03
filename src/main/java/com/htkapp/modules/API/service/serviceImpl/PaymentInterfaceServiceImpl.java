package com.htkapp.modules.API.service.serviceImpl;

import com.alipay.api.internal.util.AlipaySignature;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.config.AlipayConfig;
import com.htkapp.core.config.WechatConfig;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.dto.AliPayUtil;
import com.htkapp.core.dto.WeChatPayUtil;
import com.htkapp.core.params.ServiceParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.core.wxpaySDK.WXPayUtil;
import com.htkapp.modules.API.dto.ReturnCallUpWeChatData;
import com.htkapp.modules.API.service.APICommonService;
import com.htkapp.modules.API.service.PaymentInterfaceService;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderProductService;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderService;
import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import com.htkapp.modules.merchant.integral.service.AccountTicketListService;
import com.htkapp.modules.merchant.pay.dto.CallUpAliPayReturnData;
import com.htkapp.modules.merchant.pay.dto.EnterPayReturn;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.entity.OrderRefund;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.pay.service.OrderRefundService;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

/**
 * Created by yinqilei on 17-6-29.
 */

@Service
public class PaymentInterfaceServiceImpl implements PaymentInterfaceService {

    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private AccountTicketListService accountTicketListService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private OrderRefundService orderRefundService;
    @Resource
    private BuffetFoodOrderService buffetFoodOrderService;
    @Resource
    private BuffetFoodOrderProductService buffetFoodOrderProductService;
    @Resource
    private MoreMethodsUtils methodsUtils;
//    @Resource
//    private WeChatPayUtil wxPay = new WeChatPayUtil();
    @Resource
    private APICommonService apiCommonService;
    @Resource
    private OtherUtils otherUtilsServiceImpl;


    /* =================接口开始========================= */
    //调起外卖支付宝支付接口
    @Override
    public APIResponseModel callUpPaymentInterfaceHTK(APIRequestParams params, OrderRecord orderRecord) {
        if (params != null && orderRecord != null) {
            try {

                String out_trade_no = OrderNumGen.next().toString();
                orderRecord.setOrderNumber(out_trade_no);
                orderRecord.setOrderTime(format(new Date(), NORM_DATETIME_PATTERN));
                //根据类flag 判断是微信还是支付宝
                if (params.getFlag() == 1) {
                    //支付宝
                    CallUpAliPayReturnData result = AliPayUtil.generateAlipayPaymentInformation(orderRecord.getOrderAmount(), orderRecord.getMark(), out_trade_no);
                    orderRecord.setOrderBody(result.getOrderBody());
                    orderRecord.setPaymentMethod(0);
                    if (orderRecord.getMark() == 0) {
                        //外卖
                        apiCommonService.takeoutPaymentInterface(orderRecord);
                    } else if (orderRecord.getMark() == 1) {
                        //团购
                        apiCommonService.groupBuyPaymentInterface(orderRecord, params.getToken());
                    }
                    OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(out_trade_no);
                    result.setOrderId(order.getId());
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", result);
                } else if (params.getFlag() == 2 && params.getAppIp() != null) {
                    //微信  new 创建一个对象
                    WeChatPayUtil wxPay = new WeChatPayUtil();
                    ReturnCallUpWeChatData result = wxPay.callUpWeChatInterface(params.getAppIp(), orderRecord.getOrderAmount(), orderRecord.getMark(), out_trade_no);
                    orderRecord.setOrderBody(result.getOrderBody());
                    orderRecord.setPaymentMethod(1);
                    if (orderRecord.getMark() == 0) {
                        //外卖
                        apiCommonService.takeoutPaymentInterface(orderRecord);
                    } else if (orderRecord.getMark() == 1) {
                        //团购
                        apiCommonService.groupBuyPaymentInterface(orderRecord, params.getToken());
                    }
                    OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(out_trade_no);
                    result.setOrderId(order.getId());
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", result);
                }
            } catch (Exception e1) {
                return new APIResponseModel<>(Globals.API_FAIL);
            }
            return new APIResponseModel<>(Globals.API_FAIL);
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //调起退款接口
    @Override
    @Transactional
    public APIResponseModel callUpRefundInterfaceHTK(APIRequestParams params, String orderNumber) {
        if (StringUtils.isNotEmpty(orderNumber)) {
            try {
                OrderRecord record = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                if (record == null) {
                    return new APIResponseModel(Globals.API_FAIL, "订单不存在");
                } else {
                    if (record.getOrderState() == 0) {
                        Shop shop = shopService.getShopDataById(record.getShopId());
                        if(shop != null){
                            AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                            String startTime = format(DateUtil.beginOfDay(DateUtil.parse(record.getOrderTime())), NORM_DATETIME_PATTERN);
                            String endTime = format(DateUtil.endOfDay(DateUtil.parse(record.getOrderTime())), NORM_DATETIME_PATTERN);
                            apiCommonService.updateBillData(new ServiceParams(accountShop.getToken(), startTime, endTime, orderNumber, record.getOrderAmount()));

                            /**
                             * @author 马鹏昊
                             * @desc 取消订单之后退还用户优惠券（如果有用到的话）
                             */
                            //获取这个订单用到的优惠券信息（只会找到一个）
                            List<AccountTicketList> ticketLists = accountTicketListService.getTicketListByTokenAndCouponId( params.getToken(),record.getCouponId());
                            //如果用到了的话
                            if (ticketLists!=null&&ticketLists.size()>0) {
                                AccountTicketList coupon = ticketLists.get(0);
                                int nowQuantity = coupon.getQuantity();
                                accountTicketListService.updateTicketListByTokenAndCouponIdDAO(nowQuantity + 1, params.getToken(), record.getCouponId());
                            }


                            return new APIResponseModel<>(Globals.API_SUCCESS, "取消成功", record.getId());
                        }else {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } else {
                        boolean result = false;
                        int stateId = record.getMark() == 0 ? Globals.DEFAULT_T_OrderRefundState : (record.getMark() == 1 ? Globals.DEFAULT_G_OrderRefundState : 99);
                        Map<String, Object> resultData = new HashMap<>();
                        if (record.getPaymentMethod() == 0) {
                            resultData = AliPayUtil.aliPayRefundInterface(orderNumber, record.getOrderAmount(), params.getContent());
                            result = (boolean) resultData.get("result");
                        } else if (record.getPaymentMethod() == 1) {
                            WeChatPayUtil wxPay = new WeChatPayUtil();
                            resultData = wxPay.refund(record.getOrderNumber(), record.getOrderAmount(), params.getContent());
                            result = (boolean) resultData.get("result");
                        }
                        if (result) {
                            boolean isSuccess = orderRecordService.changeOrderStateByOrderNumber(orderNumber, stateId);
                            if (isSuccess) {
                                Shop shop = shopService.getShopDataById(record.getShopId());
                                AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                                OrderRefund orderRefund = new OrderRefund()
                                        .setOrderNumber(record.getOrderNumber())
                                        .setOutRefundNo((String) resultData.get("out_refund_no"))
                                        .setPayWay(record.getPaymentMethod())
                                        .setTotalFee(String.valueOf(resultData.get("total_fee")))
                                        .setRefundFee(String.valueOf(resultData.get("refund_fee")))
                                        .setContent((String) resultData.get("refund_desc"));
                                orderRefundService.insertRefund(orderRefund);
                                String startTime = format(DateUtil.beginOfDay(DateUtil.parse(record.getOrderTime())), NORM_DATETIME_PATTERN);
                                String endTime = format(DateUtil.endOfDay(DateUtil.parse(record.getOrderTime())), NORM_DATETIME_PATTERN);
                                apiCommonService.updateBillData(new ServiceParams(accountShop.getToken(), startTime, endTime, orderNumber, record.getOrderAmount()));

                                /**
                                 * @author 马鹏昊
                                 * @desc 取消订单之后退还用户优惠券（如果有用到的话）
                                 */
                                //获取这个订单用到的优惠券信息（只会找到一个）
                                List<AccountTicketList> ticketLists = accountTicketListService.getTicketListByTokenAndCouponId( params.getToken(),record.getCouponId());
                                //如果用到了的话
                                if (ticketLists!=null&&ticketLists.size()>0) {
                                    AccountTicketList coupon = ticketLists.get(0);
                                    int nowQuantity = coupon.getQuantity();
                                    accountTicketListService.updateTicketListByTokenAndCouponIdDAO(nowQuantity + 1, params.getToken(), record.getCouponId());
                                }

                                //推送消息到页面
                                return new APIResponseModel<>(Globals.API_SUCCESS, "取消成功", record.getId());
                            } else {
                                return new APIResponseModel(Globals.API_FAIL);
                            }
                        } else {
                            return new APIResponseModel(Globals.API_FAIL, "取消失败");
                        }
                    }
                }
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //自助点餐确认下单接口(生成单号，插入订单信息到订单表中)
    @Override
    public APIResponseModel enterBuffetFoodSuccessfullyTransferred(BuffetFoodOrder order) {
        if (order != null) {
            try {
                String orderTime = format(new Date(), NORM_DATETIME_PATTERN);
                final String orderNumber = String.valueOf(OrderNumGen.next());
                order.setOrderNumber(orderNumber);
                order.setOrderTime(orderTime);
                order.setOrderBody("自助点餐下单");
                try {
                    Map<String, String> resultMap = buffetFoodOrderService.insertOrder(order);
                    boolean result = Boolean.parseBoolean(resultMap.get("result"));
                    if (result) {
                        Integer orderId = Integer.parseInt(resultMap.get("orderId"));
                        for (BuffetFoodOrderProduct each : order.getProductLists()) {
                            each.setOrderId(orderId);
                            buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
                        }
                        Shop shop = shopService.getShopDataById(order.getShopId());
                        AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                        methodsUtils.pushMesToManagePage(new PushMesEntity(
                                "自助点餐订单消息", "b", "你有一个新的订单", accountShop.getToken(),
                                'b', 1, "您有新的自助点餐订单", accountShop.getId()
                        ));
                        EnterPayReturn enterPayReturn = new EnterPayReturn(order.getId(), orderTime, order.getOrderNumber());
                        Jpush.jPushMethod(order.getToken(), "自助点餐订单下单成功", "ALERT");
                        Jpush.jPushMethodToMerchant(accountShop.getToken(), "您有新的自助点餐订单", "ALERT","商家版");
                        return new APIResponseModel<EnterPayReturn>(Globals.API_SUCCESS, "成功", enterPayReturn);
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, "下单失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }


    //接微信服务端异步通知
    @Override
    public String weChatPayNotifyUrl() {
        BufferedReader reader = null;
        Map<String, String> map = new HashMap<>();
        String XMLMapString = null;
        try {
            HttpServletRequest request = otherUtilsServiceImpl.getRequestByMethod();
            reader = request.getReader();
            String line = "";
            String xmlString = null;
            StringBuilder inputString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                inputString.append(line);
            }
            xmlString = inputString.toString();
            request.getReader().close();
            System.out.println("接收到的数据如下:------" + xmlString);
            //验证签名
            boolean checkSignResult = WXPayUtil.isSignatureValid(xmlString, WechatConfig.APP_SECRET);
            if (checkSignResult) {
                //更新订单状态
                Map<String, String> respData = WXPayUtil.xmlToMap(xmlString);
                //先根据拿到的订单号查找订单状态，如果已处理过则不再处理，未处理过则再处理
                String outTradeNo = respData.get("out_trade_no");  //微信服务返回的商户订单号
                OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(outTradeNo);
                if (order != null) {
                    if (order.getOrderState() == 0) {
                        apiCommonService.insertBillData(new ServiceParams(order.getOrderNumber(),order.getMark()));
                    }
                }
                map.put("return_code", "SUCCESS");
            } else {
                map.put("return_code", "FAIL");
                map.put("return_msg", "验证签名失败");
            }
            XMLMapString = WXPayUtil.mapToXml(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return XMLMapString;
    }

    //接支付宝服务端异步通知
    @Override
    public String aliPayNotifyUrl(HttpServletRequest request) {
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Object o : requestParams.keySet()) {
                String name = (String) o;
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
            }
            //验证数据
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
            if (flag) {
                if (params.get("trade_status").equals("TRADE_SUCCESS")) {
                    //交易成功，且可对该交易做操作，如：多级分润、退款等。   改变订单状态
                    String orderNumber = params.get("out_trade_no");
                    //根据订单号查询当前订单状态
                    OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                    if (order != null) {
                        if (order.getOrderState() == 0) {
                            apiCommonService.insertBillData(new ServiceParams(order.getOrderNumber(),order.getMark()));
                            return "success";
                        }
                    }
                    System.out.println("订单交易成功");
                } else if (params.get("trade_status").equals("WAIT_BUYER_PAY")) {
                    //交易创建，等待买家付款。
                    System.out.println("订单等待付款");
                } else if (params.get("trade_status").equals("TRADE_CLOSED")) {
                    //在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易。
                    System.out.println("订单关闭");
                } else if (params.get("trade_status").equals("TRADE_FINISHED")) {
                    //交易成功且结束，即不可再做任何操作。
                    System.out.println("订单已完成");
                }
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /* =======================接口结束========================== */
}
