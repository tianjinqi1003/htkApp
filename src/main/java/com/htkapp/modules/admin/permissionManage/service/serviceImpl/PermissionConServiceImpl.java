package com.htkapp.modules.admin.permissionManage.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.admin.permissionManage.service.PermissionConService;
import com.htkapp.modules.common.entity.AccountShopRole;
import com.htkapp.modules.common.entity.Role;
import com.htkapp.modules.common.service.AccountShopRoleService;
import com.htkapp.modules.common.service.RoleService;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by terabithia on 1/3/18.
 */

@Service
public class PermissionConServiceImpl implements PermissionConService {

    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private RoleService roleService;
    @Resource
    private AccountShopRoleService accountShopRoleService;

    @Override
    public AjaxResponseModel getPermissionsList() {
        try {
            List<Role> roleList = roleService.getRoleList();
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", roleList);
        }catch (Exception var0) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    @Override
    public AjaxResponseModel updatePermissionByMerchantId(AjaxRequestParams params) {
        try {
            AccountShopRole accountShopRole = new AccountShopRole();
            accountShopRole.setuId(params.getId());
            accountShopRole.setrId(params.getRoleId());
            AccountShopRole accountShopRole1 = accountShopRoleService.getAccountShopRoleById(params.getId());
            if(accountShopRole1 == null){
                accountShopRoleService.insertAccountShopRole(accountShopRole);
            }else {
                accountShopRoleService.updateAccountShopRole(accountShopRole);
            }
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION);
        }catch (Exception var0) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    @Override
    public AjaxResponseModel updateUseTimeByMerchantId(AjaxRequestParams params) {
        try {
            accountShopService.updateUseTimeById(params.getId(),params.getTime());
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
        }catch (Exception var0) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }
}
