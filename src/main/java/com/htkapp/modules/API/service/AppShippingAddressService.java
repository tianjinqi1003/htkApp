package com.htkapp.modules.API.service;

import com.htkapp.modules.API.entity.AppShippingAddress;

import java.util.List;

/**
 * Created by yinqilei on 17-3-2.
 *
 */
public interface AppShippingAddressService {

    /** ===================接口开始======================== **/
    //通过用户token获得用户的外卖收货地址
    List<AppShippingAddress> getAccountShippingAddressList(String token) throws Exception;
    //添加用户外卖收货地址
    boolean addAccountShippingAddress(AppShippingAddress appShippingAddress) throws Exception;
    //修改用户外卖收货地址
    boolean changeAccountShippingAddress(AppShippingAddress appShippingAddress) throws Exception;
    //删除用户外卖收货地址
    boolean deleteAccountShippingAddress(String token, int addressId)throws Exception;


    /** ===================接口结束======================== **/

    int deleteByPrimaryKey(Integer addressId);

    int insert(AppShippingAddress record);

    int insertSelective(AppShippingAddress record);

    AppShippingAddress selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(AppShippingAddress record);

    int updateByPrimaryKey(AppShippingAddress record);
}
