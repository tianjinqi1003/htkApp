package com.htkapp.modules.API.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.MD5Utils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.GetImagesByUrl;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.dao.AccountMapper;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.entity.AccountFocus;
import com.htkapp.modules.API.entity.AppAccountEventLog;
import com.htkapp.modules.API.entity.AppShippingAddress;
import com.htkapp.modules.API.service.*;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import com.htkapp.modules.merchant.integral.entity.IntegralManageRecord;
import com.htkapp.modules.merchant.integral.service.IntegralManageRecordService;
import com.htkapp.modules.merchant.integral.service.IntegralService;
import com.htkapp.modules.merchant.pay.dto.AppAccountInfo;
import com.htkapp.modules.merchant.pay.entity.BillBalanceSheet;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.*;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.xiaoleilu.hutool.date.DateUtil.*;

@Service
public class AccountServiceImpl implements AccountServiceI {
    @Resource
    private AccountMapper accountDao;
    @Resource
    private SMSBaseServiceI smsService;
    @Resource
    private UserServiceI userService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private OrderProductService orderProductService;
    @Resource
    private AccountFocusService accountFocusService;
    @Resource
    private AppAccountEventLogService eventLogService;
    @Resource
    private AppShippingAddressService appShippingAddressService;
    @Resource
    private BillRecordService billRecordService;
    @Resource
    private BillBalanceSheetService billBalanceSheetService;
    @Resource
    private OtherUtils otherUtilsService;
    @Resource
    private IntegralService integralService;
    @Resource
    private TakeoutProductServiceI takeoutProductService;
    @Resource
    private IntegralManageRecordService integralManageRecordService;

    @Resource
    private TakeoutOrderService takeoutOrderService;

