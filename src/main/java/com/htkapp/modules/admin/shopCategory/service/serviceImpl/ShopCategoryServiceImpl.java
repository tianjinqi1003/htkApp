package com.htkapp.modules.admin.shopCategory.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.admin.shopCategory.dao.ShopCategoryMapper;
import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.admin.shopCategory.service.ShopCategoryService;
import com.htkapp.modules.common.service.AccountShopRoleService;
import com.htkapp.modules.merchant.shop.entity.ShopCategoryData;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinqilei on 17-6-26.
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Resource
    private ShopCategoryMapper shopCategoryDao;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private AccountShopRoleService accountShopRoleService;

    /* =================接口开始==================== */

    /**
     * 获取所有的商铺分类
     * @return
     */
    @Override
    public List<ShopCategoryData> getShopCategoryList() {
        try {
            return shopCategoryDao.getShopCategoryList();
        }catch (Exception e){
            return null;
        }
    }

    //获取所有商铺类别
    @Override
    public List<ShopCategory> getAllCategory(int pageNo, int pageLimit, int mark) throws Exception {
        List<ShopCategory> resultList = null;
        try {
            PageHelper.startPage(pageNo, pageLimit);
            resultList = shopCategoryDao.getShopCategoryDAO(mark);
            PageInfo<ShopCategory> info = new PageInfo<ShopCategory>(resultList);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                throw new NullDataException("查询出数据为空");
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据分类id获取分类信息
    @Override
    public ShopCategory getCategoryById(int categoryId) {
        return shopCategoryDao.getCategoryByIdDAO(categoryId);
    }

    //查询二级分类信息
    @Override
    public List<ShopCategory> getCategoryListById(int categoryId) {
        List<ShopCategory> resultList = shopCategoryDao.getCategoryListByIdDAO(categoryId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //根据分类ID和标记查询子分类列表
    @Override
    public List<ShopCategory> getCategoryListByIdAndMark(int categoryId, int mark) {
        List<ShopCategory> resultList = shopCategoryDao.getCategoryListByIdAndMarkDAO(categoryId, mark);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    /* =================接口结束==================== */


    /* ====================JSP接口开始===================== */
    //根据类别id查找商铺类别信息
    @Override
    public ShopCategory getShopCategoryDataById(int categoryId) throws Exception {
        try {
            return shopCategoryDao.getShopCategoryDataByIdDAO(categoryId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过标识查询分类列表
    @Override
    public List<ShopCategory> getCategoryListData(int mark) {
        List<ShopCategory> resultList = shopCategoryDao.getCategoryListDataDAO(mark);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        } else {
            return null;
        }
    }

    //通过id查找子分类列表
    @Override
    public List<ShopCategory> getChildCategoryById(int mark, int parentId) {
        List<ShopCategory> resultList = shopCategoryDao.getChildCategoryByIdDAO(mark, parentId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //添加分类下的子分类
    @Override
    public void addChildCategoryById(ShopCategory shopCategory) throws Exception {
        int row = shopCategoryDao.addChildCategoryByIdDAO(shopCategory);
        if(row <=0 ){
            throw new Exception("添加子分类失败");
        }
    }

    //添加一级分类
    @Override
    public void addCategoryByMark(ShopCategory shopCategory) throws Exception {
        int row = shopCategoryDao.addCategoryByMarkDAO(shopCategory);
        if(row <= 0){
            throw new Exception("添加分类失败");
        }
    }

    //保存修改的分类信息
    @Override
    public void saveCategoryById(ShopCategory category) throws Exception {
        int row = shopCategoryDao.saveCategoryByIdDAO(category);
        if(row <= 0){
            throw new Exception("修改分类失败");
        }
    }

    //通过分类id删除分类
    @Override
    public void delCategoryId(int categoryId) throws Exception {
        int row = shopCategoryDao.delCategoryIdDAO(categoryId);
        if(row <= 0){
            throw new Exception("删除二级分类失败");
        }
    }

    /* ====================JSP接口结束===================== */

}
