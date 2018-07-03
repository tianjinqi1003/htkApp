package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.htkapp.core.MD5Utils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.common.dao.UserMessageMapper;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserServiceI {

    @Resource
    private UserMessageMapper userMessageMapper;


    /* ===========================接口开始================================= */
    //检测用户身份标识和身份验证
    @Override
    public LoginUser checkToken(String token, String role) throws Exception {
        // TODO Auto-generated method stub
        if (StringUtils.isEmpty(role)) {
            throw new Exception("登录角色类型为空,请检查!");
        }
        LoginUser user = null;
        if (Globals.CUSTOMER_USER.equals(role)) {
            //通过用户身份验证查找用户信息，
            user = userMessageMapper.selectByUserToken(token);
        } else if (Globals.MERCHANT_USER.equals(role)) {
            user = userMessageMapper.selectByAccountShopToken(token);
        } else {
            user = userMessageMapper.selectByAdminToken(token);
        }

        //取出通过MD5加密码后的身份验证
        if(user != null){
            String saltToken = user.getSaltToken();
            //如果加密后的MD5密文Token和数据库中的一致，表示验证通过
            if (MD5Utils.getMD5Encode(token + saltToken).equals(user.getEncryptToken())) {
                return user;
            } else {
                throw new Exception("token验证不通过!");
            }
        }else {
            throw new Exception("用户不存在");
        }

    }

    //验证用户名（手机号）和密码　登陆
    @Override
    public LoginUser checkUser(LoginUser loginUser) throws Exception {

        String role = loginUser.getRole();
        if (StringUtils.isEmpty(role)) {
            throw new Exception("登录角色类型为空,请检查!");
        } else {
            try {
                if (Globals.CUSTOMER_USER.equals(role)) {
                    loginUser = userMessageMapper.selectByUser(loginUser);
                } else if (Globals.MERCHANT_USER.equals(role)) {
                    loginUser = userMessageMapper.selectByAccountShop(loginUser);
                } else {
                    loginUser = userMessageMapper.selectByAdmin(loginUser);
                }
            } catch (Exception e) {
                throw new Exception("数据库层验证用户名和密码错误操作异常");
            }
        }
        return loginUser;
    }

    //通过用户名和密码、role　查找用户信息
    @Override
    public LoginUser checkUser(String userName, String password, String role) {
        LoginUser user = new LoginUser();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);
        if (Globals.MERCHANT_USER.equals(role)) {
            user = userMessageMapper.selectByAccountShop(user);
        } else if (Globals.ADMIN_USER.equals(role)) {
            user = userMessageMapper.selectByAdmin(user);
        }
        return user;
    }

    /* =============================接口结束================================== */


}
