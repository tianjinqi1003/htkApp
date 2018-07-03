package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.dto.AccountIntegralList;
import com.htkapp.modules.merchant.integral.entity.Integral;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * 积分列表
 */

public interface IntegralMapper {


    /* ==========================接口开始============================== */
    //获取店铺下的用户积分列表
    List<AccountIntegralList> getAccountIntegralListByIdDAO(@Param("accountShopId") int accountShopId, @Param("userName") String userName);
    //根据token查找用户积分
    Integer getIntegralValByAccountTokenDAO(String token, String accountToken);
    /* ==========================接口结束============================== */

    Integer getVal(String token, int shopId);

    Integer updateIntegral(String token, int shopId,int val);


    /* ==========================JSP页面接口开始================================ */
    //根据商户token查找积分用户列表
    List<Integral> getIntegralUserListByShopTokenDAO(@Param("token") String token, @Param("condition") String condition, @Param("orderDesc") String orderDesc);
    List<Integral> getIntegralUserListByShopIds(@Param("shopIdList") List<Integer> shopIds,@Param("orderDesc") String orderDesc);
    //抵扣积分
    int presentOrDeductionIntegralByTokenDAO(@Param("accountToken") String accountToken, @Param("shopId") int shopId, @Param("val") int val, @Param("operationId") int operationId);
    //根据用户token获取数据
    Integral getUserIntegralByAccountTokenDAO(String token, int shopId);
    //更改用户积分状态
    int updateIntegralFlagByTokenDAO(String token, int flagId);
    //插入积分记录
    int insertUserIntegralDAO(Integral integral);

    int updateLatestConsumeTime(String token, Integer shopId , String time);

    int updateLatestGetTime(String token, Integer shopId, String time);
    /* ==========================JSP页面接口结束================================ */
}
