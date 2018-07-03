package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodProductMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.awt.geom.AreaOp;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinqilei on 17-7-18.
 */

@Service
public class BuffetFoodProductServiceImpl implements BuffetFoodProductService {

    @Resource
    private BuffetFoodProductMapper buffetFoodProductDao;

    /* =============接口开始================= */
    //根据分类id获取菜品列表
    @Override
    public List<BuffetFoodProduct> getGoodsListByCategoryId(int categoryId, int pageNumber, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNumber, pageLimit);
            return buffetFoodProductDao.getGoodsListByCategoryIdDAO(categoryId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商品id获取商品详情
    @Override
    public BuffetFoodProduct getBuffetFoodProductDetailById(int productId) throws Exception {
        try {
            return buffetFoodProductDao.getBuffetFoodProductDetailByIdDAO(productId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //商品点赞，数量固定加1
    @Override
    public void likeProductAddOneById(int productId) throws Exception {
        try {
            int row = buffetFoodProductDao.likeProductAddOneByIdDAO(productId);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //产品名匹配搜索
    @Override
    public List<BuffetFoodProduct> matchSearchingByProductName(String key) {
        List<BuffetFoodProduct> resultList = buffetFoodProductDao.matchSearchingByProductNameDAO(key);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    /* =============接口结束================= */


    /* ======================JSP接口开始=========================== */
    //通过查出来的店铺类别id查找出店铺下的商品
    @Override
    public List<BuffetFoodProduct> getBuffetFoodProductById(int categoryId) throws Exception {
        try {
            List<BuffetFoodProduct> resultList = buffetFoodProductDao.getBuffetFoodProductByIdDAO(categoryId);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查询店铺下的外卖产品
    @Override
    public List<BuffetFoodProduct> getBuffetFoodProductListByAccountShopId(int accountShopId) throws Exception {
        try {
            return buffetFoodProductDao.getBuffetFoodProductListByAccountShopIdDAO(accountShopId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过类别id查找产品
    @Override
    public List<BuffetFoodProduct> getBuffetFoodProductByCategoryId(int categoryId, int accountShopId) throws Exception {
        try {
            List<BuffetFoodProduct> result = buffetFoodProductDao.getBuffetFoodProductByCategoryIdDAO(categoryId, accountShopId);
            if (result != null && result.size() > 0) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过产品id查找商品
    @Override
    public BuffetFoodProduct getBuffetFoodProductByPId(int accountShopId, int productId) {
        return buffetFoodProductDao.getBuffetFoodProductByPIdDAO(accountShopId, productId);
    }

    //添加自助点餐商品
    @Override
    public void addBuffetFoodProduct(BuffetFoodProduct product) throws Exception {
        int row = buffetFoodProductDao.addBuffetFoodProductDAO(product);
        if (row <= 0) {
            throw new Exception("添加失败");
        }
    }

    //通过商品id删除商品
    @Override
    public void delProductById(int productId) throws Exception {
        int row = buffetFoodProductDao.delProductByIdDAO(productId);
        if (row <= 0) {
            throw new Exception("删除商品失败");
        }
    }

    //修改商品
    @Override
    public void editProductById(BuffetFoodProduct product) throws Exception {
        int row = buffetFoodProductDao.editProductByIdDAO(product);
        if (row <= 0) {
            throw new Exception("修改失败");
        }
    }

    //通过分类ID删除商品
    @Override
    public void delProductByCId(int categoryId) throws Exception {
        try {
            int row = buffetFoodProductDao.delProductByCIdDAO(categoryId);
            if (row <= 0) {
//                throw new Exception("删除分类下的所有商品失败");
                //这里应该什么也不做，因为有一种情况是我添加了分类但是没有添加商品，这时的row是0很正常不应该抛出异常
            }
        } catch (Exception e) {
            //向上抛出，抛到service 调用层处理
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<BuffetFoodProduct> getNotInGroupBuyProductListData(int shopId) {
        List<BuffetFoodProduct> resultList = buffetFoodProductDao.getNotInGroupBuyProductListDataDAO(shopId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    @Override
    public List<BuffetFoodProduct> getAllProductByShopId(int shopId, String orderBy) {
        List<BuffetFoodProduct> resultList=buffetFoodProductDao.getAllProductByShopId(shopId, orderBy);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

	@Override
	public AjaxResponseModel buffetFoodOff(String selectedIds) {
		 try {
	            //转成int型id
	            String[] idStrs = selectedIds.split(",");
	            List<Integer> idInts = new ArrayList<>();
	            for (String idStr : idStrs) {
	                Integer i = Integer.parseInt(idStr);
	                idInts.add(i);
	            }
	            buffetFoodProductDao.buffetFoodOff(idInts);
	            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "下架失败");
	        } catch (Exception e) {
	            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "下架失败");
	        }
	}

	@Override
	public AjaxResponseModel buffetFoodOn(String selectedIds){
		 try {
	            //转成int型id
	            String[] idStrs = selectedIds.split(",");
	            List<Integer> idInts = new ArrayList<>();
	            for (String idStr : idStrs) {
	                Integer i = Integer.parseInt(idStr);
	                idInts.add(i);
	            }
	    		buffetFoodProductDao.buffetFoodOn(idInts);
	    		return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "上架成功");
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "上架失败");
	        }
	}

    /* ======================JSP接口结束========================== */
}
