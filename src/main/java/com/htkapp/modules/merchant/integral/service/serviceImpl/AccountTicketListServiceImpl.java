package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.AccountTicketListMapper;
import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import com.htkapp.modules.merchant.integral.service.AccountTicketListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountTicketListServiceImpl implements AccountTicketListService {

    @Resource
    private AccountTicketListMapper accountTicketListDao;

    //通过用户token 和 店铺id 查找优惠券列表
    @Override
    public List<AccountTicketList> getTicketListByTokenAndShopId(String token, int shopId) {
        List<AccountTicketList> ticketList = accountTicketListDao.getTicketListByTokenAndShopIdDAO(token, shopId);
        if(ticketList != null && ticketList.size() > 0){
            return ticketList;
        }
        return null;
    }

    /**
     *     通过用户token 和 优惠券id 查找此用户所拥有的优惠券列表
     *     @modified by 马鹏昊
     */
    @Override
    public List<AccountTicketList> getTicketListByTokenAndCouponId(String token, Integer ticket_id) {
        List<AccountTicketList> ticketList = accountTicketListDao.getTicketListByTokenAndCouponIdDAO(token, ticket_id);
        if(ticketList != null && ticketList.size() > 0){
            return ticketList;
        }
        return null;
    }

    @Override
    public void updateTicketListByTokenAndCouponIdDAO(int ticketQuantity, String token, Integer ticket_id) {
        int row = accountTicketListDao.updateTicketListByTokenAndCouponIdDAO(ticketQuantity,token,ticket_id);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //通过用户token 查找优惠券列表
    @Override
    public List<AccountTicketList> getTicketListByToken(String token) {
        List<AccountTicketList> ticketList = accountTicketListDao.getTicketListByTokenDAO(token);
        if(ticketList != null && ticketList.size() > 0){
            return ticketList;
        }
        return null;
    }

    //插入用户优惠券
    @Override
    public void insertAccountTicket(AccountTicketList ticketList) {
        int row = accountTicketListDao.insertAccountTicketDAO(ticketList);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

	@Override
	public AccountTicketList getTicketByTokenAndShopIdAndTicketId(Integer ticketId, String token, int shopId) {
		AccountTicketList atl=accountTicketListDao.getTicketByTokenAndShopIdAndTicketId(ticketId, token, shopId);
		if(atl!=null) {
			return atl;
		}
		return null;
	}

	@Override
	public int updataTicketByTokenAndShopIdAndTicketId(Integer ticketId, String token, int shopId,int ticketQuantity) {
		int a=accountTicketListDao.updataTicketByTokenAndShopIdAndTicketId(ticketId, token, shopId, ticketQuantity);
		if(a<=0) {
			return a;
		}
		return a;
	}

}
