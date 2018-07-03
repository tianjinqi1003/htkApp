package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.AccountSaverTicket;

import java.util.List;

public interface AccountSaverTicketService {

    //通过id 和 shopId 查找单个优惠券信息
    AccountSaverTicket getTicketMesByIdAndShopId(int id, int shopId);
    //通过shopId 查找店铺下的优惠券兑换活动
    List<AccountSaverTicket> getSaverTicketListByShopId(int shopId, int pageNumber, int pageLimit);
    //插入兑换活动
    void insertSaverTicketByShopId(AccountSaverTicket saverTicket);
    //根据条件查询兑换活动
    List<AccountSaverTicket> getSaverTicketListByCondition(int shopId, String orderDesc, String time, int flag);
    //作废活动
    void updateActiveState(int id, int stateId);
    //开启活动
    void updateActiveOpenTime(int id, String startTime);
    //作废活动
    void updateActiveCloseTime(int id, String endTime);

    int getTicketActiveCounts(List<Integer> shopIds);

    List<AccountSaverTicket> getTicketActiveUnderWayCounts(List<Integer> shopIds);
}
