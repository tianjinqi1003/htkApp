package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.merchant.shop.dao.ShopCommentMapper;
import com.htkapp.modules.merchant.shop.entity.ShopComment;
import com.htkapp.modules.merchant.shop.service.ShopCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yinqilei on 17-3-2.
 */
@Service
public class ShopCommentServiceImpl implements ShopCommentService {

    @Autowired
    private ShopCommentMapper shopCommentDao;


    /*=============================================商家=============================================*/
    @Override
    public List<ShopComment> getShopCommentByShopIdByPage(int pageNo, int pageLimit, Integer shopCommentId) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNo, pageLimit);
        return shopCommentDao.findShopCommentByShopIdByPage(shopCommentId);
    }

    @Override
    public int findTotalRows(Integer shopId) {
        // TODO Auto-generated method stub
        return shopCommentDao.findTotalRows(shopId);
    }

}
