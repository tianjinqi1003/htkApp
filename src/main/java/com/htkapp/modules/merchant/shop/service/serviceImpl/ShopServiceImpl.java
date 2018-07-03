package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopMapper;
import com.htkapp.modules.merchant.shop.dao.ShopMessageMapper;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ShopServiceImpl implements ShopServiceI {

    @Resource
    private ShopMapper shopDao;

    @Resource
    private ShopMessageMapper shopMessageDao;

    /* ===============接口开始===================== */


    /**
     * @desc 获取此分类集合下的所有shop
     * @param allCategoryIdSet
     * @author 马鹏昊
     */
    @Override
    public List<Shop> getShopListByCategoryList(Set<Integer> allCategoryIdSet) {
        List<Shop> result ;
        try{
            result = shopDao.getShopListByCategoryList(allCategoryIdSet);
        }catch (Exception e){
            //sql查询集合不能为空
            result = new ArrayList<>();
            Shop shop = new Shop();
            shop.setShopId(-1);
            result.add(shop);
        }
        return result;
    }

    /**
     * @desc 获取此大分类下的所有子分类
     * @author 马鹏昊
     */
    @Override
    public Set<Integer> getAllChildCategoryIdList(int categoryId) {
        Set<Integer> allChildCategoryIdList;
        try {
            allChildCategoryIdList = shopDao.getAllChildCategoryIdList(categoryId);
        } catch (Exception e) {
            Set<Integer> noValueDataSet = new HashSet<>();
            noValueDataSet.add(-1);
            return noValueDataSet ;
        }
        return allChildCategoryIdList;
    }

    @Override
    public int initShopMessage(int shopId) {
        int row = shopMessageDao.initShopMessage(shopId);
        return row;
    }

    //根据条件搜索商家
    @Override
    public List<Shop> getShopByCondition(String keyWord, int mark, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<Shop> shopList = shopDao.getShopByConditionDAO(keyWord, mark);
            if (shopList != null && shopList.size() > 0) {
                return shopList;
            } else {
                throw new NullDataException("查询不到店铺");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //获取根据传入的经纬度推荐商家
    @Override
    public List<Shop> getAllShopLatitudeAndLongitude(int pageNo, int pageLimit, int mark, String token) throws Exception {
        try {
            String orderDesc = "shop.shop_id asc";
            PageHelper.startPage(pageNo, pageLimit);
            List<Shop> resultList = shopDao.getAllShopLatitudeAndLongitudeDAO(mark, token, orderDesc ,new Date());
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            }
            return null;
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //推荐所有商家(用户未登陆推荐商家)
    @Override
    public List<Shop> notLoginRecommendedBusinesses(int mark, int pageNo, int pageLimit) {
        String orderDesc = "shop.shop_id asc";
        PageHelper.startPage(pageNo, pageLimit);
        List<Shop> shops = shopDao.getNotLoginRecommendedBusinessesDAO(mark, orderDesc,new Date());
        if (shops != null && shops.size() > 0) {
            return shops;
        }
        return null;
    }

    //根据店铺id查找店铺信息
    @Override
    public Shop getShopDataById(int shopId) throws Exception {
        try {
            Shop shopList = shopDao.getShopDataByIdDAO(shopId);
            if (shopList != null) {
                return shopList;
            } else {
                throw new NullDataException("根据店铺id查询数据为空");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据店铺id和店铺类型查找店铺信息
    @Override
    public Shop getShopShowInfoById(int shopId) throws Exception {
        try {
            Shop shop = shopDao.getShopShowInfoByIdDAO(shopId);
            if (shop != null) {
                return shop;
            } else {
                throw new NullDataException(Globals.CALL_NULL_DATA_ERROR);
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据传入的店铺类别id，返回店铺列表
    @Override
    public List<Shop> getShopListByCategoryId(int categoryId, int pageNo, int pageLimit, int mark) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<Shop> shopList = shopDao.getShopListByCategoryIdDAO(categoryId, mark);
            if (shopList != null && shopList.size() > 0) {
                return shopList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过传入的商户id查度店铺
    @Override
    public Shop getShopByAccountShopId(int accountShopId) throws Exception {
        try {
            List<Shop> shops = shopDao.getShopByAccountShopIdDAO(accountShopId);
            if (shops != null && shops.size() > 0) {
                return shops.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //改变店铺营业状态
    @Override
    public boolean changeShopStateById(int accountShopId, int shopStateId) throws Exception {
        try {
            int row = shopDao.changeShopStateByIdDAO(accountShopId, shopStateId);
            return row > 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据商户id查询商户下的店铺
    @Override
    public List<Shop> getShopListByAccountShopId(int accountShopId) throws Exception {
        try {
            List<Shop> shopList = shopDao.getShopListByAccountShopIdDAO(accountShopId);
            if (shopList != null && shopList.size() > 0) {
                return shopList;
            } else {
                throw new Exception(Globals.CALL_DATABASE_ERROR);
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商户id和mark标识查找商铺信息
    @Override
    public Shop getShopByAccountShopIdAndMark(int accountShopId, int mark) throws Exception {
        try {
            return shopDao.getShopByAccountShopIdAndMarkDAO(accountShopId, mark);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过一级分类id获取所有二级分类店铺
    @Override
    public List<Shop> getShopListByChildCategoryIdsAndFocus(int mark, Set<String> childSId, Set<Integer> shopIdList, String token, int tag, int pageNo, int pageLimit) {
        PageHelper.startPage(pageNo, pageLimit);
        List<Shop> shops = shopDao.getShopListByChildCategoryIdsAndFocusDAO(mark, childSId, shopIdList, token, tag,new Date());
        if (shops != null) {
            return shops;
        }
        return null;
    }

    //通过分类id查询所有已关注店铺
    @Override
    public List<Shop> getShopListByCategoryIdAndFocus(int mark, int categoryId, Set<Integer> shopIdList, String token, int tag, int pageNo, int pageLimit) {
        PageHelper.startPage(pageNo, pageLimit);
        List<Shop> shops = shopDao.getShopListByCategoryIdAndFocusDAO(mark, categoryId, shopIdList, token, tag,new Date());
        if (shops != null) {
            return shops;
        }
        return null;
    }

    //查找分类下的所有关注店铺数量(子分类是0:代表全部)
    @Override
    public int getFocusCategoryShopListCount(int mark, Set<String> childSId, Set<Integer> shopIdList, String token, int tag) {
        List<Shop> resultList = shopDao.getShopListByChildCategoryIdsAndFocusDAO(mark, childSId, shopIdList, token, tag, new Date());
        if (resultList != null && resultList.size() > 0) {
            return resultList.size();
        }
        return 0;
    }

    //查找二级分类下的所有关注店铺数量
    @Override
    public int getFocusChildCategoryShopListCount(int mark, int categoryId, Set<Integer> shopIdList, String token, int tag) {
        List<Shop> shops = shopDao.getShopListByCategoryIdAndFocusDAO(mark, categoryId, shopIdList, token, tag, new Date());
        if (shops != null && shops.size() > 0) {
            return shops.size();
        }
        return 0;
    }

    //获取店铺总数量
    @Override
    public int getAllShopCount(int mark) {
        return shopDao.getAllShopCountDAO(mark);
    }

    //根据index查询店铺
    @Override
    public Shop getShopByIndex(int index, int mark) {
        return shopDao.getShopByIndexDAO(index, mark);
    }

    //通过商户ID改变店铺状态
    @Override
    public void changeShopOpenStateById(int stateId, int accountShopId) throws Exception {
        try {
            int row = shopDao.changeShopStateByIdDAO(accountShopId, stateId);
            if (row <= 0) {
                throw new Exception("更改失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //更改店铺二维码图片url
    @Override
    public void updateShopQRCode(String qrImgUrl, int shopId) {
        int row = shopDao.updateShopQRCodeDAO(qrImgUrl, shopId);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    /* ===============接口结束===================== */


    /* ===============JSP页面接口开始======================= */
    //根据商户id查询店铺信息
    @Override
    public Shop getShopDataByAccountShopId(int accountShopId) throws Exception {
        try {
            return shopDao.getShopDataByIdDAO(accountShopId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据商户id查询店铺id
    @Override
    public Shop getShopIdByAccountShopId(int accountShopId, int mark) throws Exception {
        try {
            return shopDao.getShopIdByAccountShopId(accountShopId, mark);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商户token查找商铺信息
    @Override
    public Shop getShopDataByAccountShopToken(String accountShopToken) throws Exception {
        try {
            return shopDao.getShopDataByAccountShopTokenDAO(accountShopToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商户ID和mark获取商铺
    @Override
    public Shop getShopDataByAccountShopIdAndMark(int accountShopId, int mark) {
        return shopDao.getShopByAccountShopIdAndMarkDAO(accountShopId, mark);
    }

    //注册商铺
    @Override
    public int insertShopById(Shop shop) {
        try {
            System.out.println(shop.toString());
            int row = shopDao.insertShopByIdDAO(shop);
            if (row > 0) {
                //注册成功，把返回的自增id赋给用用户呢称
                return shop.getShopId();
            }
            return 0;
        } catch (Exception e) {
            throw new InsertException("添加商铺失败");
        }
    }

    //查找店铺信息
    @Override
    public Shop getShopMessageById(int accountShopId, int mark) {
        return shopDao.getShopMessageByIdDAO(accountShopId, mark);
    }

    //店铺头像上传修改
    @Override
    public void updateShopImg(Shop shop) {
        int row = shopDao.updateShopImgDAO(shop);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //营业时间修改
    @Override
    public void updateOpeningTime(Shop shop) {
        int row = shopDao.updateOpeningTimeDAO(shop);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //订餐电话修改
    @Override
    public void updatePhone(Shop shop) {
        int row = shopDao.updatePhoneDAO(shop);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //店铺公告修改
    @Override
    public void updateIntro(Shop shop) {
        int row = shopDao.updateIntroDAO(shop);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //店铺简介修改
    @Override
    public void updateDes(Shop shop) {
        int row = shopDao.updateDesDAO(shop);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //配送费
    @Override
    public void updateDeliveryFee(Shop shop) {
        int row = shopDao.updateDeliveryFeeDAO(shop);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    /* ================JSP页面接口结束======================= */
}
