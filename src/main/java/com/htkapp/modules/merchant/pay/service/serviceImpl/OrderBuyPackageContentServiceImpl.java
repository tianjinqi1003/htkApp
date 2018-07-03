package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.OrderBuyPackageContentMapper;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackageContent;
import com.htkapp.modules.merchant.pay.service.OrderBuyPackageContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderBuyPackageContentServiceImpl implements OrderBuyPackageContentService {

    @Resource
    private OrderBuyPackageContentMapper orderBuyPackageContentDao;


    //插入团购订单套餐内商品信息
    @Override
    public void insertOrderBuyPackageContentByGroupBuyOrder(OrderBuyPackageContent orderBuyPackageContent) {
        int row = orderBuyPackageContentDao.insertOrderBuyPackageContentByGroupBuyOrderDAO(orderBuyPackageContent);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //通过订单id查找团购套餐内产品
    @Override
    public List<OrderBuyPackageContent> getOrderBuyPackageContentList(int orderId) {
        List<OrderBuyPackageContent> resultList = orderBuyPackageContentDao.getOrderBuyPackageContentListDAO(orderId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
}
