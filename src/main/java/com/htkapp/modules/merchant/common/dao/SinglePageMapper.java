package com.htkapp.modules.merchant.common.dao;

import com.htkapp.modules.merchant.common.entity.SinglePage;

public interface SinglePageMapper {


    /* =================appHtml页面接口开始=================== */
    //通过商家ID获得积分消费单页对象
    SinglePage findSinglePageDataByType(SinglePage singlePage);


    /* =================appHtml页面接口结束=================== */

    /**
     * 通过ID获得积分消费公告
     *
     * @param singlePage
     * @return
     */
    SinglePage findSpendingById(SinglePage singlePage);

    /**
     * 修改积分消费公告
     *
     * @param singlePage
     * @return
     */
    int updateSpendingById(SinglePage singlePage);

    /**
     * 插入积分消费公告
     *
     * @param singlePage
     * @return
     */
    int insertSpendingById(SinglePage singlePage);
}
