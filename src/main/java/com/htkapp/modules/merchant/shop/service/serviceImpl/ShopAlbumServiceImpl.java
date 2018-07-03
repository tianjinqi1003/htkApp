package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopAlbumMapper;
import com.htkapp.modules.merchant.shop.entity.ShopAlbum;
import com.htkapp.modules.merchant.shop.service.ShopAlbumService;
import jdk.nashorn.internal.objects.Global;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by terabithia on 11/19/17.
 */

@Service
public class ShopAlbumServiceImpl implements ShopAlbumService {

    @Resource
    private ShopAlbumMapper shopAlbumDao;

    //获取店铺相册列表
    @Override
    public List<ShopAlbum> getShopAlbumListById(String accountShopToken) {
        List<ShopAlbum> resultList = shopAlbumDao.getShopAlbumListByIdDAO(accountShopToken);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //插入店铺相册图片
    @Override
    public void insertShopAlbumByShopId(ShopAlbum album) {
        int row = shopAlbumDao.insertShopAlbumByShopIdDAO(album);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //删除店铺相册接口
    @Override
    public void deleteAlbumById(String accountShopToken, int id) {
        int row = shopAlbumDao.deleteAlbumByIdDAO(accountShopToken, id);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    //通过shopId获取相册列表
    @Override
    public List<ShopAlbum> getShopAlbumListByShopId(int shopId) {
        List<ShopAlbum> resultList = shopAlbumDao.getShopAlbumListByShopIdDAO(shopId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
}
