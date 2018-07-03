package com.htkapp.modules.common.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.dao.NoticeCenterMapper;
import com.htkapp.modules.common.entity.NoticeCenter;
import com.htkapp.modules.common.service.NoticeCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeCenterServiceImpl implements NoticeCenterService {

    @Autowired
    private NoticeCenterMapper noticeCenterDao;

    /* ======================接口开始===================== */
    //插入订单通知消息
    @Override
    @Transactional
    public void insertOrderMessageToNoticeCenterDAO(NoticeCenter notice) throws OrderException {
        try {
            noticeCenterDao.insertOrderMessageToNoticeCenterDAO(notice);
        } catch (OrderException e) {
            throw new OrderException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //获取用户的通知消息列表
    @Override
    public List<NoticeCenter> getNoticeCenterListByToken(String token, int pageNo, int pageLimit, String orderByDesc) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            return noticeCenterDao.getNoticeCenterListByTokenDAO(token,orderByDesc);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }
    /* =======================接口结束===================== */

}
