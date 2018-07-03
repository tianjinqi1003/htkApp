package com.htkapp.core.shiro.core.shiro.listenter;


import com.htkapp.core.OtherUtils;
import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.shiro.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import javax.annotation.Resource;
import java.util.Date;

import static com.xiaoleilu.hutool.date.DatePattern.NORM_DATETIME_MINUTE_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

public class CustomSessionListener implements SessionListener {

    private ShiroSessionRepository shiroSessionRepository;

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        //TODO
        try {
            StackTraceElement[] temp = Thread.currentThread().getStackTrace();
            StackTraceElement a = (StackTraceElement) temp[2];
            LoggerUtils.fmtDebug(getClass(), "---On start from----" + a.getMethodName());
            String className = a.getClassName();
            LoggerUtils.fmtDebug(getClass(), "---On start from---" + className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("on start");
    }
    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        //TODO
        System.out.println("on stop");
    }

    @Override
    public void onExpiration(Session session) {
        //当session超时触发的delete redis内session 缓存操作
        LoggerUtils.fmtDebug(getClass(), "session expiration , current time is :"+ format(new Date(), NORM_DATETIME_MINUTE_PATTERN));
        shiroSessionRepository.deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }



}

