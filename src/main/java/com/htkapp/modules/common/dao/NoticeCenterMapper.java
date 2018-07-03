package com.htkapp.modules.common.dao;


import com.htkapp.modules.common.entity.NoticeCenter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeCenterMapper {

    /* ===============接口开始============== */
    //插入订单通知消息
    int insertOrderMessageToNoticeCenterDAO(NoticeCenter notice);
    //获取用户的通知消息列表
    List<NoticeCenter> getNoticeCenterListByTokenDAO(@Param("accountToken") String accountToken, @Param("orderByDesc") String orderByDesc);

    /* ===============接口结束==================== */

}
