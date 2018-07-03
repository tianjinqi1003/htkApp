package com.htkapp.core.config;

/**
 * Created by yinqilei on 17-7-12.
 * 微信配置
 */

public class WechatConfig {

    //预支付请求地址
    public static final String PrepayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    // 查询订单地址
    public static final String OrderUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

    //关闭订单地址
    public static final String CloseOrderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";

    //申请退款地址
    public static final String RefundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    //appId
    public static final String APP_ID = "wxb872a94f23cc21a0";

    //app秘钥
    public static final String APP_SECRET = "aqkdlpondbzSkpNdopMMqaHJKLpoKjLm";

    //商户id
    public static final String MCH_ID = "1485611012";

    public static final String WX_BODY = "回头客app微信支付";

    //    public static final String NOTIFY_URL = "http://1704aa0586.51mypc.cn:32351/htkApp/API/paymentInterfaceAPI/weChatPayNotifyInterface";
    public static final String NOTIFY_URL = "http://120.27.5.36:8080/htkApp/API/paymentInterfaceAPI/weChatPayNotifyInterface";

    public static final String TRADE_TYPE = "APP";
}
