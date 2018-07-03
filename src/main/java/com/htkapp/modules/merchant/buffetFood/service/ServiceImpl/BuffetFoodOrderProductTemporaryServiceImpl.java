package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodOrderProductTemporaryMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProductTemporary;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderProductTemporaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuffetFoodOrderProductTemporaryServiceImpl implements BuffetFoodOrderProductTemporaryService {

    @Resource
    private BuffetFoodOrderProductTemporaryMapper temporaryDao;

    /* =======================接口开始========================== */
    //插入数据
    @Override
    public void insertProductTemporary(BuffetFoodOrderProductTemporary temporary) {
        int row = temporaryDao.insertProductTemporaryDAO(temporary);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
    //通过订单id查找数据
    @Override
    public List<BuffetFoodOrderProduct> getProductListById(int orderId) {
        List<BuffetFoodOrderProduct> resultList = temporaryDao.getProductListByIdDAO(orderId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //通过订单id删除数据
    @Override
    public void deleteProductListById(int orderId) {
        int row = temporaryDao.deleteProductListByIdDAO(orderId);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }
    /* =======================接口结束========================== */
}
