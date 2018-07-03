package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodCollectMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCollect;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BuffetFoodCollectServiceImpl implements BuffetFoodCollectService {

    @Resource
    private BuffetFoodCollectMapper buffetFoodCollectDao;

    //添加收藏商品
    @Override
    public void insertCollect(BuffetFoodCollect collect) {
        int row = buffetFoodCollectDao.insertCollectDAO(collect);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //查找用户收藏商品集合
    @Override
    public int getCollectObjById(int productId, String token) {
        BuffetFoodCollect collect = buffetFoodCollectDao.getCollectObjByIdDAO(productId, token);
        if(collect != null){
            return 1;
        }
        return 0;
    }

    //删除收藏状态
    @Override
    public void deleteCollectById(int productId, String token) {
        int row = buffetFoodCollectDao.deleteCollectByIdDAO(productId, token);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }
}
