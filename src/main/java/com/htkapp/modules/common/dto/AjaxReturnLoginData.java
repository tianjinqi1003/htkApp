package com.htkapp.modules.common.dto;

/**
 * Created by yinqilei on 17-7-5.
 * 商户登陆，异常提交form验证返回结果封装类
 */
public class AjaxReturnLoginData {

    private String userName;  //用户名

    private String password;  //密码

    private String role;   //身份标识

    private String token;

    private String url; //成功后要跳转的url

    public AjaxReturnLoginData(String userName, String password, String role, String url, String token){
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.url = url;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
