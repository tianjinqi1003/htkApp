package com.htkapp.modules.merchant.integral.dao;


import com.htkapp.modules.merchant.integral.entity.AccountSaverTicket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountSaverTicketMapper {

    //通过id 和 shopId 查找单个优惠券信息
    AccountSaverTicket getTicketMesByIdAndShopIdDAO(int id, int shopId);
    //通过shopId 查找店铺下的优惠券兑换活动
    List<AccountSaverTicket> getSaverTicketListByShopIdDAO(int shopId);
    //插入兑换活动
    int insertSaverTicketByShopIdDAO(AccountSaverTicket saverTicket);
    //根据条件查询兑换活动
    List<AccountSaverTicket> getSaverTicketListByConditionDAO(@Param("shopId") int shopId, @Param("orderDesc") String orderDesc, @Param("time") String time, @Param("flag") int flag);
    //作废活动
    int updateActiveStateDAO(int id, int stateId);
    //开启活动
    int updateActiveOpenTimeDAO(int id, String startTime);
    //作废活动
    int updateActiveCloseTimeDAO(int id, String endTime);

    int getTicketActiveCounts(@Param("shopIdList") List<Integer> shopIds);

    List<AccountSaverTicket> getTicketActiveUnderWayCounts(@Param("shopIdList") List<Integer> shopIds);
}