package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.RegisterApply;

import java.util.List;

public interface RegisterApplyService {

    //插入注册申请列表
    void insertApplyById(RegisterApply apply);
    //获取注册申请列表
    List<RegisterApply> getRegisterApplyList(int stateId, int pageNumber, int pageLimit);
}
