package com.htkapp.modules.merchant.common.dao;

import com.htkapp.modules.merchant.common.entity.AccountShopNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountShopNoticeMapper {

    /* ========================JSP页面接口开始============================ */
    //插入消息
    int insertNoticeByTokenDAO(AccountShopNotice notice);
    //改变消息状态
    int changeNoticeStatusByIdDA0(int id, int statusId);
    //条件查找消息列表(全部，已读，未读)
    List<AccountShopNotice> getNoticeListByTokenAndStatusDAO(@Param("token") String token, @Param("status") int status, @Param("orderByDesc") String orderByDesc);
    /* ========================JSP页面接口结束============================ */
}
