package com.htkapp.core.customShiro;

import com.htkapp.modules.common.entity.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by terabithia on 10/1/17.
 * token管理
 */
public class CusTokenManage {


    //获取当前登陆成功的用户对象
    public static LoginUser getCurUserData(){
        return (LoginUser) SecurityUtils.getSubject().getPrincipal();
    }

    //获得Subject
    public static Subject getCurSubject(){
        return SecurityUtils.getSubject();
    }

    //根据拿到的用户凭据登陆
    public static LoginUser loginByUser(LoginUser user, boolean rememberMe){
        UserToken userToken = new UserToken(user.getUserName(),user.getPassword(),user.getRole());
        userToken.setRememberMe(rememberMe);
        SecurityUtils.getSubject().login(userToken);
        return getCurUserData();
    }

    //用户退出
    public static void logoutUser(){
        SecurityUtils.getSubject().logout();
    }

    //获取当前用户名的userId
    public static Long getUserId(){
        return getCurUserData() == null ? null : Long.parseLong(getCurUserData().getUserId().toString());
    }
}
