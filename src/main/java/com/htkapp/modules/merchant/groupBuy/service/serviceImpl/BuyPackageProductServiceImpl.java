package com.htkapp.modules.merchant.groupBuy.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.groupBuy.dao.BuyPackageProductMapper;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageProduct;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuyPackageProductServiceImpl implements BuyPackageProductService {

    @Resource
    private BuyPackageProductMapper buyPackageProductDao;

    /* ===========================接口开始============================== */
    //查找加入团购的商品列表
    @Override
    public List<BuyPackageProduct> getBuyPackageProductListByShopId(int shopId, int pageNumber, int pageLimit) {
        PageHelper.startPage(pageNumber, pageLimit);
        List<BuyPackageProduct> buyPackageProductList = buyPackageProductDao.getBuyPackageProductListByShopIdDAO(shopId);
        if(buyPackageProductList != null && buyPackageProductList.size() > 0){
            return buyPackageProductList;
        }
        return null;
    }

    //插入添加的团购商品
    @Override
    public void insertBuyPackageProductByShopId(BuyPackageProduct buyPackageProduct) {
        int row = buyPackageProductDao.insertBuyPackageProductByShopIdDAO(buyPackageProduct);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    /* ===========================接口结束============================= */

    /* ===========================JSP页面接口开始============================= */
    //获取加入团购的商品列表
    @Override
    public List<BuyPackageProduct> getProductListById(int shopId) {
        List<BuyPackageProduct> resultList = buyPackageProductDao.getBuyPackageProductListByShopIdDAO(shopId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //删除添加到团购的商品
    @Override
    public void deleteAddProductById(int id) {
        int row = buyPackageProductDao.deleteAddProductDAO(id);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    /* ==========================JSP页面接口结束============================ */
}
