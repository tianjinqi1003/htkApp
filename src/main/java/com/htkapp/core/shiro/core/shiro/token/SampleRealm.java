package com.htkapp.core.shiro.core.shiro.token;

import com.htkapp.core.customShiro.UserToken;
import com.htkapp.core.shiro.core.shiro.token.manager.TokenManager;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * 登陆验证
 */
public class SampleRealm extends AuthorizingRealm {


    @Resource
    private UserServiceI userService;

    public SampleRealm() {
        super();
    }


    //认证信息，主要针对用户登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UserToken token = (UserToken) authcToken;
        LoginUser loginUser = userService.checkUser(token.getUsername(),token.getPassWd(),token.getRole());
        if (loginUser == null) {
            throw new AccountException("账号或密码不正确！");
        } else if (0 == loginUser.getAccountStatus()) {
            throw new DisabledAccountException("账号已经禁止登录！");
        }
        return new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long userId = TokenManager.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户ID查询角色（role），放入到Authorization里。
//		Set<String> roles = roleService.findRoleByUserId(userId);
        Set<String> roles = new HashSet<>();
        roles.add("1");
        info.setRoles(roles);
        //根据用户ID查询权限（permission），放入到Authorization里。
//		Set<String> permissions = permissionService.findPermissionByUserId(userId);
        Set<String> permissions = new HashSet<>();
        permissions.add("/merchant/index");
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 清空当前用户权限信息
     */
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
