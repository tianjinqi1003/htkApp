package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopAlbum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by terabithia on 11/19/17.
 */
public interface ShopAlbumMapper {

    //获取店铺相册列表
    List<ShopAlbum> getShopAlbumListByIdDAO(String accountShopToken);
    //插入店铺相册图片
    int insertShopAlbumByShopIdDAO(ShopAlbum album);
    //删除店铺相册接口
    int deleteAlbumByIdDAO(@Param("accountShopToken") String accountShopToken, @Param("id") int id);
    //通过shopId获取相册列表
    List<ShopAlbum> getShopAlbumListByShopIdDAO(int shopId);
}
