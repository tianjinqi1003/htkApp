package com.htkapp.core.utils;

/**
 * 全局常量类
 *
 * @author yangjingxi
 *         2016年11月28日
 */


public final class Globals {

    /*====================共同使用开始========================**/
    //默认密码
    public static final String DEFAULT_PWD = "123456";
    //默认加密token
    public static final String DEFAULT_SALT_TOKEN = "htkAppByYQL";
    //默认页码
    public static final int DEFAULT_PAGE_NO = 1;
    //默认每页记录数字
    public static final int DEFAULT_PAGE_LIMIT = 10;
    //外卖商户标识码
    public static final int DEFAULT_T_SHOP = 0;
    //团购商户标识码
    public static  final int DEFAULT_G_SHOP = 1;
    //自助点餐商户标识码
    public static final int DEFAULT_B_SHOP = 2;
    //后台逻辑异常
    public static final String BACK_CODE_LOGIC_ERROR_MESSAGE = "后台程序逻辑异常!";
    //异常出现
    public static final String BACK_CODE_THROW_EXCEPTION = "后台抛出异常";
    //传入参数不能为空
    public static final String PARAMETER_CANNOT_BE_EMPTY = "传入参数不能为空";
    //调用数据库层异常
    public static final String CALL_DATABASE_ERROR = "数据层抛出异常";
    //service层调用dao接口异常
    public static final String CALL_NULL_DATA_ERROR = "查询数据为空异常";
    //付款账户名
    public static final String PAYER_SHOW_NAME = "回头客平台";
    //更新失败
    public static final String DEFAULT_EXCEPTION_UPDATE_FAILED = "更新操作失败";
    //插入失败
    public static final String DEFAULT_EXCEPTION_INSERT_FAILED = "插入操作失败";
    //删除失败
    public static final String DEFAULT_EXCEPTION_DELETE_FAILED = "删除操作失败";
    //二维码加密码key
    public static final String encryptionKey = "yinqilei_ug@126.";
    //goEasy appKey
    public static final String goEasyKey = "BC-71831bbaf07c4ecba5a026bec6cc5f4e";
    //默认外卖订单下单成功状态
    public static final int DEFAULT_T_OrderState = 1;
    //默认团购订单下单成功状态
    public static final int DEFAULT_G_OrderState = 10;
    //默认外卖订单接单成功
    public static final int DEFAULT_T_CONFIRM_ORDER = 2;
    //默认外卖订单接单成功
    public static final int DEFAULT_T_ENTER_RECEIPT = 3;
    //默认外卖订单收货成功
    public static final int DEFAULT_T_ENTER_RECEIVING = 4;
    //默认外卖订单退款状态
    public static final int DEFAULT_T_OrderRefundState = 5;
    //默认团购订单退款状态
    public static final int DEFAULT_G_OrderRefundState = 12;
    //=================================返回码开始
    //参数错误代码
    public static final int COMMON_PARAMETER_ERROR = -2;
    //操作失败代码
    public static final int COMMON_OPERATION_FAILED = -1;
    //操作成功代码
    public static final int COMMON_SUCCESSFUL_OPERATION = 0;
    //成功刷新当前页
    public static final int COMMON_SUCCESS_AND_REFRESH_CURRENT_PAGE = 1;
    //成功并跳转url
    public static final int COMMON_SUCCESS_AND_JUMP_URL = 2;
    //成功并刷新iFrame的父界面
    public static final int COMMON_SUCCESS_SUCCESSFULLY_AND_REFRESH_PARENT_PARENT_PAGE_OF_IFRAME = 3;

    public static final int COMMON_SUCCESS_POP_WINDOW = 4;
    //=================================返回码结束

    //===================================支付宝开始
    //支付宝唯一用户号(以2088开头的16位纯数字组成)
    public static final String ALIPAY_USERID = "ALIPAY_USERID";
    //支付宝登陆号(邮箱和手机号格式)
    public static final String ALIPAY_LOGONID = "ALIPAY_LOGONID";
    //===================================支付宝结束

