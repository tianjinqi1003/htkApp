package com.htkapp.core.shiro.core.shiro.cache;

import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.shiro.common.utils.SerializeUtil;
import com.htkapp.core.shiro.common.utils.StringUtils;
import org.apache.shiro.session.Session;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class JedisManager {

    @Resource
    private JedisPool jedisPool;

    //获取jedis
    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (JedisConnectionException e) {
        	String message = StringUtils.trim(e.getMessage());
        	throw new JedisConnectionException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jedis;
    }

    public void returnResource(Jedis jedis) {
        if (jedis == null)
            return;
        jedis.close();
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public void deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            Long result = jedis.del(key);
            LoggerUtils.fmtDebug(getClass(), "删除Session结果：%s" , result);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime)
            throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0)
                jedis.expire(key, expireTime);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

	/**
	 * 获取所有Session
	 * @param dbIndex
	 * @param redisShiroSession
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Collection<Session> AllSession(int dbIndex, String redisShiroSession) throws Exception {
		Jedis jedis = null;
        Set<Session> sessions = new HashSet<Session>();
		try {
            jedis = getJedis();
            jedis.select(dbIndex);
            
            Set<byte[]> byteKeys = jedis.keys((JedisShiroSessionRepository.REDIS_SHIRO_ALL).getBytes());  
            if (byteKeys != null && byteKeys.size() > 0) {  
                for (byte[] bs : byteKeys) {  
                	Session obj = SerializeUtil.deserialize(jedis.get(bs),
                    		 Session.class);  
                     if(obj instanceof Session){
                    	 sessions.add(obj);  
                     }
                }  
            }  
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return sessions;
	}
}
