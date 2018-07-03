package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.merchant.common.dao.AccountShopNoticeMapper;
import com.htkapp.modules.merchant.common.entity.AccountShopNotice;
import com.htkapp.modules.merchant.common.service.AccountShopNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountShopNoticeServiceImpl implements AccountShopNoticeService {

    @Resource
    private AccountShopNoticeMapper accountShopNoticeDao;

    /* ========================JSP页面接口开始============================ */

    //插入消息
    @Override
    public void insertNoticeByToken(AccountShopNotice notice) throws Exception {
        try {
            int row = accountShopNoticeDao.insertNoticeByTokenDAO(notice);
            if(row <= 0){
                throw new Exception("插入消息失败");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //改变消息状态
    @Override
    public void changeNoticeStatusById(int id, int statusId) throws Exception {
        try {
           int row = accountShopNoticeDao.changeNoticeStatusByIdDA0(id, statusId);
           if(row <= 0){
               throw new Exception("改变消息状态失败");
           }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //条件查找消息列表(全部，已读，未读)
    @Override
    public List<AccountShopNotice> getNoticeListByTokenAndStatus(String token, int status, int pageNo, int pageLimit) {
        String orderByDesc = "gmt_create desc";
        PageHelper.startPage(pageNo,pageLimit);
        List<AccountShopNotice> resultList = accountShopNoticeDao.getNoticeListByTokenAndStatusDAO(token, status, orderByDesc);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
    /* ========================JSP页面接口结束============================ */
}
