package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodCommentMapper;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnCommentList;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodComment;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodCommentService;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderProductService;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderService;
import com.htkapp.modules.merchant.pay.dto.EnterPayReturn;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.xiaoleilu.hutool.date.DatePattern.NORM_DATETIME_PATTERN;
import static org.apache.commons.lang.time.DateFormatUtils.format;

@Service
public class BuffetFoodCommentServiceImpl implements BuffetFoodCommentService {

    @Resource
    private BuffetFoodCommentMapper buffetFoodCommentDao;

    /* ================================接口开始================================== */
    //根据产品id查询产品下的评论列表
    @Override
    public List<ReturnCommentList> getCommentListByProductId(int shopId, int productId, int pageNumber, int pageLimit) {
        PageHelper.startPage(pageNumber, pageLimit);
        List<ReturnCommentList> resultList = buffetFoodCommentDao.getCommentListByProductIdDAO(shopId, productId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    /* ================================接口结束================================= */
}