    //商户资金提现备注
    public static final String TRANSFER_CASH = "商户账单入账提现,交易单号：";
    /*====================共同使用结束========================**/



    /*=================================商户使用开始=====================================*/
    //=================================登陆状态码开始
    //登陆成功在线代码
    public static final int MERCHANT_LOGIN_ONLINE = 1;
    //退出成功离线代码
    public static final int MERCHANT_QUIT_OFFLINE = 0;
    //=================================登陆状态码结束

    //=================================cookieKey值开始
    //商户登陆成功保存到cookie中的用户名key
    public static final String MERCHANT_COOKIE_USER_NAME= "htk_merchant_userName";
    //商户登陆成功保存到cookie中的密码key
    public static final String MERCHANT_COOKIE_PASSWORD = "htk_merchant_password";
    //商户登陆成功保存到cookie中的RoleKey
    public static final String MERCHANT_COOKIE_ROLE = "htk_merchant_role";
    //=================================cookieKey值结束

    //=================================身份标识值开始
    //商户身份标识
    public static final String MERCHANT_USER = "S";
    //=================================身份标识值结束

    //=================================商户url开始
    //商户登陆页面url
    public static final String MERCHANT_LOGIN_URL = "merchant/login";
    //商户主页面url
    public static final String MERCHANT_INDEX_URL = "merchant/index";
    //=================================商户url结束

    //=================================商户登陆界面验证码KEY开始
    //商户登陆界面验证码key
    public static final String MERCHANT_KAPTCHAT_KEY = "MERCHANT_KAPTCHAT";
    //=================================商户登陆界面验证码KEY结束

    //================================商户sessionKey开始
    //商户 session 登录人
    public static final String MERCHANT_SESSION_USER = "merchantUser";
    //================================商户sessionKey结束

    //================================分页数开始
    public static final int MERCHANT_PAGE_LIMIT = 10;
    //================================分页数结束

    //================================评论分页开始
    public static final int MERCHANT_COMMENT_LIMIT = 3;
    //================================评论分页结束

    /*==================================商户使用结束=============================*/




    /* ===========================管理员开始========================================= */

    //====================================管理员登陆界面验证码key开始
    //管理员登陆界面验证码key
    public static final String ADMIN_KAPTCHAT_KEY = "ADMIN_KAPTCHAT";
    //====================================管理员登陆界面验证码key结束　

    //====================================管理员sessionKey开始
    //管理员 session　登陆人
    public static final String ADMIN_SESSION_USER = "adminUser";
    //====================================管理员sessionKey结束

    //=================================登陆状态码开始
    //登陆成功在线代码
    public static final int ADMIN_LOGIN_ONLINE = 1;
    //退出成功离线代码
    public static final int ADMIN_QUIT_OFFLINE = 0;
    //=================================登陆状态码结束

    //=================================cookieKey值开始
    //商户登陆成功保存到cookie中的用户名key
    public static final String ADMIN_COOKIE_USER_NAME= "htk_admin_userName";
    //商户登陆成功保存到cookie中的密码key
    public static final String ADMIN_COOKIE_PASSWORD = "htk_admin_password";
    //商户登陆成功保存到cookie中的RoleKey
    public static final String ADMIN_COOKIE_ROLE = "htk_admin_role";
    //=================================cookieKey值结束

    //=================================管理员url开始
    //管理员登陆页面url
    public static final String ADMIN_LOGIN_URL = "admin/login";

    //================================管理员url结束

    //=================================身份标识值开始
    //商户身份标识
    public static final String ADMIN_USER = "E";
    //=================================身份标识值结束
    /* ===========================管理员结束========================================= */



    //项目路径
    public static final String PROJECT_URL = "htkApp/";
    //图片访问根下路径
    public static final String PHOTO_URL = "upload/";
    //图标
    //菜品图片虚拟路径
    public static final String PRODUCT_IMG_URL = "productImgs/";
    //店铺图片虚拟路径
    public static final String SHOP_IMG_URL = "shopImgs/";
    //类别图片虚拟路径
    public static final String CATEGORY_IMG_URL = "categoryImgs/";
    //广告图片虚拟路径
    public static final String POSTER_IMG_URL = "posterImgs/";

