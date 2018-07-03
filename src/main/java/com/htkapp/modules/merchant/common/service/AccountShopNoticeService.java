package com.htkapp.modules.merchant.common.service;

import com.htkapp.modules.merchant.common.dao.AccountShopNoticeMapper;
import com.htkapp.modules.merchant.common.entity.AccountShopNotice;

import java.util.List;

public interface AccountShopNoticeService {

    /* ========================JSP页面接口开始============================ */
    //插入消息
    void insertNoticeByToken(AccountShopNotice notice) throws Exception;
    //改变消息状态
    void changeNoticeStatusById(int id, int statusId) throws Exception;
    //条件查找消息列表(全部，已读，未读)
    List<AccountShopNotice> getNoticeListByTokenAndStatus(String token, int status, int pageNo, int pageLimit);
    /* ========================JSP页面接口结束============================ */
}
