package com.htkapp.core.customShiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;

public class SimpleWebSessionManager extends DefaultWebSessionManager implements WebSessionManager {

    private CacheManager cacheManager;

    public SimpleWebSessionManager(){
        super();
    }

    @Override
    public void validateSessions() {
        System.out.println("验证session");
        super.validateSessions();
    }

    @Override
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
