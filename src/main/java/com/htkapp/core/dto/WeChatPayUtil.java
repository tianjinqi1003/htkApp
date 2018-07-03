package com.htkapp.core.dto;

import com.htkapp.core.config.WechatConfig;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.wxpaySDK.WXPay;
import com.htkapp.core.wxpaySDK.WXPayConfigImpl;
import com.htkapp.modules.API.dto.ReturnCallUpWeChatData;
import com.htkapp.modules.API.web.PaymentInterfaceAPI;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.htkapp.core.OtherUtils.*;
import static com.htkapp.core.utils.OrderNumGen.getOutRefundNo;
import static com.htkapp.core.wxpaySDK.WXPayUtil.MD5;

@Component
public class WeChatPayUtil {

    private WXPay wxpay;

    public WeChatPayUtil() throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
    }

    //调起微信支付
    public ReturnCallUpWeChatData callUpWeChatInterface(String accountIp, double orderAmount, int mark, String out_trade_no) throws Exception {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", WechatConfig.TRADE_TYPE);
        data.put("fee_type", "CNY");
        data.put("total_fee", doubleToString(orderAmount));
        data.put("spbill_create_ip", accountIp);
        data.put("notify_url", WechatConfig.NOTIFY_URL);
        data.put("trade_type", "APP");
        if (mark == 0) {
            data.put("body", WechatConfig.WX_BODY + Globals.API_PAY_TAKEOUT);
        } else if (mark == 1) {
            data.put("body", WechatConfig.WX_BODY + Globals.API_PAY_GROUP_BUY);
        } else if (mark == 2) {
            data.put("body", WechatConfig.WX_BODY + Globals.API_PAY_BUFFET_FOOD);
        } else {
            data.put("body", WechatConfig.WX_BODY + "未知的支付请求");
        }

        try {
            Map<String, String> result = wxpay.unifiedOrder(data);
            if (result.get("return_code").equals("SUCCESS")) {
                //创建预支付订单成功
                ReturnCallUpWeChatData callUpWeChatData = new ReturnCallUpWeChatData();
                callUpWeChatData.setAppId(result.get("appid"));
                callUpWeChatData.setOrderNumber(out_trade_no);
                callUpWeChatData.setMchId(result.get("mch_id"));
                callUpWeChatData.setNonceStr(result.get("nonce_str"));
                callUpWeChatData.setPrePayId(result.get("prepay_id"));
                callUpWeChatData.setTimestamp(getCurTime());
                callUpWeChatData.setOrderBody(data.get("body"));  //返回orderBody
                //签名需要再次生成后返回给app
                String A = "appid=" + result.get("appid")
                        + "&noncestr=" + result.get("nonce_str")
                        + "&package=" + "Sign=WXPay"
                        + "&partnerid=" + result.get("mch_id")
                        + "&prepayid=" + result.get("prepay_id")
                        + "&timestamp=" + getCurTime();
                String signTemp = A + "&key=" + WechatConfig.APP_SECRET; //注：key为商户平台设置的密钥key
                String sign = MD5(signTemp).toUpperCase();
                callUpWeChatData.setSign(sign);
                return callUpWeChatData;
            } else {
                throw new Exception(result.get("return_code"));
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    //关闭订单
    public String closeOrder() {
        return null;
    }

    //申请退款
    public Map<String, Object> refund(String orderNumber, Double orderAmount, String content) throws Exception {
        Map<String, String> data = new HashMap<>();
        Map<String, Object> returnData = new HashMap<>();
        String refundNumber = getOutRefundNo("wx_rf_");
        data.put("out_trade_no", orderNumber);
        data.put("total_fee", doubleToString(orderAmount));
        data.put("refund_fee", wxPayDeductRate(orderAmount));
        data.put("out_refund_no", refundNumber);
        data.put("refund_desc", content);
        try {
            Map<String, String> result = wxpay.refund(data);
            System.out.println("退款金额是===========" + orderAmount);
            System.out.println("扣除费率后的金额是==============" + wxPayDeductRate(orderAmount));
            if (result.get("result_code").equals("SUCCESS")) {
                returnData.put("result", true);
                returnData.put("out_trade_no", orderNumber);
                returnData.put("total_fee", orderAmount);
                returnData.put("refund_fee", wxPayDeductRate(orderAmount));
                returnData.put("out_refund_no", refundNumber);
                returnData.put("refund_desc", content);
            } else {
                returnData.put("result", false);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return returnData;
    }

    //查询订单接口
    public void orderQuery() throws Exception {
        //微信单号
        String transactionId = "";
        //商户订单号
        String outTradeNo = "";
        Map<String, String> data = new HashMap<>();
        //微信订单号 二选一
        data.put("transaction_id", transactionId);
        //商户订单号 二选一
//      data.put("out_trade_no","");
        try {
            Map<String, String> result = wxpay.orderQuery(data);
            if (result.get("return_code").equals("SUCCESS")) {
                System.out.println("查询订单成功");
            } else {
                System.out.println("查询订单失败");
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    //查询退款
    public void refundQuery() throws Exception {
        Map<String, String> data = new HashMap<>();
        //四选一
        //微信订单号，商户订单号,商户退款单号，微信退款单号
        String outRefundNo = "";
        data.put("out_refund_no", outRefundNo);
        try {
            Map<String, String> result = wxpay.refundQuery(data);
            if (result.get("return_code").equals("SUCCESS")) {
                //成功
            } else {
                //失败
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    //对账单下载
    public void downloadBill() throws Exception {

        Map<String, String> data = new HashMap<>();
        data.put("", "");
        try {
            wxpay.downloadBill(data);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    //对从微信服务器拿到的数据再次签名
    public void signAppPayData() {

    }
}
