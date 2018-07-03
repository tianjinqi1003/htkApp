package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.ActivityGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */

public interface ActivityGoodsMapper {


    /* =================appHtml页面接口开始=================== */

    /* =================appHtml页面接口结束=================== */

    /**
     * @return 查找商铺下的所有积分兑换实物活动
     */
    List<ActivityGoods> findPresentListByPageById(ActivityGoods activityGoods);

    /**
     * @param id 通过ID删除积分兑换活动
     * @return
     */
    int deletePresentById(Integer id);

    /**
     * @param id 通过ID查找出活动信息
     * @return
     */
    ActivityGoods editPresentById(Integer id);

    /**
     * @param activityGoods 插入新数据
     * @return
     */
    int insertPresentById(ActivityGoods activityGoods);

    /**
     * @param activityGoods 通过ID修改数据
     * @return
     */
    int updatePresentById(ActivityGoods activityGoods);

    /**
     * @param id  查询要选择的活动是否有六个实物
     * @return
     */
    int selectActivityCount(int id);

    /*=============================================appPage=============================================*/

    /**
     * @param shopId 通过ID查找店铺的所有活动
     * @return
     */
    List<ActivityGoods> findActivityListById(@Param("shopId") Integer shopId, @Param("parentId") Integer parentId);

    /**
     * @param id 通过用户ID和商铺ID修改活动信息(实物数量更改)
     * @return
     */
    int updateActivityCountById(@Param("id") Integer id);

    /**
     * @param id 通过活动ID查找活动名称
     * @return
     */
    String findGoodsNameById(Integer id);
}
