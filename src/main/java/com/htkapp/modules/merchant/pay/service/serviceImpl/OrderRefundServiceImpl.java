package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.OrderRefundMapper;
import com.htkapp.modules.merchant.pay.entity.OrderRefund;
import com.htkapp.modules.merchant.pay.service.OrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRefundServiceImpl implements OrderRefundService {

    @Autowired
    private OrderRefundMapper orderRefundDao;

    /* ===================接口开始==================== */
    //插入退款记录
    @Override
    public void insertRefund(OrderRefund orderRefund) throws Exception{
        try {
            int row = orderRefundDao.insertRefundDAO(orderRefund);
            if(row <= 0){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /* ===================接口结束==================== */


    /* ====================JSP页面接口开始======================== */
    //查找当前退款单数量
    @Override
    public int getCurrentRefundOrderNumber(String accountShopToken) throws Exception {
        try {
            return orderRefundDao.getCurrentRefundOrderNumberDAO(accountShopToken);
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }


    /* =====================JSP页面接口结束========================== */
}
