package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.modules.merchant.pay.dao.TakeoutOrderMapper;
import com.htkapp.modules.merchant.pay.service.TakeoutOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TakeoutOrderServiceImpl implements TakeoutOrderService {

    @Resource
    private TakeoutOrderMapper takeoutOrderDao;

    /* =========================接口开始=============================== */
    //更改催单状态
    @Override
    public void updateReminderStateByOrderId(int orderId, int stateId) throws Exception {
        int row = takeoutOrderDao.updateReminderStateByOrderIdDAO(orderId, stateId);
        if(row <= 0){
            throw new Exception("更改失败");
        }
    }

    //插入数据
    @Override
    public void insertReminderStateByOrderId(int orderId) throws Exception {
       int row  = takeoutOrderDao.insertReminderStateByOrderIdDAO(orderId);
       if(row <= 0){
           throw new Exception("插入失败");
       }
    }

    /* =========================接口结束=============================== */
}
