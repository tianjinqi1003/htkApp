package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.htkapp.core.MD5Utils;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.web.AccountShopMessageAPI;
import com.htkapp.modules.admin.common.entity.Admin;
import com.htkapp.modules.admin.system.entity.AdminLoginLog;
import com.htkapp.modules.admin.system.service.AdminLoginLogService;
import com.htkapp.modules.common.dto.AjaxReturnLoginData;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.LoginService;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.AccountShopLoginLog;
import com.htkapp.modules.merchant.shop.service.AccountShopLoginLogService;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.htkapp.core.utils.CookiesUtils.clearCookies;
import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

/**
 * Created by yinqilei on 17-7-4.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserServiceI userService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private AccountShopLoginLogService accountShopLoginLogService;
    @Resource
    private AdminLoginLogService adminLoginLogService;
    @Autowired(required = false)
    private HttpSession session;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;

    private static int adminLoginLogId = 3;

    //商户登陆
    @Override
    @Transactional
    public AjaxResponseModel merchantLogin(LoginUser loginUser) {
        loginUser.setPassword(MD5Utils.getMD5Encode(loginUser.getPassword()));
        try {
            LoginUser user = userService.checkUser(loginUser);
            //判断当前账号是否已经登陆
            if (user != null) {
                if (!user.getPcLoginState()) {
                    //当前该账号不在线
                    session.setAttribute(Globals.MERCHANT_SESSION_USER, user);
                    //调用方法记录本次登陆记录
                    AccountShopLoginLog accountShopLoginLog = new AccountShopLoginLog();
                    accountShopLoginLog.setLoginDate(format(new Date(), NORM_DATETIME_PATTERN));
                    accountShopLoginLog.setCurrentState(Globals.MERCHANT_LOGIN_ONLINE);
                    accountShopLoginLog.setAccountShopToken(user.getToken());
                    try {
                        accountShopLoginLogService.insertCurrentLoginLog(accountShopLoginLog);
                        AccountShopMessageAPI.accountShopLoginLogId = accountShopLoginLog.getId();
                        AjaxReturnLoginData returnData = new AjaxReturnLoginData(user.getUserName(), user.getPassword(), loginUser.getRole(), "merchant/index.htm", user.getToken());
                        //登陆成功，改变账号状态
                        AccountShop accountShop = new AccountShop();
                        accountShop.setToken(user.getToken());
//                        accountShop.setPcLoginState(true);
                        accountShopService.changeAccountShopLoginState(accountShop);
                        return new AjaxResponseModel<AjaxReturnLoginData>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "登陆成功", returnData);
                    } catch (Exception e) {
                        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
                    }
                } else {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "该账号已登陆!");
                }
            } else {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "登陆失败");
            }
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }

    //商户退出登陆（写入本次登陆的退出记录）
    @Override
    @Transactional
    public AjaxResponseModel merchantSignOut(String sessionUserName) {
        try {
            //通过商户登陆后保存到session中的数据key值,取到商户的token
            LoginUser user = (LoginUser) session.getAttribute(Globals.MERCHANT_SESSION_USER);
            String merchantUser = "";
            if (user != null) {
                if (user.getToken() != null) {
                    AccountShopLoginLog accountShopLoginLog = new AccountShopLoginLog();
                    accountShopLoginLog.setAccountShopToken(user.getToken());
                    accountShopLoginLog.setSignOutDate(format(new Date(), NORM_DATETIME_PATTERN));
                    accountShopLoginLog.setId(AccountShopMessageAPI.accountShopLoginLogId);
                    accountShopLoginLog.setCurrentState(Globals.MERCHANT_QUIT_OFFLINE);
                    try {
                        accountShopLoginLogService.insertCurrentSignOutLog(accountShopLoginLog);
                        String accountShopToken = user.getToken();
                        session.removeAttribute(Globals.MERCHANT_SESSION_USER);
                        if (session.getAttribute(Globals.MERCHANT_SESSION_USER) == null) {
                            //删除cookie
                            clearCookies(request, response);
                            AccountShop accountShop = new AccountShop();
                            accountShop.setToken(accountShopToken);
//                            accountShop.setPcLoginState(false);
                            try {
                                accountShopService.changeAccountShopLoginState(accountShop);
                                return new AjaxResponseModel<String>(Globals.COMMON_SUCCESS_AND_JUMP_URL, " 退出登陆成功", "/merchant/login.htm");
                            } catch (Exception e) {
                                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                            }
                        } else {
                            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                        }
                    } catch (Exception e) {
                        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                    }
                } else {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                }
            } else {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
            }
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
        }
    }

    //管理员登陆
    @Override
    @Transactional
    public AjaxResponseModel adminLogin(LoginUser loginUser) {
        try {
            loginUser.setPassword(MD5Utils.getMD5Encode(loginUser.getPassword()));
            try {
                LoginUser user = userService.checkUser(loginUser);
                //判断当前账号是否已经登陆
                if (user != null) {
                    if (!user.getPcLoginState()) {
                        //当前该账号不在线
                        session.setAttribute(Globals.ADMIN_SESSION_USER, user);
                        //调用方法记录本次登陆记录
                        AdminLoginLog adminLoginLog = new AdminLoginLog();
                        adminLoginLog.setLoginDate(format(new Date(), NORM_DATETIME_PATTERN));
                        adminLoginLog.setCurrentState(Globals.ADMIN_LOGIN_ONLINE);
                        adminLoginLog.setAdminToken(user.getToken());
                        try {
                            adminLoginLogService.insertAdminCurrentLoginLog(adminLoginLog);
                            LoginServiceImpl.adminLoginLogId = adminLoginLog.getId();
                            AjaxReturnLoginData returnData = new AjaxReturnLoginData(user.getUserName(), user.getPassword(), loginUser.getRole(), "admin/index", user.getToken());
                            //登陆成功，改变账号状态
//                            Admin admin = new Admin();
//                            admin.setToken(user.getToken());
//                            admin.setPcLoginState(true);
//                            adminService.changeAdminLoginState(admin);
                            return new AjaxResponseModel<AjaxReturnLoginData>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "登陆成功", returnData);
                        } catch (Exception e) {
                            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
                        }
                    } else {
                        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "该账号已登陆!");
                    }
                } else {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "登陆失败");
                }
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
            }
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //管理员退出
    @Override
    public AjaxResponseModel adminSignOut(String sessionUserName) {
        try {
            //通过商户登陆后保存到session中的数据key值,取到商户的token
            LoginUser user = (LoginUser) session.getAttribute(Globals.ADMIN_SESSION_USER);
            String merchantUser = "";
            if (user != null) {
                if (user.getToken() != null) {
                    AdminLoginLog adminLoginLog = new AdminLoginLog();
                    adminLoginLog.setAdminToken(user.getToken());
                    adminLoginLog.setSignOutDate(format(new Date(), NORM_DATETIME_PATTERN));
                    adminLoginLog.setId(LoginServiceImpl.adminLoginLogId);
                    adminLoginLog.setCurrentState(Globals.ADMIN_QUIT_OFFLINE);
                    try {
                        adminLoginLogService.insertAdminCurrentSignOutLog(adminLoginLog);
                        String adminToken = user.getToken();
                        session.removeAttribute(Globals.ADMIN_SESSION_USER);
                        if (session.getAttribute(Globals.ADMIN_SESSION_USER) == null) {
                            AccountShop accountShop = new AccountShop();
//                            Admin admin = new Admin();
//                            admin.setToken(adminToken);
//                            admin.setPcLoginState(false);
                            try {
//                                adminService.changeAdminLoginState(admin);
                                return new AjaxResponseModel<String>(Globals.COMMON_SUCCESS_AND_JUMP_URL, " 退出登陆成功", "/admin/login");
                            } catch (Exception e) {
                                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                            }
                        } else {
                            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                        }
                    } catch (Exception e) {
                        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                    }
                } else {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
                }
            } else {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
            }
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "退出登陆失败");
        }
    }
}
