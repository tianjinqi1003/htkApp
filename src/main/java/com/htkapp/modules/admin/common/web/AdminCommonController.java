package com.htkapp.modules.admin.common.web;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.customShiro.CusTokenManage;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.admin.common.service.AdminCommonControllerServiceI;
import com.htkapp.modules.common.dto.AjaxReturnLoginData;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.shop.dao.RegisterApplyMapper;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.RegisterApply;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.RegisterApplyService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCommonController {

    public final String adminDirector = "admin/";
    @Resource
    private OtherUtils otherUtilsService;
    @Resource
    private AdminCommonControllerServiceI adminCommonControllerService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private RegisterApplyMapper registerApplyDao;


    //==================================管理登陆页
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String adminLoginPage(Model model) {
        model.addAttribute("date", new Date().getTime());
        try {
            HttpServletRequest request = otherUtilsService.getRequestByMethod();
            if (request != null) {
                HttpSession session = request.getSession();
                Object object = session.getAttribute(Globals.ADMIN_SESSION_USER);
                if (object != null) {
                    //重定向到主页
                    return "redirect:index";
                }
            }
        } catch (Exception e) {
            return adminDirector + "login";
        }
        return adminDirector + "login";
    }

    //登陆post
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel adminLoginPost(LoginUser user, String loginVCode) {
        try {
            HttpServletRequest request = otherUtilsService.getRequestByMethod();
            HttpSession session = request.getSession();
            String verifyCode = (String) session.getAttribute(Globals.ADMIN_KAPTCHAT_KEY);
            if (loginVCode.equals(verifyCode)) {
                user = CusTokenManage.loginByUser(user, user.getRememberMe());
                session.setAttribute(Globals.ADMIN_SESSION_USER, user);
                AjaxReturnLoginData returnData = new AjaxReturnLoginData(user.getUserName(), user.getPassword(), user.getRole(), "/admin/index", user.getToken());
                return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "成功", returnData, "/admin/index");
            } else {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "验证码错误,请重新输入");
            }
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }

    //退出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel logout() {
        try {
            CusTokenManage.logoutUser();
            return new AjaxResponseModel<String>(Globals.COMMON_SUCCESS_AND_JUMP_URL, " 退出登陆成功", null, "/admin/login");
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "退出失败");
        }
    }

    //获取当前所有店铺权限
    @RequestMapping(value = "/getPermissions", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel getPermissions(){
        return adminCommonControllerService.getPermissions();
    }

    //确认店铺权限
    @RequestMapping(value = "/enterPermissions", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel enterPermissions(AjaxRequestParams params){
        return adminCommonControllerService.enterPermissions(params);
    }

    //开通接口，更改账号状态
    @RequestMapping(value = "/changeAccountState", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel changeAccountState(AjaxRequestParams params) {
        AccountShop accountShop = new AccountShop();
        accountShop.setPcLoginState(1);
        accountShop.setId(params.getId());
        try {
            boolean result = accountShopService.changeAccountShopLoginState(accountShop);
            if(result) {
                AccountShop accountShop1 = accountShopService.getAccountShopDataById(params.getId());
                int row = registerApplyDao.deleteByIdDAO(accountShop1.getId());
                if(row <= 0){
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
                }
            }
            return new AjaxResponseModel(result ? Globals.COMMON_SUCCESSFUL_OPERATION :Globals.COMMON_OPERATION_FAILED);
        }catch (Exception e){
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }

    }

    //==================================管理首页
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String adminIndexPage(Model model) {
        model.addAttribute("date", new Date().getTime());
        model.addAttribute("home_page", true);
        return adminDirector + "index";
    }

    //==================================广告管理

    //==================================app管理

    //==================================用户管理
    @RequestMapping(value = "/manageUser", method = RequestMethod.GET)
    public String getUserManagePage(Model model, RequestParams params,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModelAndAdmin(model, map);
        adminCommonControllerService.getManageUserData(model, pageNum, params);
        return adminDirector + "manage_user";
    }

    //==================================分类管理

    //==================================权限管理
    @RequestMapping(value = "/permissionPage", method = RequestMethod.GET)
    public String permissionPage(Model model,@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                 @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum) {
        model.addAttribute("date", new Date().getTime());
        model.addAttribute("permission_page", true);
        RequestParams params = new RequestParams();
        params.setModel(model);
        params.setPage(page);
        params.setPageNum(pageNum);
        adminCommonControllerService.permissionPage(params);
        return adminDirector + "permission";
    }
//    @RequestMapping(value = "/permissionPageByPage", method = RequestMethod.POST)
//    public String permissionPageByPost(Model model,@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
//                                 @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum) {
//        model.addAttribute("date", new Date().getTime());
//        model.addAttribute("permission_page", true);
//        RequestParams params = new RequestParams();
//        params.setModel(model);
//        params.setPageNum(pageNum);
//        params.setPage(page);
//        adminCommonControllerService.permissionPage(params);
//        return adminDirector + "permission";
//    }

    //==================================注册申请列表
    @RequestMapping("/registerApplyList")
    public String getRegisterApply(Model model, RequestParams params,
                                   @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        model.addAttribute("registerApp_page", true);
        model.addAttribute("date", new Date().getTime());
        params.setPageNum(pageNum);
        params.setModel(model);
        adminCommonControllerService.getRegisterApply(params);
        return adminDirector + "/registerApply";
    }

    //分配权限
    @RequestMapping(value = "/assignPermissions", method = RequestMethod.POST)
    public AjaxResponseModel assignPermissions(AjaxRequestParams params){
        return null;
    }
    //================================账号管理开始

    //app用户
    @RequestMapping("/accountManage/appAccount")
    public String appAccount(Model model) {
        model.addAttribute("account", true);
        model.addAttribute("app_a", true);
        return "admin/accountManage_user";
    }

    //商家用户
    @RequestMapping("/accountManage/merchantAccount")
    public String merchantAccount(Model model) {
        model.addAttribute("account", true);
        model.addAttribute("merchant_a", true);
        return "admin/accountManage_merchant";
    }
    //================================账号管理结束

    //===============================app版本管理开始
    //app版本管理首页
    @RequestMapping("appVersionManage/index")
    public String appAccountIndex(Model model) {
        model.addAttribute("app_page", true);
        return "admin/version_";
    }
    //===============================app版本管理结束


    //===============================分类管理开始
    //外卖分类管理
    @RequestMapping("/category/takeoutCategory")
    public String takeoutCategory(Model model) {
        model.addAttribute("category_page", true);
        return "admin/category_takeout";
    }

    //团购分类管理
    @RequestMapping("/category/groupBuyCategory")
    public String groupBuyCategory(Model model) {
        model.addAttribute("category_page", true);
        return "admin/category_groupBuy";
    }

    //===============================分类管理结束


    //===============================系统设置开始

    //===============================系统设置结束

}
