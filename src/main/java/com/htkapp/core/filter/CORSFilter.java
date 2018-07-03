package com.htkapp.core.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
        	System.out.println("====================111111==================================================");
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //跨域
            String origin = httpRequest.getHeader("Origin");
            if (origin == null){
                httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            }else {
                httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            }

            httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept,X-Cookie");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");
            if ( httpRequest.getMethod().equals("OPTIONS") ) {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                return;
            }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void destroy() {

    }
}
