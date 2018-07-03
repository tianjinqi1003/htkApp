package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.ActivityMapper;
import com.htkapp.modules.merchant.integral.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.htkapp.modules.merchant.integral.service.*;
import java.util.Date;
import java.util.List;

/**
 * Created by yinqilei on 17-4-17.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityDao;


    /* =====================appHtml页面接口开始========================= */
    //查询出商家下的所有正在进行的活动 并分页
    @Override
    public List<Activity> getListByIdByDate(int pageNo, int pageLimit, int shopId, Date nowTime) throws Exception {
        try {
            PageHelper.startPage(pageNo,pageLimit);
            List<Activity> resultList = activityDao.findListByIdByDate(shopId, nowTime);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过活动id查找活动对象
    @Override
    public Activity getActivityById(int activeId) {
        return activityDao.getActivityByIdDAO(activeId);
    }

    /* =====================appHtml页面接口结束========================= */


//    /**
//     * @return 通过商铺ID查找集合数据并分页
//     */
//    @Override
//    public List<Activity> getListByPageById(int pageSize, int limit, int id) {
//        PageHelper.startPage(pageSize, limit);  //分页
//        return activityParentDao.findListById(id);
//    }
//
//    /**
//     * @param id     通过活动ID和商铺ID查找活动对象
//     * @param shopId
//     * @return
//     */
//    @Override
//    public Activity getById(int id, int shopId) {
//        return activityParentDao.findById(id, shopId);
//    }
//
//    /**
//     * @param activityParent 通过主键ID和商铺ID更改
//     * @return
//     */
////    @Override
////    public ResponseModel updateByIdS(Activity activityParent) {
////        int row = activityParentDao.updateById(activityParent);
////        if (row > 0) {
////            return new ResponseModel(3, "更改活动成功!");
////        } else {
////            return new ResponseModel(-1, "更改活动失败!");
////        }
////    }
//
//    /**
//     * @param activityParent 通过商铺ID插入数据
//     * @return
//     */
////    @Override
////    public ResponseModel saveByIdS(Activity activityParent) {
////        int row = activityParentDao.saveById(activityParent);
////        if (row > 0) {
////            return new ResponseModel(3, "新增活动成功!");
////        } else {
////            return new ResponseModel(-1, "新增活动失败!");
////        }
////    }
//
//    /**
//     * @param id     通过主键ID和商铺ID删除活动
//     * @param shopId
//     * @return
//     */
////    @Override
////    public ResponseModel deleteByIdS(int id, int shopId) {
////        int row = activityParentDao.deleteById(id, shopId);
////        if (row > 0) {
////            return new ResponseModel(3, "删除活动成功!");
////        } else {
////            return new ResponseModel(-1, "删除活动失败!");
////        }
////    }
//
//    /**
//     * @param shopId 通过商铺ID查询商铺下活动集合
//     * @return
//     */
//    @Override
//    public List<Activity> getListById(int shopId) {
//        return activityParentDao.findListById(shopId);
//    }

}
