package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.AppShippingAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户外卖收货地址
 */

public interface AppShippingAddressMapper {


    /* ==================接口开始======================== */
    //获得用户的外卖收货地址列表
    List<AppShippingAddress> getAccountShippingAddressListDAO(String token);
    //添加用户外卖收货地址
    int addAccountShippingAddressDAO(AppShippingAddress appShippingAddress);
    //修改用户外卖收货地址
    int changeAccountShippingAddressDAO(AppShippingAddress appShippingAddress);
    //删除用户外卖收货地址
    int deleteAccountShippingAddressDAO(@Param("token") String token, @Param("addressId") int addressId);



    /* ==================接口结束======================== */
    int deleteByPrimaryKey(Integer addressId);

    int insert(AppShippingAddress record);

    int insertSelective(AppShippingAddress record);

    AppShippingAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(AppShippingAddress record);

    int updateByPrimaryKey(AppShippingAddress record);
}