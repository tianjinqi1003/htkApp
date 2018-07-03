package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopSuggestMapper;
import com.htkapp.modules.merchant.shop.entity.ShopSuggest;
import com.htkapp.modules.merchant.shop.service.ShopSuggestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShopSuggestServiceImpl implements ShopSuggestService {

    @Resource
    private ShopSuggestMapper shopSuggestDao;


    //插入用户建议内容
    @Override
    public void insertSuggestContentByShopId(ShopSuggest suggest) {
        int row = shopSuggestDao.insertSuggestContentByShopIdDAO(suggest);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
}
