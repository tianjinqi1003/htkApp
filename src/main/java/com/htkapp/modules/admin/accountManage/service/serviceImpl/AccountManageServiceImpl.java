package com.htkapp.modules.admin.accountManage.service.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.admin.accountManage.service.AccountManageService;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.htkapp.core.OtherUtils.getPageByKey;

/**
 * Created by yinqilei on 17-7-10.
 *
 */
@Service
public class AccountManageServiceImpl implements AccountManageService {

    @Autowired
    private AccountServiceI accountService;
    @Autowired
    private AccountShopServiceI accountShopService;


    /* ==================接口开始===================== */
    //获取用户管理页面的Json的数据
    @Override
    public TableResponseModel getUserManageJsonData(HttpServletRequest request) {
        int pageNo = getPageByKey(request,"pageSize");
        int pageLimit = getPageByKey(request,"limit");
        //获取查询条件
        String userName = request.getParameter("userName");
        if(StringUtils.isNotEmpty(userName)){
            //条件查找
            Account account = new Account();
            account.setUserName(userName);
            try {
                List<Account> accountList = accountService.getAccountListByCondition(account,pageNo,pageLimit);
                return new TableResponseModel<List<Account>>(new PageInfo<Account>(accountList).getPages(),accountList);
            }catch (Exception e){
                return new TableResponseModel<Object>(0,null);
            }
        }else {
            //无条件查找
            try {
                List<Account> accountList = accountService.getAccountList(pageNo,pageLimit);
                return new TableResponseModel<List<Account>>(new PageInfo<Account>(accountList).getPages(),accountList);
            }catch (Exception e){
                return new TableResponseModel<Object>(0,null);
            }
        }
    }


    //获取商户管理表格json数据
    @Override
    public TableResponseModel getMerchantManageJsonData(HttpServletRequest request) {
        int pageNo = getPageByKey(request,"pageSize");
        int pageLimit = getPageByKey(request,"limit");
        //获取查询条件
        String userName = request.getParameter("userName");
        if(StringUtils.isNotEmpty(userName)){
            //条件查找
            AccountShop accountShop = new AccountShop();
            accountShop.setUserName(userName);
            try {
                List<AccountShop> accountShopList = accountShopService.getAccountShopListByCondition(accountShop,pageNo,pageLimit);
                return new TableResponseModel<List<AccountShop>>(new PageInfo<AccountShop>(accountShopList).getPages(),accountShopList);
            }catch (Exception e){
                return new TableResponseModel<Object>(0,null);
            }
        }else {
            //无条件查找
            try {
                List<AccountShop> accountShopList = accountShopService.getAccountShopList(pageNo,pageLimit);
                return new TableResponseModel<List<AccountShop>>(new PageInfo<AccountShop>(accountShopList).getPages(),accountShopList);
            }catch (Exception e){
                return new TableResponseModel<Object>(0,null);
            }
        }
    }

    /* ===================接口结束===================== */
}
