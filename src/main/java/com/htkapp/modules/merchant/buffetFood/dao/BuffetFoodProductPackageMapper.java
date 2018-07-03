package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProductPackage;

import java.util.List;

public interface BuffetFoodProductPackageMapper {

    //通过父id获取套餐内详情
    List<BuffetFoodProductPackage>  getPackageProductListByIdDAO(int id);
}
