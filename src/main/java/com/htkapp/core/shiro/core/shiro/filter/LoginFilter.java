package com.htkapp.core.shiro.core.shiro.filter;

import com.htkapp.core.customShiro.CusTokenManage;
import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//登陆filter
public class LoginFilter extends AccessControlFilter {

    @Resource
    private UserServiceI userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            return false;
        }
        HttpSession session = ((HttpServletRequest) request).getSession();
        LoginUser loginUser = (LoginUser) subject.getPrincipal();
        if (loginUser != null) {
            //判断用户身分类型
            LoginUser user1 = userService.checkUser(loginUser.getUserName(), loginUser.getPassword(), loginUser.getRole());
            if(loginUser.getRole().equals("E")){
                //admin
                session.setAttribute(Globals.ADMIN_SESSION_USER, user1);
            }else if(loginUser.getRole().equals("S")) {
                //merchant
                session.setAttribute(Globals.MERCHANT_SESSION_USER, user1);
            }
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {

        LoggerUtils.fmtDebug(getClass(), "登陆过滤器执行...");
        LoginUser token = CusTokenManage.getCurUserData();

        if (null != token || isLoginRequest(request, response)) {// && isEnabled()
            //isLoginRequest 判断当前请求是否是登陆请求（验证当前请求地址）
            return Boolean.TRUE;
        }

        //通过头部判断是否是ajax请求
        if (ShiroFilterUtils.isAjax(request)) {// ajax请求
            Map<String, String> resultMap = new HashMap<String, String>();
            System.out.println("判断是否是ajax请求");
            LoggerUtils.debug(getClass(), "当前用户没有登录，并且是Ajax请求！");
            resultMap.put("login_status", "300");
            resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");//当前用户没有登录！
            ShiroFilterUtils.out(response, resultMap);
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        //保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(request, response);
        return Boolean.FALSE;
    }


}
