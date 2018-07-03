package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.ShopAlbum;

import java.util.List;

/**
 * Created by terabithia on 11/19/17.
 */
public interface ShopAlbumService {

    //获取店铺相册列表
    List<ShopAlbum> getShopAlbumListById(String accountShopToken);
    //插入店铺相册图片
    void insertShopAlbumByShopId(ShopAlbum album);
    //删除店铺相册接口
    void deleteAlbumById(String accountShopToken, int id);
    //通过shopId获取相册列表
    List<ShopAlbum> getShopAlbumListByShopId(int shopId);
}
