package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yinqilei on 17-4-17.
 */
public interface ActivityMapper {

    /* =================appHtml页面接口开始=================== */
    //通过活动id查找活动对象
    Activity getActivityByIdDAO(int activeId);

  /* =================appHtml页面接口结束=================== */

    /**
     * @return 通过商铺ID查找活动集合
     */
    List<Activity> findListById(int id);

    /**
     * @param id 通过ID和商铺ID查找对象
     * @return
     */
    Activity findById(@Param("id") int id, @Param("shopId") int shopId);

    /**
     * @param activity 通过主键ID和商铺ID更改
     * @return
     */
    int updateById(Activity activity);

    /**
     * @param activity 通过商铺ID插入数据
     * @return
     */
    int saveById(Activity activity);

    /**
     * @param id     通过主键ID和商铺ID删除活动
     * @param shopId
     * @return
     */
    int deleteById(@Param("id") int id, @Param("shopId") int shopId);

    /**
     * @param shopId  查询出商家下的所有正在进行的活动
     * @param nowTime
     * @return
     */
    List<Activity> findListByIdByDate(@Param("shopId") int shopId, @Param("nowTime") Date nowTime);

}
