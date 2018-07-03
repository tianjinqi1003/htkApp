//package com.htkapp.core.filter;
//
//import org.apache.http.util.TextUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * @author 马鹏昊
// * @desc
// * @date 2018/4/18
// */
//public class MySessionFilter implements Filter {
//
//    private String excludedPages;
//
//    private String[] excludedPageArray;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        excludedPages = filterConfig.getInitParameter("excludedPages");
//        if (null != excludedPages && excludedPages.length() > 0) {
//            excludedPageArray = excludedPages.split(",");
//        }
//        return;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        boolean isExcludedPage = false;
//        String servletPath = httpRequest.getServletPath();
//        for (String page : excludedPageArray) {
//            if (servletPath.contains(page)) {
//                isExcludedPage = true;
//                break;
//            }
//        }
//
//        boolean isFromLogin = false ;
//        String lastUrl = httpRequest.getHeader("Referer");
//        if (!TextUtils.isEmpty(lastUrl)&&lastUrl.contains("/merchant/login")){
//            isFromLogin = true ;
//            //登陆成功之后再次刷新这个页不要跳过判断session
//            httpResponse.setHeader("Referer","");
//        }
//        if (isExcludedPage||isFromLogin) {
//            chain.doFilter(request, response);
//            return;
//        } else {//
//            HttpSession session = httpRequest.getSession();
//            //取营业状态
//            String status = (String) session.getAttribute("status");
//            if(session == null || TextUtils.isEmpty(status)){
//                httpRequest.getRequestDispatcher("merchant/login").forward(request,response);
////            httpResponse.sendRedirect("login.jsp");
//            }else {
//                chain.doFilter(request, response);
//            }
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
