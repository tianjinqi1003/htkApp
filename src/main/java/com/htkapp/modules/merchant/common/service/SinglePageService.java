package com.htkapp.modules.merchant.common.service;

import com.htkapp.modules.merchant.common.entity.SinglePage;

public interface SinglePageService {

    /* =================appHtml页面接口开始=================== */
    //获得单页数据
    SinglePage getSinglePageData(SinglePage singlePage) throws Exception;

    /* =================appHtml页面接口结束=================== */

    /**
     * @param singlePage 通过ID获得积分消费公告
     * @return
     */
    SinglePage getSpendingById(SinglePage singlePage);

    /**
     * @param singlePage 修改积分消费公告
     * @return
     */
    int updateSpendingById(SinglePage singlePage);

    /**
     * @param singlePage 插入积分消费公告
     * @return
     */
    int insertSpendingById(SinglePage singlePage);

    /*=============================查询单页数据(积分消费、打折消费)===============================*/

}
