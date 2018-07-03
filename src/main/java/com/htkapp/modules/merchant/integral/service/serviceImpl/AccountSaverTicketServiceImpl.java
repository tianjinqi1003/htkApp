package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.AccountSaverTicketMapper;
import com.htkapp.modules.merchant.integral.entity.AccountSaverTicket;
import com.htkapp.modules.merchant.integral.service.AccountSaverTicketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountSaverTicketServiceImpl implements AccountSaverTicketService {

    @Resource
    private AccountSaverTicketMapper saverTicketDao;

    //通过id 和 shopId 查找单个优惠券信息
    @Override
    public AccountSaverTicket getTicketMesByIdAndShopId(int id, int shopId) {
        return saverTicketDao.getTicketMesByIdAndShopIdDAO(id, shopId);
    }

    //通过shopId 查找店铺下的优惠券兑换活动
    @Override
    public List<AccountSaverTicket> getSaverTicketListByShopId(int shopId, int pageNumber, int pageLimit) {
        PageHelper.startPage(pageNumber, pageLimit);
        List<AccountSaverTicket> resultList = saverTicketDao.getSaverTicketListByShopIdDAO(shopId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //插入兑换活动
    @Override
    public void insertSaverTicketByShopId(AccountSaverTicket saverTicket) {
        int row = saverTicketDao.insertSaverTicketByShopIdDAO(saverTicket);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //根据条件查询兑换活动
    @Override
    public List<AccountSaverTicket> getSaverTicketListByCondition(int shopId, String orderDesc, String time, int flag) {
        List<AccountSaverTicket> resultList = saverTicketDao.getSaverTicketListByConditionDAO(shopId, orderDesc, time, flag);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //作废活动
    @Override
    public void updateActiveState(int id, int stateId) {
        int row = saverTicketDao.updateActiveStateDAO(id, stateId);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //开启活动
    @Override
    public void updateActiveOpenTime(int id, String startTime) {
        int row = saverTicketDao.updateActiveOpenTimeDAO(id, startTime);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //作废活动
    @Override
    public void updateActiveCloseTime(int id, String endTime) {
        int row = saverTicketDao.updateActiveCloseTimeDAO(id, endTime);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    @Override
    public int getTicketActiveCounts(List<Integer> shopIds) {
        int counts = saverTicketDao.getTicketActiveCounts(shopIds);
        return  counts;
    }

    @Override
    public List<AccountSaverTicket> getTicketActiveUnderWayCounts(List<Integer> shopIds) {
        List<AccountSaverTicket> results = saverTicketDao.getTicketActiveUnderWayCounts(shopIds);
        return  results;
    }
}
