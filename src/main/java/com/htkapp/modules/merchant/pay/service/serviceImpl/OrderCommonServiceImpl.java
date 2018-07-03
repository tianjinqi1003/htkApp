package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.OrderCommonMapper;
import com.htkapp.modules.merchant.pay.entity.OrderCommon;
import com.htkapp.modules.merchant.pay.service.OrderCommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderCommonServiceImpl implements OrderCommonService {

    @Resource
    private OrderCommonMapper orderCommonDao;

    //联合查询外卖订单、团购订单、订座订单、自助点餐订单
    @Override
    public List<OrderCommon> getAllOrderList(String accountToken,int pageNumber, int pageLimit) throws Exception {
        try {
//            PageHelper.startPage(pageNumber,pageLimit);
//            String orderByDesc = "gmt_create DESC";
            List<OrderCommon> resultList = orderCommonDao.getAllOrderListDAO(accountToken,pageNumber,pageLimit);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }
            return null;
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }
}
