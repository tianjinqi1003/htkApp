package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodOrderProductMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuffetFoodOrderProductServiceImpl implements BuffetFoodOrderProductService {

    @Resource
    private BuffetFoodOrderProductMapper boPDao;

    /* =============================接口开始============================== */
    //插入订单下的购买商品详情
    @Override
    public void insertProductDetailsUnderOrder(BuffetFoodOrderProduct product) throws Exception {
        try {
            int row = boPDao.insertProductDetailsUnderOrderDAO(product);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(product.toString());
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号查询该订单所购买商品列表
    @Override
    public List<BuffetFoodOrderProduct> getOrderProductListById(int orderId) throws Exception {
        try {
            List<BuffetFoodOrderProduct> resultList = boPDao.getOrderProductListByIdDAO(orderId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号和订单产品自增id号查找订单产品详情
    @Override
    public List<BuffetFoodOrderProduct> getOrderProductDetailById(int orderId, List<Integer> idLists) {
        List<BuffetFoodOrderProduct> resultList = boPDao.getOrderProductDetailByIdDAO(orderId, idLists);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    //根据订单产品id号改变状态
    @Override
    public void changeOrderProductStateById(int orderId, List<Integer> idLists, int state) throws Exception {
        try {
            int row = boPDao.changeOrderProductStateByIdDAO(orderId, idLists, state);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //删除订单中已退的产品
    @Override
    public void delOrderProductById(List<Integer> idLists) throws Exception{
        int row = boPDao.delOrderProductByIdDAO(idLists);
        if(row <= 0){
            throw new Exception("删除失败");
        }
    }

    //通过订单id删除记录
    @Override
    public void deleteOrderProductByOrderId(int orderId) {
        int row = boPDao.deleteOrderProductByOrderIdDAO(orderId);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    //根据订单号查询订单下的商口列表
    @Override
    public List<BuffetFoodOrderProduct> getOrderProductListByOrderNumber(String orderNumber) {
        List<BuffetFoodOrderProduct> productList = boPDao.getOrderProductListByOrderNumberDAO(orderNumber);
        if(productList != null && productList.size() > 0){
            return productList;
        }
        return null;
    }

    //根据产品信息修改bz在数据库中的状态
    @Override
    public int updataOrderProductBzById(BuffetFoodOrderProduct product, int bz) {
        return boPDao.updataOrderProductBzById(product, bz);
    }
    /* =============================接口结束============================== */

}
