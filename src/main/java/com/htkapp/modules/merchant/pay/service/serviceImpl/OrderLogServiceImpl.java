package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.OrderLogMapper;
import com.htkapp.modules.merchant.pay.entity.OrderLog;
import com.htkapp.modules.merchant.pay.service.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yinqilei on 17-6-29.
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    private OrderLogMapper orderLogDao;

    /* =============接口开始====================== */
    //插入订单日志
    @Override
    @Transactional
    public boolean insertOrderLog(OrderLog orderLog) throws OrderException {
        try {
            int row = orderLogDao.insertOrderLogDAO(orderLog);
            return row > 0;
        } catch (OrderException e1) {
            throw new OrderException(Globals.CALL_DATABASE_ERROR);
        }
    /* =================接口结束=========================== */
    }
}