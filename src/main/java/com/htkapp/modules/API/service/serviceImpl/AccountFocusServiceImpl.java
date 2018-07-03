package com.htkapp.modules.API.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.AccountFocusMapper;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.entity.AccountFocus;
import com.htkapp.modules.API.service.AccountFocusService;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.merchant.integral.entity.Integral;
import com.htkapp.modules.merchant.integral.service.IntegralService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class AccountFocusServiceImpl implements AccountFocusService {
    @Resource
    private AccountFocusMapper accountFocusDao;
    @Resource
    private AccountServiceI accountService;
    @Resource
    private IntegralService integralService;
    @Resource
    private ShopServiceI shopService;

    /* ==============接口开始========================= */
    //根据用户token查找用户关注的店铺分页
    @Override
    public List<AccountFocus> getCollectListByTokenByPage(String token, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<AccountFocus> focusList = accountFocusDao.getCollectListByTokenDAO(token,null);
            if (focusList != null && focusList.size() > 0) {
                return focusList;
            }
            return null;
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据用户token查找用户关注的店铺
    @Override
    public List<AccountFocus> getCollectListByToken(String token) throws Exception {
        try {
            List<AccountFocus> focusList = accountFocusDao.getCollectListByTokenDAO(token,null);
            if (focusList != null && focusList.size() > 0) {
                return focusList;
            } else {
                return null;
            }
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //app用户收藏店铺
    @Override
    public boolean collectionStore(String token, int shopId) throws Exception {
        try {
            int row = accountFocusDao.collectionStoreDAO(token, shopId);
            return row > 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //app用户取消收藏店铺
    @Override
    public boolean cancelTheStore(String token, int shopId) throws Exception {
        try {
            int row = accountFocusDao.cancelTheStoreDAO(token, shopId);
            return row > 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过用户token获取关注的店铺列表id
    @Override
    public Set<Integer> getCollectionShopIdListByToken(String token, int mark) {
        List<AccountFocus> resultList = accountFocusDao.getCollectListByTokenDAO(token, mark);
        Set<Integer> setList = new HashSet<>();
        if (resultList != null && resultList.size() > 0) {
            for (AccountFocus each : resultList) {
                if (each.getShopId() != null) {
                    setList.add(each.getShopId());
                }
            }
            return setList;
        }
        return null;
    }

    /* ==============接口结束========================= */


    /* ====================JSP页面接口开始====================== */
    //用户收藏店铺
    @Override
    public void joinCollectionByAccountIdAndShopId(String accountToken, Integer shopId) {
        int row = accountFocusDao.joinCollection(accountToken, shopId);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //检查是否收藏店铺
    @Override
    public int checkCollectionByAccountIdAndShopId(String accountToken, Integer shopId) {
        AccountFocus accountFocus = accountFocusDao.checkCollection(accountToken, shopId);
        return accountFocus == null ? 0 : 1;
    }

    //根据手机号查找用户是否关注
    @Override
    public AccountFocus getAccountFocusByUserPhone(String userPhone, String accountShopToken) {
        return accountFocusDao.getAccountFocusByUserPhoneDAO(userPhone, accountShopToken);
    }

    //加入收藏(加入收藏前检查用户是否是第一次加入,如果不是第一次加入,则找回积分值，如果是第一次加入则创建积分值记录)
    @Override
    @Transactional
    public int checkStateAndJoinMemberByToken(String token, int shopId) {
        try {
            Account account = accountService.selectByToken(token);
            Shop shop = shopService.getShopDataById(shopId);
            if(account != null){
                int row = checkCollectionByAccountIdAndShopId(token,shopId);
                if(row > 0){
                    //用户已关注
                    return -2;
                }else {
                    Integral integral = integralService.getUserIntegralByAccountToken(token,shop.getShopId());
                    if(integral == null){
                        //没有关注过, 同时插入关注记录，初始积分表记录
                       joinCollectionByAccountIdAndShopId(token,shop.getShopId());
                       Integral integralObj = new Integral();
                       integralObj.setAccountToken(token);
                       integralObj.setShopId(shop.getShopId());
                       integralService.insertUserIntegralDAO(integralObj);
                       //操作成功
                       return 0;
                    }else {
                        if(integral.getFlag() == 0){
                            //加入收藏，开启之前的积分记录
                            joinCollectionByAccountIdAndShopId(token, shop.getShopId());
                            integralService.updateIntegralFlagByToken(token,1);
                            //操作成功
                            return 0;
                        }else {
                            //用户未关注店铺，但积分值可见
                            return -3;
                        }
                    }
                }
            }else {
                //用户为空
                return -1;
            }
        }catch (Exception e){
            //执行异常
            return -4;
        }
    }

    /* ====================JSP页面接口结束====================== */
}
