package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dto.ReturnHaveSomeList;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodCategoryMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCategory;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 */

@Service
public class BuffetFoodCategoryServiceImpl implements BuffetFoodCategoryService {

    @Resource
    private BuffetFoodCategoryMapper buffetFoodCategoryDao;

    /* =============接口开始================= */
    //根据店铺id获取店铺下的自助点餐菜品类别列表
    @Override
    public List<BuffetFoodCategory> getBuffetFoodCategoryListByShopId(int shopId) throws Exception {
        try {
            return buffetFoodCategoryDao.getBuffetFoodCategoryListByShopIdDAO(shopId);
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过分类id查找分类数据
    @Override
    public ReturnHaveSomeList getBuffetFoodCategoryByCategoryId(int categoryId) {
        return buffetFoodCategoryDao.getBuffetFoodCategoryByCategoryIdDAO(categoryId);
    }

    //分类名匹配搜索
    @Override
    public List<BuffetFoodCategory> matchSearchingByCategoryName(String key) {
        List<BuffetFoodCategory> resultList = buffetFoodCategoryDao.matchSearchingByCategoryNameDAO(key);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    /* =============接口结束================= */

    /* ======================JSP接口开始========================= */

    //通过商户id查找商铺下的外卖类别
    @Override
    public List<BuffetFoodCategory> getBuffetFoodCategoryListByAccountShopId(int accountShopId) throws Exception {
        try {
            List<BuffetFoodCategory> resultList = buffetFoodCategoryDao.getBuffetFoodCategoryListByAccountShopIdDAO(accountShopId);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过分类id修改分类名称
    @Override
    public void editCategoryNameById(BuffetFoodCategory category) throws Exception {
        try {
            int row = buffetFoodCategoryDao.editCategoryNameByIdDAO(category);
            if(row == 0){
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过分类id查询分类信息
    @Override
    public BuffetFoodCategory getCategoryById(int categoryId) {
        return buffetFoodCategoryDao.getCategoryByIdDAO(categoryId);
    }

    //新增分类
    @Override
    public void addCategoryById(String categoryName, int accountShopId,int mark) throws Exception {
        try {
            int row = buffetFoodCategoryDao.addCategoryByIdDAO(categoryName, accountShopId, mark);
            if(row == 0){
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过分类ID　删除分类
    @Override
    public void delCategoryById(int categoryId) throws Exception {
        try {
            int row = buffetFoodCategoryDao.delCategoryByIdDAO(categoryId);
            if(row == 0){
                throw new Exception("删除分类失败");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    /* =====================JSP接口结束======================= */
}
