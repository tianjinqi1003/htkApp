package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htkapp.modules.merchant.common.dao.SinglePageMapper;
import com.htkapp.modules.merchant.common.entity.SinglePage;
import com.htkapp.modules.merchant.common.service.SinglePageService;

@Service
public class SingleServiceImpl implements SinglePageService {

    @Autowired
    private SinglePageMapper singlePageDao;



    /* =================appHtml页面接口开始=================== */
    //获得单页数据
    @Override
    public SinglePage getSinglePageData(SinglePage singlePage) throws Exception {
        try {
            return  singlePageDao.findSinglePageDataByType(singlePage);
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }


    /* =================appHtml页面接口结束=================== */

    /**
     * 通过ID获得积分消费公告
     */
    @Override
    public SinglePage getSpendingById(SinglePage singlePage) {
        // TODO Auto-generated method stub
        return singlePageDao.findSpendingById(singlePage);
    }

    /**
     * 修改积分消费公告
     */
    @Override
    public int updateSpendingById(SinglePage singlePage) {
        // TODO Auto-generated method stub
        int row = singlePageDao.updateSpendingById(singlePage);
        return row == 0 ? 0 : 1;
    }

    /**
     * 插入积分消费公告
     */
    @Override
    public int insertSpendingById(SinglePage singlePage) {
        int row = singlePageDao.insertSpendingById(singlePage);
        return row == 0 ? 0 : 1;
    }

    /*=============================查询单页数据(积分消费、打折消费)===============================*/
}
