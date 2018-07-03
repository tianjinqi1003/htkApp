package com.htkapp.modules.common.service;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.modules.common.entity.NoticeCenter;

import java.util.List;


public interface NoticeCenterService {

    /* ==============接口开始========================= */
    //插入订单通知消息
    void insertOrderMessageToNoticeCenterDAO(NoticeCenter notice) throws OrderException;
    //获取用户的通知消息列表
    List<NoticeCenter> getNoticeCenterListByToken(String token, int pageNo, int pageLimit, String orderByDesc) throws Exception;


    /* ===================接口结束============================ */
}
