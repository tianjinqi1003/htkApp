package com.htkapp.core.shiro.core.shiro.cache.impl;

import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.shiro.core.shiro.cache.JedisManager;
import com.htkapp.core.shiro.core.shiro.cache.JedisShiroCache;
import com.htkapp.core.shiro.core.shiro.cache.ShiroCacheManager;
import org.apache.shiro.cache.Cache;

public class JedisShiroCacheManager implements ShiroCacheManager {

    private JedisManager jedisManager;

    @Override
    public <K, V> Cache<K, V> getCache(String name) {
        return new JedisShiroCache<K, V>(name, getJedisManager());
    }
    /**
     * 关闭redis的链接
     */
    @Override
    public void destroy() {
    	//如果和其他系统，或者应用在一起就不能关闭
//    	getJedisManager().getJedis().shutdown();
        getJedisManager().getJedis().close();
        LoggerUtils.fmtDebug(getClass(),"redis连接关闭。。。。。");
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