    /* ====================接口的逻辑处理方法开始========================= */
    //通过手机号注册用户
    @Override
    public APIResponseModel registerByPhone(String phone, String valCode, String password) {
        try {
            boolean isDuplicate = selectByPhone(phone);
            if (isDuplicate) {
                try {
                    String valCodeDB = smsService.findValCodeByPhone(String.valueOf(phone));
                    if (StringUtils.isEmpty(valCodeDB) || !valCodeDB.equals(valCode)) {
                        return new APIResponseModel(Globals.API_REQUEST_BAD, "很抱歉，验证码输入不正确，请重新输入！");
                    } else if (StringUtils.isNotEmpty(valCodeDB) && valCodeDB.equals(valCode)) {
                        Account account = new Account()
                                .setUserName(String.valueOf(phone))
                                .setSaltToken(Globals.DEFAULT_SALT_TOKEN)
                                .setPassword(MD5Utils.getMD5Encode(password))
                                .setPhone(phone)
                                .setAvatarUrl("htkApp/upload/app/default/appDefaultAva_img.jpg")
                                .setToken(UUID.randomUUID().toString())
                                .setRegisterTime(format(new Date(), NORM_DATETIME_PATTERN))
                                .setAccountStatus(1) //默认激活账号
                                .setNickName("尚无昵称");
                        account.setEncryptToken(MD5Utils.getMD5Encode(account.getToken() + account.getSaltToken()));
                        try {
                            return insertAccount(account);
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                        }
                    }
                    return new APIResponseModel(Globals.API_BACK_CODE_LOGIC_ERROR, Globals.BACK_CODE_LOGIC_ERROR_MESSAGE);
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_REQUEST_BAD, e.getMessage());
                }
            } else {
                return new APIResponseModel(Globals.API_FAIL, "该手机号重复注册了!");
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    /**
     * @param phone
     * @return
     * @author 新的短信验证码发送
     */
    @Override
    public APIResponseModel sendSMS(String phone) {
        //生成验证码
        String valCode = smsService.generatorValCode();
        String smsText = "【回头客】验证码是:" + valCode;
        try {
            smsService.saveOrUpdate(String.valueOf(phone), valCode);
            String smsRet = smsService.SendSms(String.valueOf(phone), smsText);
            int key = smsRet.contains(",") ? Integer.parseInt(smsRet.split(",")[0]) : Integer.parseInt(smsRet);
            if (key == -1) {
                String errorMsg = "用户名或者密码不正确或用户禁用或者是管理账户";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 1) {
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent("短信已发送，请注意查收！");
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(Globals.API_SUCCESS, "短信已发送，请注意查收！");
            } else if (key == 0) {
                String errorMsg = "发送短信失败";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 2) {
                String errorMsg = "余额不够或扣费错误";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 3) {
                String errorMsg = "扣费失败异常（请联系客服）";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 6) {
                String errorMsg = "有效号码为空";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 7) {
                String errorMsg = "短信内容为空";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 8) {
                String errorMsg = "无签名，必须，格式：【签名】";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            } else if (key == 9) {
                String errorMsg = "没有Url提交权限";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 10) {
                String errorMsg = "发送号码过多,最多支持2000个号码";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 11) {
                String errorMsg = "产品ID异常或产品禁用";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 12) {
                String errorMsg = "参数异常";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 13) {
                String errorMsg = "tkey参数错误";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 15) {
                String errorMsg = "Ip验证失败";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 16) {
                String errorMsg = "xh参数错误";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else if (key == 19) {
                String errorMsg = "短信内容过长，最多支持500个,或提交编码异常导致";
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(String.valueOf(phone))
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent(errorMsg);
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(-99, errorMsg);
            }else {
                return new APIResponseModel(-99, "未知异常");
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, "短信发送失败，请联系客服人员！");
        }
    }
//    public APIResponseModel sendSMS(String phone) {
//        //生成验证码
//        String valCode = smsService.generatorValCode();
//        String smsText = "【回头客】验证码是:" + valCode;
//        try {
//            smsService.saveOrUpdate(String.valueOf(phone), valCode);
//            String smsRet = smsService.SendSms(String.valueOf(phone), smsText);
//            if (Integer.parseInt(smsRet) < 0) {
//                AppAccountEventLog eventLog = new AppAccountEventLog()
//                        .setToken(String.valueOf(phone))
//                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
//                        .setContent("发送短信状态码失败!");
//                eventLogService.insertAppEventLog(eventLog);
//                return new APIResponseModel(-99, "发送短信状态码失败!");
//            } else {
//                AppAccountEventLog eventLog = new AppAccountEventLog()
//                        .setToken(String.valueOf(phone))
//                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
//                        .setContent("短信已发送，请注意查收！");
//                eventLogService.insertAppEventLog(eventLog);
//                return new APIResponseModel(Globals.API_SUCCESS, "短信已发送，请注意查收！");
//            }
//        } catch (Exception e) {
//            return new APIResponseModel(Globals.API_FAIL, "短信发送失败，请联系客服人员！");
//        }
//    }

    //通过手机号和短信验证码登陆
    @Override
    public APIResponseModel appAccountLoginByCode(String phone, String valCode) {
        if (StringUtils.isEmpty(String.valueOf(phone)) || StringUtils.isEmpty(valCode)) {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "登陆请求用户名或验证码为空");
        } else {
            try {
                String codeByPhone = smsService.findValCodeByPhone(String.valueOf(phone));
                if (!String.valueOf(valCode).equals(codeByPhone)) {
                    //验证码不匹配
                    return new APIResponseModel(Globals.API_FAIL, "失败验证码不匹配");
                } else {
                    try {
                        Account account = selectDataByPhone(phone);
                        if (account != null) {
                            return new APIResponseModel<String>(Globals.API_SUCCESS, "登陆成功", account.getToken());
                        } else {
                            return new APIResponseModel(Globals.API_FAIL, "在用户表中通过手机号查找返回数据为空");
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        }
    }

    //通过传入的token查找用户信息
    @Override
    public APIResponseModel getAppAccountData(String token) {
        try {
            Account account = selectByToken(token);
            if (account == null) {
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(token)
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent("通过传入的token查找用户信息为空")
                        .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(Globals.API_FAIL, "通过传入的token查找用户信息为空");
            } else {
                AppAccountInfo accountInfo = new AppAccountInfo()
                        .setToken(token)
                        .setLoginWay(account.getLoginWay())
                        .setNickName(account.getNickName())
                        .setPasswordStatus(account.getChangePassword())
                        .setAvaUrl(OtherUtils.getRootDirectory() + account.getAvatarUrl())
                        .setQqStatus(StringUtils.isNotEmpty(account.getAccountLoginWayQq()))
                        .setWeChatStatus(StringUtils.isNotEmpty(account.getAccountLoginWayWechat()))
                        .setPhone(account.getPhone());
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(token)
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent("查找用户信息成功")
                        .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel<AppAccountInfo>(Globals.API_SUCCESS, "查找用户信息成功", accountInfo);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //通过传入的token 旧密码　新密码　　修改密码
    @Override
    public APIResponseModel changeAppAccountPassword(String token, String oldP, String newP) {
        try {
            Account account = verifyAppAccountPassword(token, oldP);
            if (account == null) {
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(token)
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent("验证密码失败")
                        .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(Globals.API_FAIL, "验证密码失败");
            } else {
                account.setPassword(MD5Utils.getMD5Encode(newP));
                int row = accountDao.changePasswordDAO(account);
                if (row == 1) {
                    AppAccountEventLog eventLog = new AppAccountEventLog()
                            .setToken(token)
                            .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                            .setContent("修改密码成功")
                            .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                    eventLogService.insertAppEventLog(eventLog);
                    Account account1 = accountDao.selectByTokenDAO(token);
                    if (!account1.getChangePassword()) {
                        accountDao.changePasswordStatusDAO(token);
                    }
                    return new APIResponseModel(Globals.API_SUCCESS, "修改密码成功");
                } else {
                    AppAccountEventLog eventLog = new AppAccountEventLog()
                            .setToken(token)
                            .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                            .setContent("修改密码失败")
                            .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                    eventLogService.insertAppEventLog(eventLog);
                    return new APIResponseModel(Globals.API_SUCCESS, "修改密码失败");
                }
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //通过用户名和密码登陆
    @Override
    public APIResponseModel appAccountLoginByUserName(String userName, String password, String role, short loginWay) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(role) || StringUtils.isEmpty(String.valueOf(loginWay))) {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误");
        } else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(role)) {
            try {
                LoginUser loginUser = new LoginUser()
                        .setUserName(userName)
                        .setPassword(MD5Utils.getMD5Encode(password))
                        .setRole(role)
                        .setLoginWay(loginWay);
                LoginUser enterUser = userService.checkUser(loginUser);
                if (enterUser == null) {
                    AppAccountEventLog eventLog = new AppAccountEventLog()
                            .setToken(userName)
                            .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                            .setLoginWay(loginWay);
                    eventLogService.insertAppEventLog(eventLog);
                    return new APIResponseModel(Globals.API_FAIL, "用户登陆失败");
                } else {
                    AppAccountEventLog eventLog = new AppAccountEventLog()
                            .setToken(enterUser.getToken())
                            .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                            .setContent("用户登陆成功")
                            .setLoginWay(loginWay);
                    eventLogService.insertAppEventLog(eventLog);
                    accountDao.changeAppAccountLoginWayDAO(enterUser.getToken(), loginWay);
                    return new APIResponseModel<String>(Globals.API_SUCCESS, "用户登陆成功", enterUser.getToken());
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误");
        }

    }

    //app用户修改头像
    @Override
    public APIResponseModel changeAvaImg(MultipartFile avaImgFile, String token) {
        try {
            if (avaImgFile == null) {
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(token)
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent("修改头像请求参数错误")
                        .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                eventLogService.insertAppEventLog(eventLog);
                return new APIResponseModel(Globals.API_REQUEST_BAD, "修改头像请求参数错误");
            } else {
                try {
                    String updateAvaImg = FileUploadUtils.appUploadAvatarImg(avaImgFile, "app/account/", FTPConfig.port_to);
                    accountDao.changeAppAccountAvaUrlDAO(token, updateAvaImg);
                    AppAccountEventLog eventLog = new AppAccountEventLog()
                            .setToken(token)
                            .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                            .setContent("修改头像成功")
                            .setLoginWay(accountDao.getAppAccountLoginWayDAO(token));
                    eventLogService.insertAppEventLog(eventLog);
                    return new APIResponseModel<String>(Globals.API_SUCCESS, "修改头像成功", OtherUtils.getRootDirectory() + updateAvaImg);
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, "修改头像失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //app用户修改呢称(只能修改一次)
    @Override
    public APIResponseModel changeAppNickName(String token, String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误");
        } else {
            try {
                Account account = selectByToken(token);
                if (account == null) {
                    return new APIResponseModel(Globals.API_FAIL, "修改呢称失败");
                } else {
                    if (account.getChangeNickName()) {
                        return new APIResponseModel(Globals.API_FAIL, "呢称只能修改一次!");
                    } else {
                        try {
                            accountDao.changeAppAccountNickNameAndStateDAO(token, nickName);
                            return new APIResponseModel(Globals.API_SUCCESS, "修改呢称成功");
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL, "数据库层修改呢称和状态异常");
                        }
                    }
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        }
    }

    //获得用户的外卖收货地址列表
    @Override
    public APIResponseModel getAccountShippingAddressList(String token) {
        try {
            List<AppShippingAddress> list = appShippingAddressService.getAccountShippingAddressList(token);
            return new APIResponseModel<List<AppShippingAddress>>(Globals.API_SUCCESS, "获得用户外卖收货地址成功", list);
        } catch (NullDataException e) {
            System.out.println(e.getMessage());
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //添加用户外卖收货地址
    @Override
    public APIResponseModel addAccountShippingAddress(AppShippingAddress shippingAddress) {
        if (shippingAddress != null) {
            try {
                boolean result = appShippingAddressService.addAccountShippingAddress(shippingAddress);
                if (result) {
                    return new APIResponseModel(Globals.API_SUCCESS, "添加用户收货地址成功");
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "添加用户收货地址失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误");
        }
    }

    //修改用户外卖收货地址
    @Override
    public APIResponseModel changeAccountShippingAddress(AppShippingAddress shippingAddress) {
        if (shippingAddress != null) {
            try {
                boolean result = appShippingAddressService.changeAccountShippingAddress(shippingAddress);
                if (result) {
                    return new APIResponseModel(Globals.API_SUCCESS, "修改成功");
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "修改失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //app用户收藏店铺和收藏店铺请求
    @Override
    public APIResponseModel collectionStore(String token, Integer shopId, Boolean colStatus) {
        boolean result = false;
        if (shopId != null && colStatus != null) {
            if (colStatus) {
                //收藏店铺
                try {
                    Shop shop = shopService.getShopDataById(shopId);
                    int value = accountFocusService.checkStateAndJoinMemberByToken(token, shopId);
                    switch (value) {
                        case 0:
                            return new APIResponseModel(Globals.API_SUCCESS, "收藏当前店铺成功");
                        case -1:
                            return new APIResponseModel(Globals.API_FAIL, "用户为空");
                        case -2:
                            return new APIResponseModel(Globals.API_FAIL, "用户已关注");
                        case -3:
                            return new APIResponseModel(Globals.API_FAIL, "用户未关注店铺，但积分值可见");
                        case -4:
                            return new APIResponseModel(Globals.API_FAIL, "执行异常");
                        default:
                            break;
                    }
                    return new APIResponseModel(Globals.API_FAIL);
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }

            } else {
                //取消取藏店铺
                try {
                    result = accountFocusService.cancelTheStore(token, shopId);
                    if (result) {
                        //取消收藏店铺，置积分记录数据为不可见 0
                        integralService.updateIntegralFlagByToken(token, 0);
                        return new APIResponseModel(Globals.API_SUCCESS);
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据app用户的token获取用户的收藏列表
    @Override
    public APIResponseModel getCollectionListByToken(String token, int pageNumber) {
        int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
        int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
        if (pageNumber == 2) {
            pageNo = pageNumber;
        }
        try {
            List<AccountFocus> focusList = accountFocusService.getCollectListByTokenByPage(token, pageNo, pageLimit);
            if (focusList != null && focusList.size() > 0) {
                //有关注的店铺,根据获得的店铺id,再查询店铺的信息
                try {
                    List<Shop> shopList = new ArrayList<>();
                    for (AccountFocus every : focusList) {
                        Shop shop = shopService.getShopDataById(every.getShopId());
                        shop.setCollection(true);
                        shop.setLogoUrl(OtherUtils.getRootDirectory() + shop.getLogoUrl());
                        shopList.add(shop);
                    }
                    return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", shopList);
                } catch (NullDataException e1) {
                    return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
                } catch (Exception e2) {
                    return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
                }
            } else {
                return new APIResponseModel<>(Globals.API_SUCCESS, "当前用户没有收藏店铺", null);
            }
        } catch (Exception e1) {
            return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
        }
    }

    //通过用户token和地址id删除用户外卖收货地址
    @Override
    public APIResponseModel deleteAccountShippingAddressById(String token, Integer addressId) {
        if (addressId != null) {
            try {
                boolean result = appShippingAddressService.deleteAccountShippingAddress(token, addressId);
                if (result) {
                    return new APIResponseModel(Globals.API_SUCCESS, "删除成功");
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "删除失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //忘记密码（通过短信重置密码）
    @Override
    public APIResponseModel forgetPasswordBySMS(String phone, String code, String password) {
        try {
            if (phone != null && StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(password)) {
                try {
                    String valCodeDB = smsService.findValCodeByPhone(String.valueOf(phone));
                    if (StringUtils.isEmpty(valCodeDB) || !valCodeDB.equals(code)) {
                        return new APIResponseModel(Globals.API_REQUEST_BAD, "很抱歉，验证码输入不正确，请重新输入！");
                    } else if (StringUtils.isNotEmpty(valCodeDB) && valCodeDB.equals(code)) {
                        //记录下修改密码行为
                        Account account = new Account();
                        account.setUserName(phone);
                        account.setPassword(MD5Utils.getMD5Encode(password));
                        try {
                            boolean result = changePasswordByForgetPasswordBySMS(account);
                            if (result) {
                                return new APIResponseModel(Globals.API_SUCCESS, "找回密码成功");
                            } else {
                                AppAccountEventLog eventLog = new AppAccountEventLog()
                                        .setToken(phone)
                                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                                        .setContent("忘记密码——找回密码失败");
                                eventLogService.insertAppEventLog(eventLog);
                                return new APIResponseModel(Globals.API_SUCCESS, "找回密码失败");
                            }
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            } else {
                return new APIResponseModel(Globals.API_REQUEST_BAD);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //微信登陆调起接口
    @Override
    public APIResponseModel loginByWeChat(String weChatToken) {
        try {
            Account account = verifyLoginByWeChatToken(weChatToken);
            if (account != null) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("token",account.getToken());
//                jsonObject.put("phone", account.getPhone());
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", account.getToken());
            } else {
                return new APIResponseModel<String>(Globals.API_SUCCESS, "微信登陆没有和手机号绑定", null);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //qq登陆调起接口
    @Override
    public APIResponseModel loginByQq(String qqToken) {
        try {
            Account account = verifyLoginByQqToken(qqToken);
            if (account != null) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("token",account.getToken());
//                jsonObject.put("phone", account.getPhone());
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", account.getToken());
            } else {
                return new APIResponseModel<Boolean>(Globals.API_SUCCESS, "qq登陆没有和手机号绑定", null);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //微信登陆未绑定用户调起接口
    @Override
    public APIResponseModel weChatLoginCallUpInterface(APIRequestParams params) {
        if (StringUtils.isNotEmpty(params.getToken())) {
            try {
                Account account = selectByToken(params.getToken());
                //已登陆状态
                if (StringUtils.isNotEmpty(params.getWeChatToken()) && StringUtils.isNotEmpty(params.getAvatarUrl()) && account != null) {
                    //更新用户的微信登陆token
                    try {
                        changeThirdPartyToken(params.getWeChatToken(), 1, params.getToken());
                        return new APIResponseModel(Globals.API_SUCCESS);
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                }
                return new APIResponseModel(Globals.API_FAIL, "用户不存在或参数为空");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            //未登陆状态
            if (StringUtils.isNotEmpty(params.getPhone()) && StringUtils.isNotEmpty(params.getCode().toString()) && StringUtils.isNotEmpty(params.getWeChatToken()) && StringUtils.isNotEmpty(params.getAvatarUrl())) {
                try {
                    //通过手机号验证用户是否存在,不存在则新建用户信息,并和微信服务器返回的token值相绑定
                    String codeByPhone = smsService.findValCodeByPhone(params.getPhone());
                    if (!String.valueOf(params.getCode()).equals(codeByPhone)) {
                        //验证码不匹配
                        return new APIResponseModel(Globals.API_FAIL, "验证码不匹配");
                    } else {
                        try {
                            Account account = selectDataByPhone(params.getPhone());
                            if (account != null) {
                                //更新用户的微信登陆token
                                changeThirdPartyToken(params.getWeChatToken(), 1, account.getToken());
                                return new APIResponseModel<String>(Globals.API_SUCCESS, "登陆成功", account.getToken());
                            } else {
                                //新建用户
                                HttpServletRequest request = otherUtilsService.getRequestByMethod();
                                String avatarPath = GetImagesByUrl.getImagesByUrl(params.getAvatarUrl(), request);
                                Account account1 = new Account()
                                        .setUserName(String.valueOf(params.getPhone()))
                                        .setSaltToken(Globals.DEFAULT_SALT_TOKEN)
                                        .setPassword(MD5Utils.getMD5Encode(Globals.DEFAULT_PWD))
                                        .setPhone(params.getPhone())
                                        .setNickName(params.getNickName())
                                        .setAvatarUrl(avatarPath)
                                        .setToken(UUID.randomUUID().toString())
                                        .setRegisterTime(format(new Date(), NORM_DATETIME_PATTERN))
                                        .setAccountStatus(1); //默认激活账号
                                account1.setEncryptToken(MD5Utils.getMD5Encode(account1.getToken() + account1.getSaltToken()));
                                APIResponseModel responseModel = insertAccount(account1);
                                if (responseModel.getData().equals(account1.getToken())) {
                                    //注册成功
                                    changeThirdPartyToken(params.getWeChatToken(), 1, account1.getToken());
                                    return new APIResponseModel<String>(Globals.API_SUCCESS, "登陆成功", account1.getToken());
                                } else {
                                    //新建用户失败
                                    return new APIResponseModel(Globals.API_FAIL);
                                }
                            }
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            } else {
                return new APIResponseModel(Globals.API_REQUEST_BAD);
            }
        }
    }

    //qq登陆未绑定用户调起接口
    @Override
    public APIResponseModel qqLoginCallUpInterface(APIRequestParams params) {
        if (StringUtils.isNotEmpty(params.getToken())) {
            try {
                Account account = selectByToken(params.getToken());
                //已登陆状态
                if (StringUtils.isNotEmpty(params.getQqToken()) && StringUtils.isNotEmpty(params.getAvatarUrl()) && account != null) {
                    //更新用户的微信登陆token
                    try {
                        System.out.println("验证用户成功");
                        changeThirdPartyToken(params.getQqToken(), 1, params.getToken());
                        return new APIResponseModel(Globals.API_SUCCESS);
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                }
                return new APIResponseModel(Globals.API_FAIL, "用户不存在或参数为空");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            if (StringUtils.isNotEmpty(params.getPhone()) && StringUtils.isNotEmpty(params.getCode().toString()) && StringUtils.isNotEmpty(params.getQqToken())) {
                try {
                    //通过手机号验证用户是否存在,不存在则新建用户信息,并和微信服务器返回的token值相绑定
                    String codeByPhone = smsService.findValCodeByPhone(params.getPhone());
                    if (!String.valueOf(params.getCode().toString()).equals(codeByPhone)) {
                        //验证码不匹配
                        return new APIResponseModel(Globals.API_FAIL, "验证码不匹配");
                    } else {
                        try {
                            Account account = selectDataByPhone(params.getPhone());
                            if (account != null) {
                                //更新用户的qq登陆token
                                changeThirdPartyToken(params.getQqToken(), 0, account.getToken());
                                return new APIResponseModel<String>(Globals.API_SUCCESS, "登陆成功", account.getToken());
                            } else {
                                //新建用户
                                HttpServletRequest request = otherUtilsService.getRequestByMethod();
                                String avatarPath = GetImagesByUrl.getImagesByUrl(params.getAvatarUrl(), request);
                                Account account1 = new Account()
                                        .setUserName(String.valueOf(params.getPhone()))
                                        .setSaltToken(Globals.DEFAULT_SALT_TOKEN)
                                        .setPassword(MD5Utils.getMD5Encode(Globals.DEFAULT_PWD))
                                        .setPhone(params.getPhone())
                                        .setNickName(params.getNickName())
                                        .setAvatarUrl(avatarPath)
                                        .setToken(UUID.randomUUID().toString())
                                        .setRegisterTime(format(new Date(), NORM_DATETIME_PATTERN))
                                        .setAccountStatus(1); //默认激活账号
                                account1.setEncryptToken(MD5Utils.getMD5Encode(account1.getToken() + account1.getSaltToken()));
                                APIResponseModel responseModel = insertAccount(account1);
                                if (responseModel.getData().equals(account1.getToken())) {
                                    //注册成功
                                    changeThirdPartyToken(params.getQqToken(), 0, account1.getToken());
                                    return new APIResponseModel<String>(Globals.API_SUCCESS, "登陆成功", account1.getToken());
                                } else {
                                    //新建用户失败
                                    return new APIResponseModel(Globals.API_FAIL);
                                }
                            }
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            } else {
                return new APIResponseModel(Globals.API_REQUEST_BAD);
            }
        }
    }

    //用户确认收货接口
    @Override
    @Transactional
    public APIResponseModel enterReceipt(APIRequestParams params, String orderNumber, String token) {
        if (StringUtils.isNotEmpty(orderNumber)) {
            try {
                OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                //这里要根据account_shop_id去查AccountShop
                Shop shop = shopService.getShopDataById(orderRecord.getShopId());
                AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                orderRecordService.changeOrderStateByOrderNumber(orderNumber, Globals.DEFAULT_T_ENTER_RECEIVING);

                /**
                 * @author 马鹏昊
                 * @desc 确认收货只修改订单状态，还需要把催单状态取消（takeout_order表里的reminder_state置为0）
                 */
                takeoutOrderService.updateReminderStateByOrderId(orderRecord.getId(), 0);

                //改变记录状态为已入账(2为已入账，备注修改2为未入账，1为已入账)
                billRecordService.updateBillStatus(accountShop.getToken(), orderNumber, "1");
                //TODO 记录收入记录
                BillBalanceSheet balanceSheet = new BillBalanceSheet();
                balanceSheet.setSumAmount(orderRecord.getOrderAmount());
                balanceSheet.setAccountShopToken(accountShop.getToken());
                balanceSheet.setRecordType(1);  //1是收入
                balanceSheet.setDescription(format(DateUtil.parseDate(orderRecord.getOrderTime()), NORM_DATE_PATTERN) + "号账单");
                balanceSheet.setRemark(format(DateUtil.parseDate(orderRecord.getOrderTime()), NORM_DATE_PATTERN) + "帐单结算入余额，已成功");
                balanceSheet.setStatus(1);  //1代表成功
                billBalanceSheetService.keepRecordByAccountShopToken(balanceSheet);

                //增加用户积分  增加积分记录  增加交易记录
                /**
                 * @author 马鹏昊
                 * @desc 给用户增加消费返还积分
                 */
                if (accountShop != null) {

                    //先查出积分数量
                    int nowVal = integralService.getVal(token, shop.getShopId());
                    int newVal = nowVal;
                    int sumAddedInteger = 0;
                    List<OrderProduct> allBuyedProducts = orderProductService.getProductListByOrderId(orderRecord.getId());
                    for (OrderProduct each : allBuyedProducts) {
                        TakeoutProduct takeoutProduct = takeoutProductService.getTakeoutProductByProductId(each.getProductId());
                        int addedValue = takeoutProduct.getIntegral() * each.getQuantity();
                        sumAddedInteger += addedValue;
                        newVal += addedValue;
                    }

                    //如果新积分数和旧积分数不一样，说明购买的商品携带了返还积分，需要更新积分
                    if (newVal != nowVal) {
                        integralService.updateIntegral(token, shop.getShopId(), newVal);
                        //通过商铺id查找商铺信息
                        //记录积分变动记录
                        String recordTypeStr = "消费返还积分";
                        IntegralManageRecord record = new IntegralManageRecord(recordTypeStr, accountShop.getToken(), token, sumAddedInteger);
                        integralManageRecordService.insertRecordByToken(record);

                        /**
                         * @author 马鹏昊
                         * @desc 修改最近获得时间（gmt_latest_get字段）
                         */
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        Date d = new Date();
                        String dateStr = df.format(d);
                        integralService.updateLatestGetTime(orderRecord.getToken(), shop.getShopId(), dateStr);
                    }

                }

                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderRecord.getId());
            } catch (Exception e) {
            	e.printStackTrace();
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }


    /* ====================接口的逻辑处理方法结束========================= */


    /*===========================接口开始================================*/
    //注册app用户
    @Override
    public APIResponseModel insertAccount(Account account) throws Exception {
        try {
            int row = accountDao.insertAccountDAO(account);
            if (row == 1) {
                AppAccountEventLog eventLog = new AppAccountEventLog()
                        .setToken(account.getToken())
                        .setEventTime(format(new Date(), NORM_DATETIME_PATTERN))
                        .setContent("通过手机号注册成功");
                eventLogService.insertAppEventLog(eventLog);
                accountDao.changeAppAccountLoginWayDAO(account.getToken(), (short) 1);
                if (account.getNickName() == null) {
                    accountDao.changeAppAccountNickNameDAO(account.getToken(), String.valueOf(account.getAccountId()));
                }
                return new APIResponseModel<String>(Globals.API_SUCCESS, "通过手机号注册用户成功", account.getToken());
            } else {
                return new APIResponseModel(Globals.API_FAIL, "通过手机号注册用户失败");
            }
        } catch (Exception e) {
            throw new Exception("数据库层注册失败");
        }

    }

    //验证传入的手机号是否存在数据库中　
    @Override
    public Boolean selectByPhone(String phone) throws Exception {
        try {
            //为空则表示是数据库中没有当前用户的数据(当前注册手机号在数据库中没有)
            return accountDao.selectByPhoneDAO(phone) == null;
        } catch (Exception e) {
            throw new Exception("数据库层根据手机号查找用户操作失败");
        }
    }

    //通过手机号查找app用户数据
    @Override
    public Account selectDataByPhone(String phone) throws Exception {
        try {
            Account account = accountDao.selectByPhoneDAO(phone);
            if (account == null) {
                //为空则表示是数据库中没有当前用户的数据(当前注册手机号在数据库中没有)
                return null;
            } else {
                return account;
            }
        } catch (Exception e) {
            throw new Exception("数据库层根据手机号获得用户数据失败");
        }
    }

    //通过token查找app用户数据
    @Override
    public Account selectByToken(String token) throws Exception {

        if (StringUtils.isNotEmpty(token)) {
            try {
                return accountDao.selectByTokenDAO(token);
            } catch (Exception e) {
                throw new Exception("数据层根据token查找用户信息失败");
            }
        } else {
            return null;
        }

    }

    //验证app用户的修改密码请求的第一步：验证输入的旧密码是否正确
    @Override
    public Account verifyAppAccountPassword(String token, String oldP) throws Exception {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(oldP)) {
            return null;
        } else if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(oldP)) {
            try {
                Account account = accountDao.verifyAppAccountPasswordDAO(token, MD5Utils.getMD5Encode(oldP));
                if (account == null) {
                    return null;
                } else {
                    return account;
                }
            } catch (Exception e) {
                throw new Exception("数据库层验证app请求密码失败");
            }
        } else {
            return null;
        }
    }

    //根据用户token查询出用户id,用户头像，用户呢称
    @Override
    public Account getTakeoutCommentAccountMessageByToken(String token) throws Exception {
        try {
            Account account = accountDao.getTakeoutCommentAccountMessageByTokenDAO(token);
            if (account != null) {
                return account;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //忘记密码 通过短信改密码
    @Override
    public boolean changePasswordByForgetPasswordBySMS(Account account) throws Exception {
        try {
            int row = accountDao.changePasswordByForgetPasswordBySMSDAO(account);
            return row > 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号和标识查询购买用户信息
    @Override
    public Account getAccountDataByOrderNumberAndMark(String orderNumber, int mark) throws Exception {
        try {
            return accountDao.getAccountDataByOrderNumberAndMarkDAO(orderNumber, mark);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //验证微信登陆是否已绑定用户
    @Override
    public Account verifyLoginByWeChatToken(String weChatToken) throws Exception {
        try {
            return accountDao.verifyLoginByWeChatTokenDAO(weChatToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //验证qq登陆是否已绑定用户
    @Override
    public Account verifyLoginByQqToken(String qqToken) throws Exception {
        try {
            return accountDao.verifyLoginByQqTokenDAO(qqToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //更新用户的第三方登陆token
    @Override
    public void changeThirdPartyToken(String thirdPartyToken, Integer mark, String accountToken) throws Exception {
        try {
            int row = accountDao.changeThirdPartyTokenDAO(thirdPartyToken, mark, accountToken);
            if (row <= 0) {
                throw new Exception("更改失败");
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //解绑第三方账号
    @Override
    public APIResponseModel unbindThirdPartyAccount(APIRequestParams params) {
        try {
            int row = accountDao.changeThirdPartyTokenDAO("", params.getFlag(), params.getToken());
            if (row <= 0) {
                throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
            }
            return new APIResponseModel(Globals.API_SUCCESS);
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }


    /*===========================接口结束================================*/


    /*=========================JSP页面接口开始===========================*/
    //通过条件查找用户列表
    @Override
    public List<Account> getAccountListByCondition(Account account, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<Account> accountList = accountDao.getAccountListByConditionDAO(account);
            if (accountList != null && accountList.size() > 0) {
                return accountList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找用户列表(无条件)
    @Override
    public List<Account> getAccountList(int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<Account> accountList = accountDao.getAccountListDAO();
            if (accountList != null && accountList.size() > 0) {
                return accountList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

	@Override
	public void autoEnterReceipt() {
		// TODO Auto-generated method stub
		
		//List<OrderRecord> tokenList=orderRecordDao.getUnReceiptAccountToken();
		//System.out.println("tokenList.length==="+tokenList.size());
	}



    /* ========================JSP页面接口开始=============================== */
}
