package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.AccountShopCashMentionMapper;
import com.htkapp.modules.merchant.pay.entity.AccountShopCashMention;
import com.htkapp.modules.merchant.pay.service.AccountShopCashMentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountShopCashMentionServiceImpl implements AccountShopCashMentionService {

    @Autowired
    private AccountShopCashMentionMapper cashMentionDao;

    /* =====================接口开始========================= */
    //插入转账记录
    @Override
    public void insertTransfer(AccountShopCashMention cashMention) throws Exception {
        try {
            int row = cashMentionDao.insertTransferDAO(cashMention);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据交易订单号查看订单是否已提现
    @Override
    public boolean validationStatus(String orderNumber, String encryptToken, int cMark) throws Exception {
        try {
            if (cMark == 0 || cMark == 1) {
                AccountShopCashMention result = cashMentionDao.validationStatusDAO(orderNumber, encryptToken);
                return result != null;
            } else {
                AccountShopCashMention result = cashMentionDao.validationStatusByBuffetFoodDAO(orderNumber, encryptToken);
                return result != null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

/* =====================接口结束========================= */
}
