package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import com.htkapp.modules.merchant.shop.dao.ShopSaverTicketRecordMapper;
import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;
import com.htkapp.modules.merchant.shop.service.ShopSaverTicketRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShopSaverTicketRecordServiceImpl implements ShopSaverTicketRecordService{

    @Resource
    private ShopSaverTicketRecordMapper ticketRecordDao;



    @Override
    public void insertAccountExchangeRecord(ShopSaverTicketRecord ticketRecord) {
        int row = ticketRecordDao.insertAccountExchangeRecordDAO(ticketRecord);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

	@Override
	public int updateAccountExchangeRecordDao(Integer ticketId, String token, int shopId,int ticketQuantity) {
		int row =ticketRecordDao.updateAccountExchangeRecordDao(ticketId,token,shopId,ticketQuantity);
		if(row<=0) {
			return row;
		}
		return row;
	}

	@Override
	public ShopSaverTicketRecord getTicketByTokenAndShopIdAndTicketId(Integer ticketId, String token, int shopId) {
		ShopSaverTicketRecord str=ticketRecordDao.getShopSaverTicketRecordByTokenAndShopIdAndTicketId(ticketId, token, shopId);
		if(str!=null) {
			return str;
		}
		return null;
	}
}
