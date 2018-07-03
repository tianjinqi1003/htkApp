package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopMessageMapper;
import com.htkapp.modules.merchant.shop.entity.ShopMessage;
import com.htkapp.modules.merchant.shop.service.ShopMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by terabithia on 11/30/17.
 */
@Service
public class ShopMessageServiceImpl implements ShopMessageService {

    @Resource
    private ShopMessageMapper shopMessageDao;


    @Override
    public void updateDeliverFee(ShopMessage shopMessage) {
        int row = shopMessageDao.updateDeliverFeeDAO(shopMessage);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }
}
