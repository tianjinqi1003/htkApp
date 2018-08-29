package com.htkapp.core;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.dto.WeChatPayUtil;
import com.htkapp.core.redisCache.BaseRedisCache;
import com.htkapp.core.redisCache.JedisServiceCache;
import com.htkapp.core.solr.BaseSolr;
import com.htkapp.core.springAop.methodService.methodServiceImpl.MoreMethodsServiceImpl;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.dto.CommentListInfo;
import com.htkapp.modules.merchant.common.service.ShopMessageCommentService;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.core.shiro.common.utils.MathUtil;
import com.xiaoleilu.hutool.crypto.symmetric.AES;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.goeasy.GoEasy;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.htkapp.core.utils.FileUploadUtils.getFTPClient;
import static com.htkapp.core.utils.FileUploadUtils.uploadFileForFTP;
import static com.xiaoleilu.hutool.crypto.SecureUtil.aes;
import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATE_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

public class OtherUtils {

    private GetIdUtil getIdUtil;

    private MoreMethodsUtils methodsUtils;

    @Resource
    private ShopMessageCommentService messageCommentService;

    public static final double rate = 0.006;
    private MoreMethodsServiceImpl moreMethodsServiceImpl;
    private WeChatPayUtil wxPay;
    private JedisServiceCache jedisServiceCache;
    private BaseRedisCache baseRedisCache;
    private BaseSolr baseSolr;

    //spring set注入
    public void setGetIdUtil(GetIdUtil getIdUtil) {
        this.getIdUtil = getIdUtil;
    }

    //构造方法注入
    public OtherUtils(MoreMethodsUtils moreMethodsUtils) {
        this.methodsUtils = moreMethodsUtils;
    }

    public void setMoreMethodsServiceImpl(MoreMethodsServiceImpl moreMethodsServiceImpl) {
        this.moreMethodsServiceImpl = moreMethodsServiceImpl;
    }

    public MoreMethodsServiceImpl getMoreMethodsServiceImpl() {
        return moreMethodsServiceImpl;
    }

    //原因是：chrome浏览器可以识别出Base64数据为图片，Firefox无法识别为图片 生成二维码数据
    public static String getImgUrl(String id, String folder, int flag) throws Exception {
        try {
            long time = System.currentTimeMillis();
            JSONObject jsonObject = new JSONObject();
            String shopIdStr = encryptString(id);
            jsonObject.put("shopKey", shopIdStr);
            jsonObject.put("type", flag);  //0关注－收藏   1自助点餐
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, 0);  //margin 边框距离
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new QRCodeWriter().encode(jsonObject.toJSONString(), BarcodeFormat.QR_CODE, 500, 500, hints);
            String fileName = "shop_" + id + "_" + time + ".png";
            String writeTempPath = "D:\\resource";
            File file = new File(writeTempPath, fileName);
            Path path = file.toPath();
            try {
                MatrixToImageWriter.writeToPath(matrix, "PNG", path);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            FTPClient client = getFTPClient(FTPConfig.host, FTPConfig.port_qrcode, FTPConfig.userName, FTPConfig.password);
            uploadFileForFTP(client, fileName, writeTempPath + "/" + fileName, "Resource\\htkApp\\upload\\" + folder);
            String rootPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder;
            return rootPath + fileName;
        } catch (Exception e) {
            return null;
        }
    }

