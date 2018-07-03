package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.common.dao.AdvertisingInformationMapper;
import com.htkapp.modules.merchant.common.entity.AdvertisingInformation;
import com.htkapp.modules.merchant.common.service.AdvertisingInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 */

@Service
public class AdvertisingInformationServiceImpl implements AdvertisingInformationService {

    @Resource
    private AdvertisingInformationMapper advertisingInformationDao;

    /* =================接口开始======================== */
    //根据团购商铺id获取广告列表
    @Override
    public List<AdvertisingInformation> getGroupBuyAdList(Integer shopId) throws Exception {

        try {
            List<AdvertisingInformation> resultList = advertisingInformationDao.getGroupBuyAdListDAO(shopId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                throw new NullDataException("查询数据为空");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //获取首页广告列表(随机五个店铺广告信息)
    @Override
    public List<AdvertisingInformation> getRandomlyAdList() throws Exception {
        try {
            List<AdvertisingInformation> randList = advertisingInformationDao.getRandomlyAdListDAO();
            if (randList != null && randList.size() > 0) {
                return randList;
            } else {
                throw new NullDataException("查询数据为空");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /* =================接口结束======================== */
}
