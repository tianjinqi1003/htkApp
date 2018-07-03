package com.htkapp.core.dto;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.OpenApiRoyaltyDetailInfoPojo;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.htkapp.core.config.AlipayConfig;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.merchant.pay.dto.CallUpAliPayReturnData;

import java.util.HashMap;
import java.util.Map;

import static com.htkapp.core.OtherUtils.aliPayDeductRate;
import static com.htkapp.core.utils.OrderNumGen.getOutRefundNo;


/**
 * Created by yinqilei on 17-6-28.
 * 生成支付宝加签方法
 */

public class AliPayUtil {

    private static int number = 0;

    //根据传入的交易信息生成加签的支付宝支付字符串
    public static CallUpAliPayReturnData generateAlipayPaymentInformation(double price, int mark, String orderNumber) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, "json",
                AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        String orderBody = "";
        if (mark == 0) {
            model.setSubject(Globals.API_PAY_TAKEOUT);
            model.setProductCode(Globals.API_PAY_PRODUCT_CODE_TAKEOUT);
            orderBody = Globals.Ali_BODY + Globals.API_PAY_TAKEOUT;
        } else if (mark == 1) {
            model.setSubject(Globals.API_PAY_GROUP_BUY);
            model.setProductCode(Globals.API_PAY_PRODUCT_CODE_GROUT_BUY);
            orderBody = Globals.Ali_BODY + Globals.API_PAY_GROUP_BUY;
        } else if (mark == 2) {
            model.setSubject(Globals.API_PAY_BUFFET_FOOD);
            model.setProductCode(Globals.API_PAY_PRODUCT_CODE_BUFFET_FOOD);
            orderBody = Globals.Ali_BODY + Globals.API_PAY_BUFFET_FOOD;
        } else {
            model.setSubject("未知的支付请求");
            orderBody = Globals.Ali_BODY + "未知的支付请求";
        }
        if (StringUtils.isNotEmpty(orderNumber)) {
            model.setOutTradeNo(orderNumber);
        }
        model.setBody(orderBody);
        model.setTimeoutExpress(Globals.API_PAY_TIMEOUT);
        model.setTotalAmount(String.valueOf(price));
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);
        try {
            String aliPayResponseBody = alipayClient.sdkExecute(request).getBody();
            return new CallUpAliPayReturnData(orderNumber, aliPayResponseBody, orderBody);
        } catch (AlipayApiException e) {
            throw new AlipayApiException("调用支付宝加签失败");
        }
    }

    //支付宝退款接口
    public static Map<String, Object> aliPayRefundInterface(String orderNumber, Double amount,
                                                            String reason) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        System.out.println("退款金额是===========" + amount);
        System.out.println("扣除费率后的金额是==============" + aliPayDeductRate(amount));
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        String outRequestNo = getOutRefundNo("ali_rf_");
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + orderNumber + "\"," +
                "\"refund_amount\":" + aliPayDeductRate(amount) + "," +
                "\"refund_reason\":\"" + reason + "\"," +
                "\"out_request_no\":\"" + outRequestNo + "\"," +
                "\"store_id\":\"NJ_S_001\"," +
                "\"terminal_id\":\"NJ_T_001\"" +
                "  }");
        Map<String, Object> returnData = new HashMap<>();
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("退款调用成功");
                returnData.put("result", true);
                returnData.put("out_trade_no", orderNumber);
                returnData.put("total_fee", amount);
                returnData.put("refund_fee", aliPayDeductRate(amount));
                returnData.put("out_refund_no", outRequestNo);
                returnData.put("refund_desc", reason);
                returnData.put("content", "退款原因:" + reason);
            } else {
                System.out.println("退款调用失败");
                returnData.put("result", false);
            }
        } catch (AlipayApiException e) {
            throw new AlipayApiException();
        }
        return returnData;
    }


    //支付宝单笔转账接口
    public static Map<String, String> transfer(AliPayRequest aliPayRequest) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();

        //付款方姓名
        String payerShowName = Globals.PAYER_SHOW_NAME;
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + aliPayRequest.getOutBizNo() + "\"," +
                "\"payee_type\":\"" + aliPayRequest.getPayeeType() + "\"," +
                "\"payee_account\":\"" + aliPayRequest.getPayeeAccount() + "\"," +
                "\"amount\":\"" + aliPayRequest.getAmount() + "\"," +
                "\"payer_show_name\":\"" + payerShowName + "\"," +
                "\"remark\":\"" + aliPayRequest.getRemark() + "\"" +
                "  }");
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            Map<String, String> returnData = new HashMap<>();
            if (response.isSuccess()) {
                System.out.println("调用成功");
                returnData.put("result","true");
                returnData.put("out_biz_no", aliPayRequest.getOutBizNo());
                returnData.put("payee_type",aliPayRequest.getPayeeType());
                returnData.put("payee_account",aliPayRequest.getPayeeAccount());
                returnData.put("amount",aliPayRequest.getAmount().toString());
                returnData.put("remark",aliPayRequest.getRemark());
                return returnData;
            } else {
                System.out.println("调用失败");
                returnData.put("result","false");
                return returnData;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //转账查询接口


    //统一收单线下交易预创建
    //(收银员通过收银台或商户后台调用支付宝接口，生成二维码，展示给用户，由用扫描二维码完成订单支付)


    //统一收单交易退款查询接口
    public void refundQuery() throws Exception {

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        //支付宝交易号
        String tradeNO = "";
        //商户支付订单号
        String outTradeNo = "";
        //退款时传入的退款订单号
        String outRequestNo = "";

        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

        request.setBizContent("{" +
                "\"trade_no\":\"20150320010101001\"," +
                "\"out_trade_no\":\"2014112611001004680073956707\"," +
                "\"out_request_no\":\"2014112611001004680073956707\"" +
                "  }");
        try {
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }


    //统一收单交易结算接口
    public void orderSettle() throws Exception {

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();

        //结算请求流水号
        String outRequestNo = "";
        //支付宝订单号
        String tradeNo = "";
        //分账收入账号
        String transIn = "";
        //分账支出账号
        String transOut = "";
        //分账金额
        long amount = 1;
        //分账百分比
        long amountPercentage = 100;
        //分账描述
        String desc = "";
        OpenApiRoyaltyDetailInfoPojo infoPojo = new OpenApiRoyaltyDetailInfoPojo();
        //分账支出账号
        infoPojo.setTransOut(transOut);
        //分账收入账号
        infoPojo.setTransIn(transIn);
        //分账金额
        infoPojo.setAmount(amount);
        //分账百分比
        infoPojo.setAmountPercentage(amountPercentage);
        //分账描述
        infoPojo.setDesc(desc);
        request.setBizContent("{" +
                "\"out_request_no\":\"" + outRequestNo + "\"," +
                "\"trade_no\":\"" + tradeNo + "\"," +
                "      \"royalty_parameters\":" + infoPojo + "" +
                "\"operator_id\":\"A0001\"" +
                "  }");
        try {
            AlipayTradeOrderSettleResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    //

}
