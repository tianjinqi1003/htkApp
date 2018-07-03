package com.htkapp.core.customShiro;

import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.common.service.PermissionService;
import com.htkapp.modules.common.service.RoleService;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by terabithia on 10/1/17.
 * 定自义realm
 */
public class CusRealm extends AuthorizingRealm {

    @Resource
    private UserServiceI userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    public CusRealm(){
        super();
    }

    //认证用户登陆
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken userToken = (UserToken) authenticationToken;
        //根据用户名和密码查找数据库,验证用户是否存在
        LoginUser loginUser = userService.checkUser(userToken.getUsername(),userToken.getPassWd(),userToken.getRole());
        if(loginUser == null){
            throw new AccountException("用户不存在或密码错误!");
        }else if(loginUser.getAccountStatus() == -1){
            throw new DisabledAccountException("账号已被禁止！");
        } else if(loginUser.getAccountStatus() == -2){
            throw new DisabledAccountException("账号还没有授权开启");
        }
        loginUser.setRole(userToken.getRole());  //存入用户身份标识
        //传入 principals  credential realmName
        return new SimpleAuthenticationInfo(loginUser,loginUser.getPassword(),loginUser.getUserName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户id
        Long userId = CusTokenManage.getUserId();
        //根据用户id查询当前用户的角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (userId == null){
            return simpleAuthorizationInfo;
        }
        try {
            //获取用户角色
            Set<String> roleList = roleService.getRoleListByUserId(userId);
            //获取用户权限
            Set<String> permissionList = permissionService.getUserPermissionListByUserId(userId);
            simpleAuthorizationInfo.setRoles(roleList);
            simpleAuthorizationInfo.setStringPermissions(permissionList);
            return simpleAuthorizationInfo;
        }catch (Exception e){
            LoggerUtils.fmtError(getClass(),e, "获取授权失败:"+e.getMessage());
            //返回一个空的对象
            return new SimpleAuthorizationInfo();
        }
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
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
}
