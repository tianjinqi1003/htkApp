package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.AccountShopReplyCommentsMapper;
import com.htkapp.modules.merchant.shop.entity.AccountShopReplyComments;
import com.htkapp.modules.merchant.shop.service.AccountShopReplyCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountShopReplyCommentsServiceImpl implements AccountShopReplyCommentsService {

    @Autowired
    private AccountShopReplyCommentsMapper replyCommentsDao;

    /* ======================JSP页面接口开始========================= */
    //查找商户的未回复用户评论的数量
    @Override
    public int getNoCommentNumber(String accountShopToken) throws Exception {
        try {
            return replyCommentsDao.getNoCommentNumberDAO(accountShopToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }
    @Override
    public int getNoCommentCounts(List<Integer> shopIds) throws Exception {
        try {
            return replyCommentsDao.getNoCommentCounts(shopIds);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找商户未回复差评数量

    @Override
    public int getBadCommentNumber(String accountShopToken) throws Exception {
        try {
            return replyCommentsDao.getBadCommentNumberDAO(accountShopToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    @Override
    public int getBadCommentCounts(List<Integer> shopIds) throws Exception {
        try {
            return replyCommentsDao.getBadCommentCounts(shopIds);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据评论表id查找商户是否评论，评论内容
    @Override
    public AccountShopReplyComments getReplyContentById(int commentId) throws Exception {
        try {
            return replyCommentsDao.getReplyContentByIdDAO(commentId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //插入商户回复用户的评论
    @Override
    public void insertReply(AccountShopReplyComments replyComments) throws Exception {
        try {
            int row = replyCommentsDao.insertReplyDAO(replyComments);
            if(row <= 0){
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

/* ======================接口结束========================= */
}
