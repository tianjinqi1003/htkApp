package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.AccountMapper;
import com.htkapp.modules.merchant.integral.dao.IntegralManageRecordMapper;
import com.htkapp.modules.merchant.integral.dao.IntegralMapper;
import com.htkapp.modules.merchant.integral.dto.AccountIntegralList;
import com.htkapp.modules.merchant.integral.entity.Integral;
import com.htkapp.modules.merchant.integral.service.IntegralService;
import com.htkapp.modules.merchant.shop.dao.AccountShopMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
@Service
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IntegralMapper integralDao;

    /* ==========================接口开始================================ */
    //获取店铺下的用户积分列表
    @Override
    public List<AccountIntegralList> getAccountIntegralListById(int accountShopId, String userName, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<AccountIntegralList> resultList = integralDao.getAccountIntegralListByIdDAO(accountShopId, userName);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //传入需要积分值，判断用户积分值是否满足要求
    @Override
    public int determineTheIntegralByValue(int integralValue, String token, int shopId) {
        //根据用户token 和 商铺id 查找用户的积分值
        //比对用户积分值 和 所需积分值  满足条件则返回1 否则返回0
        Integral integral = integralDao.getUserIntegralByAccountTokenDAO(token, shopId);
        if (integral != null) {
            //判断
            return integral.getVal() > integralValue ? 1 : 0;
        }
        return 0;
    }

    /* ===========================接口结束================================= */

    /* ============================JSP接口开始================================== */
    //根据商户token查找积分用户列表
    @Override
    public List<Integral> getIntegralUserListByShopToken(String token, String condition, int pageNumber, int pageLimit) {
        String orderDesc = "integral.gmt_create desc";
        PageHelper.startPage(pageNumber, pageLimit);
        List<Integral> resultList = integralDao.getIntegralUserListByShopTokenDAO(token, condition, orderDesc);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Integral> getIntegralUserListByShopIds(List<Integer> shopIds, int pageNumber, int pageLimit) {
        String orderDesc = "gmt_create desc";
        PageHelper.startPage(pageNumber, pageLimit);
        List<Integral> resultList = integralDao.getIntegralUserListByShopIds(shopIds, orderDesc);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    //抵扣或赠送积分
    @Override
    public void presentOrDeductionIntegralByToken(String accountToken, int shopId, int val, int operationId) {
        int row = integralDao.presentOrDeductionIntegralByTokenDAO(accountToken, shopId, val, operationId);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //根据用户token获取数据
    @Override
    public Integral getUserIntegralByAccountToken(String token, int shopId) {
        return integralDao.getUserIntegralByAccountTokenDAO(token, shopId);
    }

    @Override
    public void insertUserIntegralDAO(Integral integral) {
        int row = integralDao.insertUserIntegralDAO(integral);
        if (row <= 0) {
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    @Override
    public void updateIntegralFlagByToken(String token, int flagId) {
        int row = integralDao.updateIntegralFlagByTokenDAO(token, flagId);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    @Override
    public void updateLatestConsumeTime(String token, Integer shopId, String time) {
        int row = integralDao.updateLatestConsumeTime(token, shopId, time);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    @Override
    public void updateLatestGetTime(String token, Integer shopId, String time) {
        int row = integralDao.updateLatestGetTime(token, shopId, time);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //根据token查找用户积分
    @Override
    public Integer getIntegralValByAccountToken(String token, String accountToken) {
        Integer result = integralDao.getIntegralValByAccountTokenDAO(token, accountToken);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public Integer getVal(String token, int shopId) {
        Integer result = integralDao.getVal(token, shopId);
        return result;
    }

    @Override
    public Integer updateIntegral(String token, int shopId, int val) {
        return integralDao.updateIntegral(token, shopId, val);
    }

    /* ===========================JSP接口结束================================ */

}
