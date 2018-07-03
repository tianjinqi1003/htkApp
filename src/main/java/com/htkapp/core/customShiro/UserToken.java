package com.htkapp.core.customShiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * Created by terabithia on 10/1/17.
 * UsernamePasswordToken的子类
 */
public class UserToken extends UsernamePasswordToken implements Serializable {

    private static final long serialVersionUID = -6451794657814516274L;


    public UserToken(String userName, String passWd, String role){
        super(userName,passWd); //调用父类的构造方法初始化
        this.passWd = passWd;
        this.role = role;
    }


    private String passWd;

    private String role;

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
