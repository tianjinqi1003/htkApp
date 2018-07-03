package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.dto.AccountIntegralList;
import com.htkapp.modules.merchant.integral.entity.Integral;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Administrator on 2017/3/18.
 */
public interface IntegralService {


    /* =========================接口开始============================== */
    //获取店铺下的用户积分列表
    List<AccountIntegralList> getAccountIntegralListById(int accountShopId, String userName, int pageNo, int pageLimit) throws Exception;
    //传入需要积分值，判断用户积分值是否满足要求
    int determineTheIntegralByValue(int integralValue, String token, int shopId);
    //根据token查找用户积分
    Integer getIntegralValByAccountToken(String token, String accountToken);
    /* ==========================接口结束============================== */


    Integer getVal(String token,int shopId);

    Integer updateIntegral(String token ,int shopId,int val);

    /* ==========================JSP页面接口开始============================= */
    //根据商户token查找积分用户列表
    List<Integral> getIntegralUserListByShopToken(String token, String condition, int pageNumber, int pageLimit);
    List<Integral> getIntegralUserListByShopIds(List<Integer> shopIds,  int pageNumber, int pageLimit);
    //抵扣或赠送积分
    void presentOrDeductionIntegralByToken(String accountToken, int shopId, int val, int operationId);
    //根据用户token获取数据
    Integral getUserIntegralByAccountToken(String token, int shopId);
    //插入用户积分记录
    void insertUserIntegralDAO(Integral integral);
    //更改用户积分状态
    void updateIntegralFlagByToken(String token, int flagId);

//    void updateLatestConsumeTime(String token, Integer shopId, String time);
    void updateLatestConsumeTime(String token, Integer shopId, String time);

//    void updateLatestGetTime(String token, Integer shopId, String timestamp);
    void updateLatestGetTime(String token, Integer shopId, String timestamp);

    /* =======================JSP页面接口结束=============================== */
}
