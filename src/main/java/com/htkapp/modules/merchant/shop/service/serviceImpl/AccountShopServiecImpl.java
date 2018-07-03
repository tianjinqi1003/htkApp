package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.shop.dao.AccountShopMapper;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountShopServiecImpl implements AccountShopServiceI {

    @Resource
    private AccountShopMapper accountShopDao;


    /* ==================接口开始============================ */
    //商户app端忘记密码，重置密码
    @Override
    public boolean changePasswordBySMS(AccountShop accountShop) throws Exception {
        try {
            int row = accountShopDao.changePasswordBySMSDAO(accountShop);
            return row > 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过token查询商户信息
    @Override
    public AccountShop selectByToken(String token) throws Exception {
        try {
            return accountShopDao.selectByTokenDAO(token);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //改变账号登陆状态信息
    @Override
    public boolean changeAccountShopLoginState(AccountShop accountShop) throws RuntimeException {
        try {
            int row = accountShopDao.changeAccountShopLoginStateDAO(accountShop);
            return row > 0;
        } catch (RuntimeException e) {
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过accountShopId查找商户信息
    @Override
    public AccountShop getAccountShopDataById(int accountShopId) throws Exception {
        try {
            AccountShop accountShop = accountShopDao.getAccountShopDataByIdDAO(accountShopId);
            if (accountShop != null) {
                return accountShop;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过手机号验证商户是否存在
    @Override
    public AccountShop getAccountShopByPhoneAndUserName(String phone) throws Exception {
        try {
            return accountShopDao.getAccountShopByPhoneAndUserNameDAO(phone);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //改变商户呢称
    @Override
    public void changeAccountShopNickName(AccountShop accountShop) throws Exception {
        try {
            int row = accountShopDao.changeAccountShopNickNameDAO(accountShop);
            if (row <= 0) {
                throw new Exception("失败");
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商户token查询商户的剩余使用时间
    @Override
    public AccountShop getUseTimeByToken(String token) throws Exception {
        try {
            return accountShopDao.getUseTimeByTokenDAO(token);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    @Override
    public int changeBindedAccount(Integer id, String newAccount) throws Exception {
        try {
            return accountShopDao.changeBindedAccount(id,newAccount);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    @Override
    public AccountShop getAlipayAccount(Integer id)  {
        try {
            return accountShopDao.getAlipayAccount(id);
        } catch (Exception e) {
           return  null;
        }
    }

/* =====================接口结束============================== */

    /* ====================JSP页面接口开始============================== */
    //通过条件获取商户列表
    @Override
    public List<AccountShop> getAccountShopListByCondition(AccountShop accountShop, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<AccountShop> accountShopList = accountShopDao.getAccountShopListByConditionDAO(accountShop);
            if (accountShopList != null && accountShopList.size() > 0) {
                return accountShopList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //获取商户列表
    @Override
    public List<AccountShop> getAccountShopList(int pageNo, int pageLimit) {
        PageHelper.startPage(pageNo, pageLimit);
        List<AccountShop> accountShopList = accountShopDao.getAccountShopListDAO();
        if (accountShopList != null && accountShopList.size() > 0) {
            return accountShopList;
        } else {
            return null;
        }
    }

    //注册新商户
    @Override
    public int registerAccountShopByPhone(AccountShop accountShop) throws Exception {
        try {
            int row = accountShopDao.registerAccountShopByPhoneDAO(accountShop);
            if (row > 0) {
                //注册成功，把返回的自增id赋给用用户呢称
//                accountShop.setNickName(accountShop.getId().toString());
//                changeAccountShopNickName(accountShop);
                return accountShop.getId();
            }
            return 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //找回密码验证身份成功后，根据手机号和密码重置密码
    @Override
    public void changePassword(String phone, String password) throws Exception {
        try {
            int row = accountShopDao.changePasswordDAO(phone, password);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过用户名和密码查找用户信息
    @Override
    public LoginUser getAccountShopByNameAndPwd(String userName, String pwd) {
        return accountShopDao.getAccountShopByNameAndPwdDAO(userName, pwd);
    }

    //修改密码
    @Override
    public void updatePassword(AccountShop accountShop) {
        int row = accountShopDao.updatePasswordDAO(accountShop);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //修改手机号
    @Override
    public void updateShopPhone(AccountShop accountShop) {
        int row = accountShopDao.updateShopPhoneDAO(accountShop);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //更改剩余时间
    @Override
    public void updateUseTimeById(int userId, String time) {
        int row = accountShopDao.updateUseTimeByIdDAO(userId, time);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    /* ========================JSP页面接口结束=============================== */

}
