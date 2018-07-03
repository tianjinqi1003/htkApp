package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.SmsBase;

/**
 * 短信
 */

public interface SmsBaseMapper {

    /*=================接口================*/


    int deleteByPrimaryKey(Integer id);

    int insert(SmsBase record);

    int insertSelective(SmsBase record);

    SmsBase selectByPrimaryKey(Integer id);
    
    SmsBase selectByTelphone(String telphone);

    int updateByPrimaryKeySelective(SmsBase record);

    int updateByPrimaryKey(SmsBase record);
}