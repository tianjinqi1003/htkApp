package com.htkapp.modules.API.service.serviceImpl;

import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.AppShippingAddressMapper;
import com.htkapp.modules.API.entity.AppShippingAddress;
import com.htkapp.modules.API.service.AppShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yinqilei on 17-3-2.
 */
@Service
public class AppShippingAddressServiceImpl implements AppShippingAddressService {
    @Autowired
    private AppShippingAddressMapper appShippingAddressDao;


    /**
     * =============================接口开始==========================
     **/
    //通过用户token获得用户外卖收货地址列表
    @Override
    public List<AppShippingAddress> getAccountShippingAddressList(String token) throws Exception {
        try {
            List<AppShippingAddress> appShippingAddressList = appShippingAddressDao.getAccountShippingAddressListDAO(token);
            if (appShippingAddressList != null && appShippingAddressList.size() > 0) {
                return appShippingAddressList;
            } else {
                throw new NullDataException("没有收货地址,请新建收货地址");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception("数据库层根据用户token查询用户的外卖收货地址列表异常");
        }
    }

    //添加用户外卖收货地址
    @Override
    public boolean addAccountShippingAddress(AppShippingAddress appShippingAddress) throws Exception {
        try {
            int row = appShippingAddressDao.addAccountShippingAddressDAO(appShippingAddress);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("数据库层添加用户外卖收货地址失败");
        }
    }

    //修改用户外卖收货地址
    @Override
    public boolean changeAccountShippingAddress(AppShippingAddress appShippingAddress) throws Exception {
        try {
            int row = appShippingAddressDao.changeAccountShippingAddressDAO(appShippingAddress);
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception("数据库层修改外卖收货地址异常");
        }
    }


    //删除用户外卖收货地址
    @Override
    public boolean deleteAccountShippingAddress(String token, int addressId) throws Exception {
        try {
           int row = appShippingAddressDao.deleteAccountShippingAddressDAO(token, addressId);
           return row > 0;
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /**
     * =============================接口结束==========================
     **/
    @Override
    public int deleteByPrimaryKey(Integer addressId) {
        // TODO Auto-generated method stub
        return appShippingAddressDao.deleteByPrimaryKey(addressId);
    }

    @Override
    public int insert(AppShippingAddress record) {
        // TODO Auto-generated method stub
        return appShippingAddressDao.insert(record);
    }

    @Override
    public int insertSelective(AppShippingAddress record) {
        // TODO Auto-generated method stub
        return appShippingAddressDao.insertSelective(record);
    }

    @Override
    public AppShippingAddress selectByPrimaryKey(Integer addressId) {
        // TODO Auto-generated method stub
        return appShippingAddressDao.selectByPrimaryKey(addressId);
    }

    @Override
    public int updateByPrimaryKeySelective(AppShippingAddress record) {
        // TODO Auto-generated method stub
        return appShippingAddressDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppShippingAddress record) {
        // TODO Auto-generated method stub
        return appShippingAddressDao.updateByPrimaryKey(record);
    }
}