    public static final String SHOPCATEGORY_IMG_UTL = "shopCategoryImgs/";
    //顾客
    public static final String  CUSTOMER_USER = "C";


    /*=========================app端使用开始====================================*/
    //===================================返回数据码开始
    //成功
    public static final int API_SUCCESS = 100;
    //失败
    public static final int API_FAIL = -99;
    //请求参数错误
    public static final int API_REQUEST_BAD = -98;
    //有header　经过拦截器异常
    public static final int API_INTERCEPTOR_HAVE_HEAD_ERROR = -1000;
    //没有header 经过拦截器异常
    public static final int API_INTERCEPTOR_NOT_HAVE_HEAD_ERROR = -1001;
    //return 报错　如果收到该代码，则代表后台代码逻辑处理异常
    public static final int API_BACK_CODE_LOGIC_ERROR = -1002;
    //===================================返回数据码结束

    //==============================分页页数开始
    //homePage分类分页开始页数
    public static final int API_HOME_PAGE_CATEGORY_NO = 1;
    //homePage分类分页结束页数
    public static final int API_HOME_PAGE_CATEGORY_LIMIT = 8;
    //==============================分页页数结束
    //===============================短信开始
    //短信请求url
    public static final String API_SMS_URL ="http://sms.kedaduanxin.com/SendSms.asp";
    //短信平台用户名
    public static final String API_SMS_USERNAME="dxjdcx";
    //短信平台密码
    public static final String API_SMS_PASSWORD="jdcx2015";
    //新的短信请求url
    public static final String NEW_API_SMS_URL ="http://www.ztsms.cn/sendNSms.do";
    //新的短信平台用户名
    public static final String NEW_API_SMS_USERNAME="qdhualing";
    //新的短信平台密码
    public static final String NEW_API_SMS_PASSWORD="VvMYl9ny";
    //===============================短信结束
    //===============================jPush开始
    public static final String JPUSH_APP_NAME = "回头客";
    //===============================jPush结束
    //===============================通知中心开始
    //下单成功通知
    public static final String NOTICE_CENTER_TITLE_XDCG = "您的订单已下单成功";
    //取消订单通知
    public static final String NOTICE_CENTER_TITLE_DDQX = "您的订单已取消";
    //商家拒单通知
    public static final String NOTICE_CENTER_TITLE_M_JUD = "您的订单已被商家拒单";
    //商家接单通知
    public static final String NOTICE_CENTER_TITLE_M_JID = "您的订单已被商家接单";
    //================================通知中心结束
    //==============================支付类型标识开始
    //外卖支付
    public static final String API_PAY_TAKEOUT = "外卖支付";
    //团购支付
    public static final String API_PAY_GROUP_BUY = "团购支付";
    //自助支付
    public static final String API_PAY_BUFFET_FOOD = "自助点餐";
    //支付宝支付ＢＯＤＹ
    public static final String Ali_BODY = "回头客app支付宝支付";
    //支付宝超时时间
    public static final String API_PAY_TIMEOUT = "30m";
    //产品码(外卖)
    public static final String API_PAY_PRODUCT_CODE_TAKEOUT = "QUICK_TAKEOUT_PAY";
    //产品码(团购)
    public static final String API_PAY_PRODUCT_CODE_GROUT_BUY = "QUICK_GROUP_BUY_PAY";
    //产品码（自助）
    public static final String API_PAY_PRODUCT_CODE_BUFFET_FOOD = "QUICK_BUFFET_FOOD_PAY";
    //==============================支付类型标识结束
    //==============================app Html页面保存打开商铺id Session 开始
    //从app打开页面时传入商铺id,保存为session
    public static final String API_HTML_SESSION_SHOP_ID = "htmlShopId";
    //==============================app Html页面保存打开商铺id Session 结束

    //app用户网页session保存开始
    public static final String API_HTML_SESSION = "app_session";
    //app用户网页session保存结束

    /*==========================app端使用结束======================================*/
}
