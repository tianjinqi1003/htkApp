package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.AppAccountEventLog;

/**
 * Created by yinqilei on 17-6-23.
 *
 */

public interface AppAccountEventLogMapper {

    //添加app用户操作事件日志
    void addEventLogDAO(AppAccountEventLog eventLog);
}