    //获取数据转二维码图片的url地址
    public static String getDataQrImgUrl(String data, String folder) {
        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            HttpSession session = request.getSession();
//            String getStorePath = "F:\\yinqilei\\workspace\\huitoukezhongzuoxiangmu\\src\\main\\webapp\\upload";
//            String getStorePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\htkApp\\upload";
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, 0);  //margin 边框距离
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500, hints);
//            String getPath = session.getServletContext().getRealPath("/upload") + folder;
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time) + ".png";
            String writeTempPath = "D:\\resource";
            File file = new File("D:\\resource", timeStr);
            Path path = file.toPath();
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
            FTPClient client = getFTPClient(FTPConfig.host, FTPConfig.port_dq, FTPConfig.userName, FTPConfig.password);
            uploadFileForFTP(client, timeStr, writeTempPath + "\\" + timeStr, "Resource\\htkApp\\upload\\" + folder);
            String rootPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder;
            return rootPath + timeStr;
        } catch (Exception e) {
            return null;
        }
    }

    //获取根路径
    public static String getRootDirectory() {
        return "http://120.27.5.36:8080/";
    }

    //获取当前项目路径
    public static String getCurRootDirectory() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuffer stringBuffer = request.getRequestURL();
        return stringBuffer.substring(0,stringBuffer.indexOf(Globals.PROJECT_URL));
    }

    //获取开始页码
    public static int getPageByKey(HttpServletRequest request, String key) {
        int pageData = 0;
        if (key.equals("pageSize")) {
            String pageSize = request.getParameter("pageSize"); // 页码
            pageData = Globals.DEFAULT_PAGE_NO;
            if (StringUtils.isNotEmpty(pageSize)) {
                pageData = Integer.parseInt(pageSize);
            }
        } else if (key.equals("limit")) {
            String limit = request.getParameter("limit"); // 每行显示
            pageData = Globals.DEFAULT_PAGE_LIMIT;
            if (StringUtils.isNotEmpty(limit)) {
                pageData = Integer.parseInt(limit);
            }
        }
        return pageData;
    }

    //通过传入的request取session中的商户信息
    public static LoginUser getLoginUserByRequest() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        LoginUser curUser = (LoginUser) session.getAttribute(Globals.MERCHANT_SESSION_USER);
        if (curUser != null && curUser.getToken() != null) {
            LoginUser loginUser = new LoginUser();
            loginUser.setToken(curUser.getToken());
            loginUser.setAvatarImg(curUser.getAvatarImg());
            loginUser.setUserId(curUser.getUserId());
            loginUser.setUseStartTime(curUser.getUseStartTime());
            loginUser.setUseEndTime(curUser.getUseEndTime());
            System.out.println("商户信息");
            return loginUser;
        } else {
            throw new Exception();
        }
    }

    //通过传入的request取session中的管理员信息
    public static LoginUser getAdminUserByRequest() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        LoginUser curUser = (LoginUser) session.getAttribute(Globals.ADMIN_SESSION_USER);
        if (curUser != null && curUser.getToken() != null) {
            LoginUser loginUser = new LoginUser();
            loginUser.setToken(curUser.getToken());
            loginUser.setAvatarImg(curUser.getAvatarImg());
            loginUser.setUserId(curUser.getUserId());
            loginUser.setUseStartTime(curUser.getUseStartTime());
            loginUser.setUseEndTime(curUser.getUseEndTime());
            return loginUser;
        } else {
            throw new Exception();
        }
    }


    //通过传入的request获取商户session信息
    public static LoginUser getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (LoginUser) session.getAttribute(Globals.MERCHANT_SESSION_USER);
    }

    //app分页计算
    public static int getPageNo(int pageNumber) {
        int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
        if (pageNumber > 1) {
            pageNo = pageNumber;
        }
        return pageNo;
    }

    //微信支付金额计算(金额单位为分)
    public static String doubleToString(double fee) {
        if (fee > 0) {
            String feeStr = (fee * 100) + "".trim();
            return feeStr.substring(0, feeStr.length() - 2);
        } else {
            return null;
        }
    }

    //获取当前时间戳（以秒为单位）
    public static long getCurTime() {
        return new Date().getTime() / 1000;
    }

    //支付宝退款扣除费率计算(取小数点最后一位)
    public static double aliPayDeductRate(double amount) {
        DecimalFormat df = new DecimalFormat("#.###");
        double val = 0;
        if (amount > 1) {
            String rateVal = df.format(amount * rate);
            String rateValFormat = rateVal.substring(0, rateVal.length() - 1);
            val = amount - Double.parseDouble(rateValFormat);
        }
        val = amount;
        return val;
    }

    //微信退款扣除费率计算(取小数点后最后一位数) 单位为分
    public static String wxPayDeductRate(double amount) {
        DecimalFormat df = new DecimalFormat("#.###");
        double val = 0;
        if (amount > 1) {
            String rateVal = df.format(amount * rate);
            String rateValFormat = rateVal.substring(0, rateVal.length() - 1);
            val = amount - Double.parseDouble(rateValFormat);
        }
        val = amount;

        return doubleToString(val);
    }

    //格式化值
    public static int getMarkByButtonVal(String buttonVal) {
        int orderMark = 0;
        if (StringUtils.isNotEmpty(buttonVal)) {
            if (buttonVal.equals("processed")) {
                orderMark = 1;
            } else if (buttonVal.equals("notProcessed")) {
                orderMark = 2;
            } else {
                orderMark = 3;
            }
        }
        return orderMark;
    }

    //格式化值
    public static List<OrderRecord> caseOrderHandle(List<OrderRecord> list) {
        for (OrderRecord each : list) {
            switch (each.getOrderState()) {
                case 1:
                    each.setOrderHandle("接单");
                    break;
                case 2:
                    each.setOrderHandle("配送");
                    break;
                case 3:
                    each.setOrderHandle("配送中");
                    break;
                case 4:
                    each.setOrderHandle("完成");
                    break;
                case 5:
                    each.setOrderHandle("查看");
                    break;
                case 10:
                    each.setOrderHandle("查看详情");
                    break;
                case 11:
                    each.setOrderHandle("查看详情");
                    break;
                case 12:
                    each.setOrderHandle("查看详情");
                    break;
                default:
                    each.setOrderHandle("未知");
            }
        }
        return list;
    }

    //数字值格式化值
    public static OrderRecord caseOrderHandle(OrderRecord orderRecord) {
        switch (orderRecord.getOrderState()) {
            case 1:
                orderRecord.setOrderHandle("接单");
                break;
            case 2:
                orderRecord.setOrderHandle("配送");
                break;
            case 3:
                orderRecord.setOrderHandle("配送中");
                break;
            case 4:
                orderRecord.setOrderHandle("完成");
                break;
            case 5:
                orderRecord.setOrderHandle("查看");
                break;
            case 10:
                orderRecord.setOrderHandle("查看详情");
                break;
            case 11:
                orderRecord.setOrderHandle("查看详情");
                break;
            case 12:
                orderRecord.setOrderHandle("查看详情");
                break;
            default:
                orderRecord.setOrderHandle("未知");
                System.out.println("");
        }
        return orderRecord;
    }

    //格式化数字值
    public static String getQueryDateByDateMark(int dateMark) {
        switch (dateMark) {
            case 1:
                //近七天
                //上周
                DateTime lastWeek = DateUtil.lastWeek();
                Date oneWeek = DateUtil.beginOfDay(lastWeek);
                return oneWeek.toString();
            case 2:
                //近一个月
                //上个月
                DateTime lastMonth = DateUtil.lastMonth();
                Date oneMonth = DateUtil.beginOfDay(lastMonth);
                return oneMonth.toString();
            case 3:
                //近三个月
                String nowDate = format(new Date(), NORM_DATETIME_PATTERN);
                DateTime nowDateTime = DateUtil.parse(nowDate);
                DateTime lastThreeMonth = DateUtil.offsetMonth(nowDateTime, -3);
                return lastThreeMonth.toString();
            default:
                return null;
        }
    }

    //通过session获取管理员信息
    public static LoginUser getAdminAccountBySession(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        LoginUser user = (LoginUser) session.getAttribute(Globals.ADMIN_SESSION_USER);
        if (user != null) {
            return user;
        } else {
            throw new Exception("获取用户数据为空");
        }
    }

    //aes  解码接口
    public static Object decryptString(String qrKey) throws Exception {
        String keys = Globals.encryptionKey;
        try {
            AES aesResult = aes(keys.getBytes());
            byte[] key = aesResult.getSecretKey().getEncoded();
            byte[] val = Base64.decodeBase64(qrKey);
            byte[] decryption = aes(key).decrypt(val);
            String toStr = StrUtil.str(decryption, "UTF8");
            return Integer.parseInt(toStr);
        } catch (Exception e) {
            throw new Exception("解码失败");
        }
    }

    //model统一返回方法(可变形参)
    public static void ReturnValByModel(Model model, Map<String, Object> map) {
        if (model != null && map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
            try {
                LoginUser loginUser = (LoginUser) OtherUtils.getLoginUserByRequest();
                String userKey = OtherUtils.encryptString(String.valueOf(loginUser.getUserId()));
                model.addAttribute("userKey", userKey.replace("=", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //model统一返回方法(可变形参)
    public static void ReturnValByModelAndAdmin(Model model, Map<String, Object> map) {
        if (model != null && map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                model.addAttribute(entry.getKey(), entry.getValue());
            }
            try {
                LoginUser loginUser = (LoginUser) OtherUtils.getAdminUserByRequest();
                String userKey = OtherUtils.encryptString(String.valueOf(loginUser.getUserId()));
                model.addAttribute("userKey", userKey.replace("=", ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //aes 加密返回加密后的字符串
    public static String encryptString(String key) throws Exception {
        String keys = Globals.encryptionKey;
        try {
            AES aesResult = aes(keys.getBytes());
            byte[] keyVal = aesResult.getSecretKey().getEncoded();
            return Base64.encodeBase64String(aes(keyVal).encrypt(key));
        } catch (Exception e) {
            throw new Exception("加密失败");
        }
    }

    //替换字符串方法
    public static String replaceStr(String key, String param1, String param2) {
        if (StringUtils.isNotEmpty(key)) {
            return key.replace(param1, param2);
        }
        return key;
    }

    //推送消息
    public static void pushByGoEasy(char classifyId, int statusCode, String message, int accountShopId) throws Exception {
        try {
            GoEasy goEasy = new GoEasy(Globals.goEasyKey);
            GoEasyResult goEasyResult = GoEasyResult.getInstance();
            goEasyResult.setClassifyId(classifyId);
            goEasyResult.setMessage(message);
            goEasyResult.setStatusCode(statusCode);
            JSONObject obj = (JSONObject) JSONObject.toJSON(goEasyResult);
            String userKey = OtherUtils.encryptString(String.valueOf(accountShopId));
            goEasy.publish(replaceStr(userKey, "=", ""), obj.toJSONString());
        } catch (Exception e) {
            throw new Exception("推送失败");
        }
    }

    //验证字符串是否由数字组成
    public static boolean verifyStrIsNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    //传入日期数字标识，返回Map数据，通过key取value
    public static Map<String, String> getDateMapByNum(int num) {
        Map<String, String> map = new HashMap<>();
        String todayStr = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);  //今天日期
        switch (num) {
            case 0:
                //昨天
                String yesterdayStr = format(DateUtil.yesterday(), NORM_DATE_PATTERN);  //昨天日期
                map.put("startTime", yesterdayStr);
                map.put("endTime", todayStr);
                return map;
            case 1:
                //近七天
                String lastWeek = format(DateUtil.lastWeek(), NORM_DATE_PATTERN);  //上周日期
                map.put("startTime", lastWeek);
                map.put("endTime", todayStr);
                return map;
            case 2:
                //近30天
                String lastMonth = format(DateUtil.lastMonth(), NORM_DATE_PATTERN);  //上个月
                map.put("startTime", lastMonth);
                map.put("endTime", todayStr);
                return map;
            case 3:
                //近90天
                String firstThreeMonth = format(DateUtil.offsetMonth(new Date(), -3), NORM_DATE_PATTERN);  //上三个月
                map.put("startTime", firstThreeMonth);
                map.put("endTime", todayStr);
                return map;
            default:
                break;
        }
        return map;
    }

    //根据传入的评论数据统计数量(日期数量，星级数量)
    public void statisticalQuantity(Model model, String accountShopToken, int cMark) {
        //当天最后结束的毫秒长整型值
        String todayStr = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);  //今天日期
        //昨天
        String yesterdayStr = format(DateUtil.beginOfDay(DateUtil.yesterday()), NORM_DATETIME_PATTERN);  //昨天日期
        //近七天
        String lastWeekStr = format(DateUtil.beginOfDay(DateUtil.lastWeek()), NORM_DATETIME_PATTERN);  //上周日期
        //近30天
        String lastMonthStr = format(DateUtil.beginOfDay(DateUtil.lastMonth()), NORM_DATETIME_PATTERN);  //上个月
        //近90天
        String firstThreeMonthStr = format(DateUtil.beginOfDay(DateUtil.offsetMonth(new Date(), -3)), NORM_DATETIME_PATTERN);  //上三个月

        //一星数量
        int oneStar = messageCommentService.getStarCountByStarNum(accountShopToken, cMark, 0, 1);
        //二星数量
        int twoStar = messageCommentService.getStarCountByStarNum(accountShopToken, cMark, 0, 2);
        //三星数量
        int threeStar = messageCommentService.getStarCountByStarNum(accountShopToken, cMark, 0, 3);
        //四星数量
        int fourStar = messageCommentService.getStarCountByStarNum(accountShopToken, cMark, 0, 4);
        //五星数量
        int fiveStar = messageCommentService.getStarCountByStarNum(accountShopToken, cMark, 0, 5);

        //昨天
        int yesterday = messageCommentService.getDateCountByDateVal(accountShopToken, cMark, 0, yesterdayStr, todayStr);
        //近七天
        int lastWeek = messageCommentService.getDateCountByDateVal(accountShopToken, cMark, 0, lastWeekStr, todayStr);
        //近30天
        int lastMonth = messageCommentService.getDateCountByDateVal(accountShopToken, cMark, 0, lastMonthStr, todayStr);
        //近90天
        int firstThreeMonth = messageCommentService.getDateCountByDateVal(accountShopToken, cMark, 0, firstThreeMonthStr, todayStr);


        model.addAttribute("oneStar", oneStar);
        model.addAttribute("twoStar", twoStar);
        model.addAttribute("threeStar", threeStar);
        model.addAttribute("fourStar", fourStar);
        model.addAttribute("fiveStar", fiveStar);
        model.addAttribute("yesterday", yesterday);
        model.addAttribute("lastWeek", lastWeek);
        model.addAttribute("lastMonth", lastMonth);
        model.addAttribute("firstThreeMonth", firstThreeMonth);
    }

    //获取request method
    public HttpServletRequest getRequestByMethod() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                return request;
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public void setWxPay(WeChatPayUtil wxPay) {
        this.wxPay = wxPay;
    }

    public WeChatPayUtil getWxPay() {
        return wxPay;
    }

    public void setJedisServiceCache(JedisServiceCache jedisServiceCache) {
        this.jedisServiceCache = jedisServiceCache;
    }

    public JedisServiceCache getJedisServiceCache() {
        return jedisServiceCache;
    }

    public void setBaseRedisCache(BaseRedisCache baseRedisCache) {
        this.baseRedisCache = baseRedisCache;
    }

    public BaseRedisCache getBaseRedisCache() {
        return baseRedisCache;
    }

    public void setBaseSolr(BaseSolr baseSolr) {
        this.baseSolr = baseSolr;
    }

    public BaseSolr getBaseSolr() {
        return baseSolr;
    }
}
