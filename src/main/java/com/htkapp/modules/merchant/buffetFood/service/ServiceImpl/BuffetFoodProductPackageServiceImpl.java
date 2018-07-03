package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodProductPackageMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProductPackage;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodProductPackageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuffetFoodProductPackageServiceImpl implements BuffetFoodProductPackageService {

    @Resource
    private BuffetFoodProductPackageMapper productPackageDao;

    //通过父id获取套餐内详情
    @Override
    public List<BuffetFoodProductPackage> getPackageProductListById(int id) {
        List<BuffetFoodProductPackage> resultList = productPackageDao.getPackageProductListByIdDAO(id);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
}
