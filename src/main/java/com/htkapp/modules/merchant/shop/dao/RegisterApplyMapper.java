package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.RegisterApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegisterApplyMapper {

    //插入注册申请列表
    int insertApplyByIdDAO(RegisterApply apply);
    //获取注册申请列表
    List<RegisterApply> getRegisterApplyListDAO(@Param("stateId") int stateId, @Param("orderDesc") String orderDesc);
    //根据商户id删除
    int deleteByIdDAO(int id);
}
