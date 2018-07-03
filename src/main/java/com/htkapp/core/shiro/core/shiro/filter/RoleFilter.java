package com.htkapp.core.shiro.core.shiro.filter;

import com.htkapp.core.OtherUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.time.OffsetTime;

public class RoleFilter extends AccessControlFilter {

//	private static final String LOGIN_URL = "http://120.27.5.36:8080/htkApp/merchant/login";
//	private static final String UNAUTHORIZED_URL = "http://120.27.5.36:8080/htkApp/open/404";
	private static final String LOGIN_URL = "http://127.0.0.1:8080/htkApp/merchant/login";
	private static final String UNAUTHORIZED_URL = "http://127.0.0.1:8080/htkApp/open/404";
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String[] args = (String[])mappedValue;
		
		Subject subject = getSubject(request, response);
		if(args != null){
			for (String role : args) {
				if(subject.hasRole("role:" + role)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
			Subject subject = getSubject(request, response);  
	        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面  
	            saveRequest(request);  
	            WebUtils.issueRedirect(request, response, LOGIN_URL);  
	        } else {  
	            if (StringUtils.hasText(UNAUTHORIZED_URL)) {//如果有未授权页面跳转过去  
	                WebUtils.issueRedirect(request, response, UNAUTHORIZED_URL);  
	            } else {
	            	//否则返回401未授权状态码
	                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);  
	            }  
	        }  
		return false;
	}

}
