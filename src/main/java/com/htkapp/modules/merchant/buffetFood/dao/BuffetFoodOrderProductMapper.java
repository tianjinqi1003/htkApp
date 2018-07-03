package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuffetFoodOrderProductMapper {

    /* ====================接口开始====================== */
    //插入订单下的购买商品详情
    int insertProductDetailsUnderOrderDAO(BuffetFoodOrderProduct product);
    //根据订单号查询该订单所购买商品列表
    List<BuffetFoodOrderProduct> getOrderProductListByIdDAO(int orderId);
    //根据订单号和订单产品自增id号查找订单产品详情
    List<BuffetFoodOrderProduct> getOrderProductDetailByIdDAO(@Param("orderId") int orderId, @Param("idLists") List<Integer> idLists);
    //根据订单产品id号改变状态
    int changeOrderProductStateByIdDAO(@Param("orderId") int orderId, @Param("idLists") List<Integer> idLists, @Param("state") int state);
    //删除订单中已退的产品
    int delOrderProductByIdDAO(@Param("idLists") List<Integer> idLists);
    //通过订单id删除记录
    int deleteOrderProductByOrderIdDAO(int orderId);
    //根据订单号查询订单下的商口列表
    List<BuffetFoodOrderProduct> getOrderProductListByOrderNumberDAO(String orderNumber);
	//根据订单id修改bz数据
    int updataOrderProductBzById(@Param("product")BuffetFoodOrderProduct product,@Param("bz")int bz);
    /* ====================接口结束====================== */
}
