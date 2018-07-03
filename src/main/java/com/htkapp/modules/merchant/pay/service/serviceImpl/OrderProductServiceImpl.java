package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.OrderProductMapper;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductMapper orderProductDao;

    /* =======================接口开始======================= */
    //插入关联订单的产品详情
    @Override
    public void insertProductInfoByOrder(OrderProduct orderProduct) throws OrderException {
        try {
            int row = orderProductDao.insertProductInfoByOrderDAO(orderProduct);
            if (row <= 0) {
                throw new OrderException(Globals.CALL_DATABASE_ERROR);
            }
        } catch (OrderException e) {
            throw new OrderException(e.getMessage());
        }
    }

    //通过订单id获得订单中的产品信息
    @Override
    public List<OrderProduct> getProductListByOrderId(int orderId) throws Exception {
        try {
            List<OrderProduct> orderProductList = orderProductDao.getProductListByOrderIdDAO(orderId);
            if (orderProductList != null && orderProductList.size() > 0) {
                return orderProductList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过订单id删除记录
    @Override
    public void deleteOrderProductByOrderId(int orderId) {
        int row = orderProductDao.deleteOrderProductByOrderIdDAO(orderId);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    /* ========================接口结束============================ */

    /* =======================JSP接口开始============================== */
    //通过订单id获取订单中所购买的商品名
    @Override
    public List<String> getOrderProductNameByOrderId(long orderId) {
        try {
            List<String> resultList = orderProductDao.getOrderProductNameByOrderIdDAO(orderId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    /* ======================JSP接口结束============================== */
}
