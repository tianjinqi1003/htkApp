package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.OrderBuyPackageMapper;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;
import com.htkapp.modules.merchant.pay.service.OrderBuyPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yinqilei on 17-7-1.
 */

@Service
public class OrderBuyPackageServiceImpl implements OrderBuyPackageService {

    @Autowired
    private OrderBuyPackageMapper orderBuyPackageDao;

    /* ========================接口开始========================== */
    //通过订单id查询购买过的套餐信息
    @Override
    public OrderBuyPackage getOrderPackageById(int orderId) throws Exception {
        try {
            return orderBuyPackageDao.getOrderPackageByIdDAO(orderId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //插入团购订单的团购产品信息
    @Override
    @Transactional
    public void insertBuyPackageDataByOrderId(OrderBuyPackage orderBuyPackage) throws Exception {
        try {
            int row = orderBuyPackageDao.insertBuyPackageDataByOrderIdDAO(orderBuyPackage);
            if (row <= 0) {
                throw new Exception("插入团购订单商品失败");
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过订单id删除记录
    @Override
    public void deleteOrderBuyPackageByOrderId(int orderId) {
        int row = orderBuyPackageDao.deleteOrderBuyPackageByOrderIdDAO(orderId);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    /* =========================接口结束============================= */
}
