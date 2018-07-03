package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.modules.merchant.shop.dao.RegisterApplyMapper;
import com.htkapp.modules.merchant.shop.entity.RegisterApply;
import com.htkapp.modules.merchant.shop.service.RegisterApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegisterApplyServiceImpl implements RegisterApplyService {

    @Resource
    private RegisterApplyMapper registerApplyDao;

    //插入注册申请列表
    @Override
    public void insertApplyById(RegisterApply apply) {
       int row = registerApplyDao.insertApplyByIdDAO(apply);
       if(row <= 0){
           throw new InsertException("插入数据失败");
       }
    }

    //获取注册申请列表
    @Override
    public List<RegisterApply> getRegisterApplyList(int stateId, int pageNumber, int pageLimit) {
        String orderDesc = "register_apply.gmt_create desc";
        PageHelper.startPage(pageNumber, pageLimit);
        List<RegisterApply> resultList = registerApplyDao.getRegisterApplyListDAO(stateId, orderDesc);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
}
