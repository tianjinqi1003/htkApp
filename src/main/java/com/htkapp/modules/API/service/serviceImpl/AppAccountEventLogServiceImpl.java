package com.htkapp.modules.API.service.serviceImpl;

import com.htkapp.modules.API.dao.AppAccountEventLogMapper;
import com.htkapp.modules.API.entity.AppAccountEventLog;
import com.htkapp.modules.API.service.AppAccountEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinqilei on 17-6-23.
 *
 */
@Service
public class AppAccountEventLogServiceImpl implements AppAccountEventLogService {

    @Autowired
    private AppAccountEventLogMapper eventLogDao;

    /*========================接口开始========================*/
    //插入app用户操作日志数据
    @Override
    public void insertAppEventLog(AppAccountEventLog eventLog) {
        if (eventLog != null) {
            eventLogDao.addEventLogDAO(eventLog);
        }
    }
    /*========================接口结束========================*/
}
