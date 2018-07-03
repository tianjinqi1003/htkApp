package com.htkapp.core.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.CheckMobile;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.UserServiceI;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserServiceI userService;
    private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    private List<String> excludedUrls;

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludedUrls = excludeUrls;
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求的URL
        String requestUri = request.getRequestURI();
        StringBuffer requestUrl = request.getRequestURL();
        Boolean isMobile = false;
        PrintWriter out = null;
        JSONObject loginError = null;
        APIResponseModel apiResponse = null;

        //subString用于截取字符串,指定起始位置和终止位置
        String merchantProjectPath = requestUrl.substring(0, requestUrl.indexOf(Globals.PROJECT_URL)) + Globals.PROJECT_URL + Globals.MERCHANT_LOGIN_URL;
        String adminProjectPath = requestUri.substring(0, requestUri.indexOf(Globals.PROJECT_URL)) + Globals.PROJECT_URL + Globals.ADMIN_LOGIN_URL;
        //查看拦截路径
        for (String excludeUrl : excludedUrls) {
            //contains()方法返回boolean类型
            //当参数出现在此字符串中返回true

            /**
             * @author 马鹏昊
             * @desc 去掉xml属性的前后空格、回车符、制表符
             */
            String formatStr = excludeUrl;
            formatStr = formatStr.replace("\n","");
            formatStr = formatStr.replace("\t","");
            formatStr = formatStr.trim();
            if (requestUri.contains(formatStr)) {
                return true;
            }
        }

        //request.getHeader("USER-AGENT")用来获取客户端浏览器和操作系统信息
        //toLowerCase 用于把所有的字符串转为小写
        String reqHeader = request.getHeader("USER-AGENT");
        //获取Session      http://192.168.0.7:8080/htkApp/API/appMember/index?token=db5af7ca-e946-483a-8acd-d3b0678a4c8f&role=C&shopId=1
        HttpSession session = request.getSession();
        if (StringUtils.isEmpty(reqHeader)) {
            //是null　则再判断是否是app接入后台
            String token = request.getParameter("token"); //手机端登陆要携带的参数(身份验证)
            String role = request.getParameter("role");  //手机端登陆要携带的参数(用户身份标识)
            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(role)) {
                //请求参数为空,返回请求参数错误
                out = response.getWriter();
                loginError = new JSONObject();
                apiResponse = new APIResponseModel(Globals.API_REQUEST_BAD);
                loginError.put("code", apiResponse.getCode());
                loginError.put("message", apiResponse.getMessage());
                out.append(loginError.toString());
                out.flush();
                return false;
            } else if (userService.checkToken(token, role) != null) {
                return true;
            } else if (userService.checkToken(token, role) == null) {
                //请求参数为空,返回请求参数错误
                out = response.getWriter();
                loginError = new JSONObject();
                apiResponse = new APIResponseModel(Globals.API_FAIL);
                loginError.put("code", apiResponse.getCode());
                loginError.put("message", "app_token无效或已失效，请重新登录!==" + apiResponse.getMessage());
                out.append(loginError.toString());
                out.flush();
                return false;
            }
            //没有header的验证异常
            out = response.getWriter();
            loginError = new JSONObject();
            apiResponse = new APIResponseModel(Globals.API_INTERCEPTOR_NOT_HAVE_HEAD_ERROR);
            loginError.put("code", apiResponse.getCode());
            loginError.put("message", "没有Header==验证异常＝＝＝＝请联系后台管理员!");
            out.append(loginError.toString());
            out.flush();
            return false;
        } else {
            //校验是否是手机登录
            isMobile = CheckMobile.check(reqHeader.toLowerCase());
            if (isMobile) {
                String shopIdVal = request.getParameter("shopId");
                if (session.getAttribute(Globals.API_HTML_SESSION) != null) {
                    if(StringUtils.isNotEmpty(shopIdVal)){
                        session.setAttribute(Globals.API_HTML_SESSION_SHOP_ID, shopIdVal);
                    }
                    return true;
                } else {
                    //是手机浏览器访问
                    String token = request.getParameter("token"); //手机端登陆要携带的参数(身份验证)
                    String role = request.getParameter("role");  //手机端登陆要携带的参数(用户身份标识)
                    Integer shopId = null;
                    response.setCharacterEncoding("UTF8");
                    if (StringUtils.isNotEmpty(shopIdVal)) {
                        shopId = Integer.parseInt(shopIdVal);
                    }
                    if (StringUtils.isEmpty(token) || StringUtils.isEmpty(role)) {
                        //请求参数为空,返回请求参数错误
//                        out = response.getWriter();
//                        loginError = new JSONObject();
//                        apiResponse = new APIResponseModel(Globals.API_REQUEST_BAD);
//                        loginError.put("code", apiResponse.getCode());
//                        loginError.put("message", "浏览器端==" + apiResponse.getMessage());
//                        out.append(loginError.toString());
//                        out.flush();
                        return true;
                    } else if (userService.checkToken(token, role) != null) {
                        if (StringUtils.isNotEmpty(shopId.toString())) {
                            //传入了shopId,则保存到session中
                            session.setAttribute(Globals.API_HTML_SESSION_SHOP_ID, shopId);
                            session.setAttribute(Globals.API_HTML_SESSION,userService.checkToken(token, role));
                        } else {
                            //验证session中是否存在商铺id
                            if (session.getAttribute(Globals.API_HTML_SESSION_SHOP_ID) != null) {
                                return true;
                            } else {
                                out = response.getWriter();
                                loginError = new JSONObject();
                                apiResponse = new APIResponseModel(Globals.API_FAIL);
                                loginError.put("code", apiResponse.getCode());
                                loginError.put("message", "没有shopId值" + apiResponse.getMessage());
                                out.append(loginError.toString());
                                out.flush();
                                return false;
                            }
                        }
                        return true;
                    } else if (userService.checkToken(token, role) == null) {
                        //请求参数为空,返回请求参数错误
                        out = response.getWriter();
                        loginError = new JSONObject();
                        apiResponse = new APIResponseModel(Globals.API_FAIL);
                        loginError.put("code", apiResponse.getCode());
                        loginError.put("message", "浏览器端token无效或已失效，请重新登录!==" + apiResponse.getMessage());
                        out.append(loginError.toString());
                        out.flush();
                        return false;
                    }
                }
            } else {
                //判断请求路径
                if (requestUri.contains("admin")) {
                    //管理页面
                    if (session.getAttribute(Globals.ADMIN_SESSION_USER) != null) {
                    	//TODO 再次出现卡死时，判断user.getState()和user.getShopName()
                        return true;
                    } else {
//                        Cookie cookieUserName = CookiesUtils.getCookieByName(request, Globals.ADMIN_COOKIE_USER_NAME);
//                        Cookie cookiePassword = CookiesUtils.getCookieByName(request, Globals.ADMIN_COOKIE_PASSWORD);
//                        Cookie cookieRole = CookiesUtils.getCookieByName(request, Globals.ADMIN_COOKIE_ROLE);
//                        if (cookieUserName != null && cookiePassword != null && cookieRole != null) {
//                            //从cookie中取出保存在浏览器端的用户名和密码
//                            String userName = cookieUserName.getValue();
//                            String password = cookiePassword.getValue();
//                            String role = cookieRole.getValue();
//                            LoginUser currentUser = new LoginUser();
//                            currentUser.setUserName(userName);
//                            currentUser.setPassword(password);
//                            currentUser.setRole(role);
//                            try {
//                                LoginUser loginUser = userService.checkUser(currentUser);
//                                if (loginUser != null) {
//                                    session.setAttribute(Globals.ADMIN_SESSION_USER, loginUser);
//                                    return true;
//                                } else {
//                                    //不符合条件的，跳转到登录界面
//                                    response.sendRedirect(adminProjectPath);
//                                    return false;
//                                }
//                            } catch (Exception e) {
//                                response.sendRedirect(adminProjectPath);
//                                return false;
//                            }
//                        } else {
//                            response.sendRedirect(adminProjectPath);
//                            return false;
//                        }
                        response.sendRedirect(adminProjectPath);
                        return false;
                    }
                } else if (requestUri.contains("merchant")) {
                    //商家页面
                    if (session.getAttribute(Globals.MERCHANT_SESSION_USER) != null) {
                        return true;
                    } else {
//                        Cookie cookieUserName = CookiesUtils.getCookieByName(request, Globals.MERCHANT_COOKIE_USER_NAME);
//                        Cookie cookiePassword = CookiesUtils.getCookieByName(request, Globals.MERCHANT_COOKIE_PASSWORD);
//                        Cookie cookieRole = CookiesUtils.getCookieByName(request, Globals.MERCHANT_COOKIE_ROLE);
//                        if (cookieUserName != null && cookiePassword != null && cookieRole != null) {
//                            //从cookie中取出保存在浏览器端的用户名和密码
//                            String userName = cookieUserName.getValue();
//                            String password = cookiePassword.getValue();
//                            String role = cookieRole.getValue();
//                            LoginUser currentUser = new LoginUser();
//                            currentUser.setUserName(userName);
//                            currentUser.setPassword(password);
//                            currentUser.setRole(role);
//                            try {
//                                LoginUser loginUser = userService.checkUser(currentUser);
//                                if (loginUser != null) {
//                                    session.setAttribute(Globals.MERCHANT_SESSION_USER, loginUser);
//                                    return true;
//                                } else {
//                                    //不符合条件的，跳转到登录界面
//                                    response.sendRedirect(merchantProjectPath);
//                                    return false;
//                                }
//                            } catch (Exception e) {
//                                response.sendRedirect(merchantProjectPath);
//                                return false;
//                            }
//                        } else {
//                            response.sendRedirect(merchantProjectPath);
//                            return false;
//                        }
                    }
                    response.sendRedirect(merchantProjectPath);
                    return false;
                } else {
                    return false;
                }
            }
            //有header的验证异常
            out = response.getWriter();
            loginError = new JSONObject();
            apiResponse = new APIResponseModel(Globals.API_INTERCEPTOR_HAVE_HEAD_ERROR);
            loginError.put("code", apiResponse.getCode());
            loginError.put("message", "有Header==验证异常＝＝＝＝请联系后台管理员!");
            out.append(loginError.toString());
            out.flush();
            return false;
        }
    }


}

