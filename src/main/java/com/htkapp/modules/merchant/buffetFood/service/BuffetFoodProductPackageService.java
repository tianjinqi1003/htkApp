package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProductPackage;

import java.util.List;

public interface BuffetFoodProductPackageService {

    //通过父id获取套餐内详情
    List<BuffetFoodProductPackage> getPackageProductListById(int id);
}
