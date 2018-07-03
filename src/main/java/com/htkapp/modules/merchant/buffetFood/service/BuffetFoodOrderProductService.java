package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;

import java.util.List;

public interface BuffetFoodOrderProductService {

    /* =========================接口开始======================== */
    //插入订单下的购买商品详情
    void insertProductDetailsUnderOrder(BuffetFoodOrderProduct product) throws Exception;
    //根据订单号查询该订单所购买商品列表
    List<BuffetFoodOrderProduct> getOrderProductListById(int orderId) throws Exception;
    //根据订单号和订单产品自增id号查找订单产品详情
    List<BuffetFoodOrderProduct> getOrderProductDetailById(int orderId,List<Integer> idLists);
    //根据订单产品id号改变状态
    void changeOrderProductStateById(int orderId,List<Integer> idLists,int state) throws Exception;
    //删除订单中已退的产品
    void delOrderProductById(List<Integer> idLists) throws Exception;
    //通过订单id删除记录
    void deleteOrderProductByOrderId(int orderId);
    //根据订单号查询订单下的商口列表
    List<BuffetFoodOrderProduct> getOrderProductListByOrderNumber(String orderNumber);

    //根据产品信息修改bz在数据库中的状态
    int updataOrderProductBzById(BuffetFoodOrderProduct product,int bz);

    /* =========================接口结束======================== */
}
