package com.htkapp.core.redisCache;

import com.htkapp.core.shiro.core.shiro.cache.JedisManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by terabithia on 11/12/17.
 * service redis缓存
 */

public class JedisServiceCache {

    //redis缓存前缀
    private static final String REDIS_SERVICE_CACHE = "htk-service-cache";
    //redis 分区
    private static int DB_INDEX = 2;
    //jedisManage 对象
    @Resource
    private BaseRedisCache redisCache;


    //存入
    public void put(Object key, Object value) {
        redisCache.set(DB_INDEX, buildCacheKey(key), value, -1);
    }

    //取出
    public <T> T get(String key, Class<T> requiredType) {
        return redisCache.get(DB_INDEX, buildCacheKey(key), requiredType);
    }


    //build key
    private String buildCacheKey(Object key) {
        return REDIS_SERVICE_CACHE + ":" + key;
    }
}
