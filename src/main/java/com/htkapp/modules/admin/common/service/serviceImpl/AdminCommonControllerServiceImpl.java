package com.htkapp.modules.admin.common.service.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.htkapp.core.LogUtil;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.admin.common.dao.AdminMapper;
import com.htkapp.modules.admin.common.entity.Admin;
import com.htkapp.modules.admin.common.service.AdminCommonControllerServiceI;
import com.htkapp.modules.common.entity.AccountShopRole;
import com.htkapp.modules.common.entity.Role;
import com.htkapp.modules.common.service.AccountShopRoleService;
import com.htkapp.modules.common.service.RoleService;
import com.htkapp.modules.merchant.shop.dao.RegisterApplyMapper;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.RegisterApply;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.RegisterApplyService;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class AdminCommonControllerServiceImpl implements AdminCommonControllerServiceI {

    @Resource
    private AdminMapper adminDao;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private AccountServiceI accountService;
    @Resource
    private RegisterApplyService registerApplyService;
    @Resource
    private RoleService roleService;
    @Resource
    private AccountShopRoleService accountShopRoleService;
    @Resource
    private ShopServiceI shopService;

    /* ===================接口开始===================== */
    //改变用户登陆状态
    @Override
    public void changeAdminLoginState(Admin admin) throws RuntimeException {
        try {
            int row = adminDao.changeAdminLoginStateDAO(admin);
            if (row <= 0) {
                throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据用户名和密码验证用户
    @Override
    public Admin getAccountByNameAndPwd(String userName, String pwd) {
        return null;
    }

    /* ====================接口结束======================= */


    /* ============================JSP页面接口开始============================== */
    //用户管理页面数据
    @Override
    public void getManageUserData(Model model, int pageNum, RequestParams params) {
        int pageNumber = Globals.DEFAULT_PAGE_NO;
        int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
        try {
            String type = params.getType() == null ? "account" : params.getType();
            if (type.equals("merchant")) {
                List<AccountShop> accountShopList = accountShopService.getAccountShopList(pageNumber, pageLimit);
                model.addAttribute("data", accountShopList);
            } else if (type.equals("account")) {
                List<Account> accountList = accountService.getAccountList(pageNumber, pageLimit);
                model.addAttribute("data", accountList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注册申请列表
    @Override
    public void getRegisterApply(RequestParams params) {
        if (params != null && params.getPageNum() != null && params.getModel() != null) {
            //获取所有申请列表
            int pageNumber = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
            if (params.getPageNum() > 1) {
                pageNumber = params.getPageNum();
            }
            try {
                List<RegisterApply> resultList = registerApplyService.getRegisterApplyList(-1, pageNumber, pageLimit);
                PageInfo pageInfo = new PageInfo<>(resultList);
                Model model = params.getModel();
                model.addAttribute("data", resultList);
                model.addAttribute("pageInfo", pageInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //获取店铺角色列表
    @Override
    public AjaxResponseModel getPermissions() {
        try {
            List<Role> roleList = roleService.getRoleList();
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", roleList);
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //确认店铺权限
    @Override
    public AjaxResponseModel enterPermissions(AjaxRequestParams params) {
        if (params != null && params.getPhone() != null && params.getRoleId() != null) {
            //添加用户和角色关系,并新建店铺
            try {
                AccountShop accountShop = accountShopService.getAccountShopByPhoneAndUserName(params.getPhone());
                AccountShopRole role = new AccountShopRole();
                role.setuId(accountShop.getId());
                role.setrId(params.getRoleId());
                accountShopRoleService.insertAccountShopRole(role);
                //从申请列表中删除
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        } else {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //权限页面,查找商户列表
    @Override
    public void permissionPage(RequestParams params) {
        try {
            if (params.getPageNum() != null) {
//                int pageNumber = Globals.DEFAULT_PAGE_NO;
//                int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
//                if (params.getPageNum() > 1) {
//                    pageNumber = params.getPage();
//                }
                int pageLimit;
                int pageNumber;
                if (params.getPageNum() > 1) {
                    pageLimit = params.getPageNum();
                } else {
                    pageLimit = Globals.DEFAULT_PAGE_LIMIT;
                }
                if (params.getPage()!=null&&params.getPage() > 1) {
                    pageNumber = params.getPage();
                }else {
                    pageNumber = Globals.DEFAULT_PAGE_NO;
                }

                List<AccountShop> accountShopList = accountShopService.getAccountShopList(pageNumber, pageLimit);
                if (accountShopList != null) {
                    for (AccountShop each : accountShopList) {
                        StringBuilder sb = null;
                        //查找当前商户下的店铺、
                        List<Shop> shopList = shopService.getShopListByAccountShopId(each.getId());
                        //权限
                        Set<String> roleList = roleService.getRoleListNameByUserId(each.getId());
                        StringBuilder sbu = null;
                        if (roleList != null && roleList.size() > 0) {
                            sbu = new StringBuilder();
                            int i = 1;
                            for (String eve : roleList) {
                                sbu.append(eve);
                                if (i < roleList.size()) {
                                    sbu.append("、");
                                }
                                i++;
                            }
                        }
                        if (sbu != null) {
                            each.setRoleStr(sbu.toString());
                        }
                        if (shopList != null) {
                            sb = new StringBuilder();
                            int i = 1;
                            for (Shop every : shopList) {
                                String var0 = every.getMark() == 0 ? "外卖" : (every.getMark() == 1 ? "团购" : "自助点餐");
                                sb.append(every.getShopName() + "(" + var0 + ")");
                                if (i < shopList.size()) {
                                    sb.append("、");
                                }
                                i++;
                            }
                        }
                        if (sb != null) {
                            each.setShopListStr(sb.toString());
                        }
                    }
                }
                Model model = params.getModel();
                model.addAttribute("data", accountShopList);
                model.addAttribute("page", new PageInfo<>(accountShopList));
            } else {
                LogUtil.error(getClass(), "请求参数错误");
                return;
            }
        } catch (Exception var0) {
            LogUtil.error(getClass(), var0.toString(), var0);
        }
    }

    /* ===========================JSP页面接口结束=============================== */
}
