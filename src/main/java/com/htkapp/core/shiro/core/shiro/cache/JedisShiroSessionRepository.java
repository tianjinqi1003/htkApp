package com.htkapp.core.shiro.core.shiro.cache;

import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.shiro.common.utils.SerializeUtil;
import com.htkapp.core.shiro.core.shiro.session.CustomSessionManager;
import com.htkapp.core.shiro.core.shiro.session.SessionStatus;
import com.htkapp.core.shiro.core.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;


public class JedisShiroSessionRepository implements ShiroSessionRepository {

    public static final String REDIS_SHIRO_SESSION = "htk-shiro-session:";
    //这里有个小BUG，因为Redis使用序列化后，Key反序列化回来发现前面有一段乱码，解决的办法是存储缓存不序列化
    public static final String REDIS_SHIRO_ALL = "*htk-shiro-session:*";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private static final int DB_INDEX = 1;

    private JedisManager jedisManager;


    /**
     * 执行顺序：获取、保存、更新
     * @param session
     */

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new NullPointerException("session is empty");
        try {
            byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
            //不存在才添加。
            if (null == session.getAttribute(CustomSessionManager.SESSION_STATUS)) {
                //Session 踢出自存存储。
                SessionStatus sessionStatus = new SessionStatus();
                session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
            }
//            session.setTimeout(3600000L);
            byte[] value = SerializeUtil.serialize(session);
            long sessionTimeOut = session.getTimeout() / 1000;

//            Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
//            getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime.intValue());

            /**
             * @author 马鹏昊
             * @desc 设置过期时间小于0，不对Jedis设置expire就不会删除sessionId
             */
            int expireTime = -1;
            getJedisManager().saveValueByKey(DB_INDEX, key, value, expireTime);


            LoggerUtils.fmtDebug(getClass(), "保存session操作");
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "save session error，id:[%s]", session.getId());
        }
    }

    @Override
    public void deleteSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("session id is empty");
        }
        try {
            getJedisManager().deleteByKey(DB_INDEX,
                    SerializeUtil.serialize(buildRedisSessionKey(id)));
            LoggerUtils.fmtDebug(getClass(),"超时删除session,删除成功");
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "删除session出现异常，id:[%s]", id);
        }
    }


    @Override
    public Session getSession(Serializable id) {
        if (id == null)
            throw new NullPointerException("session id is empty");
        Session session = null;
        try {
            byte[] value = getJedisManager().getValueByKey(DB_INDEX, SerializeUtil
                    .serialize(buildRedisSessionKey(id)));
            session = SerializeUtil.deserialize(value, Session.class);
            LoggerUtils.fmtDebug(getClass(), "获取session, 成功");
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取session异常，id:[%s]", id);
        }
        return session;
    }



    @Override
    public Collection<Session> getAllSessions() {
        Collection<Session> sessions = null;
        try {
            sessions = getJedisManager().AllSession(DB_INDEX, REDIS_SHIRO_SESSION);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "获取全部session异常");
        }

        return sessions;
    }

    private String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }

    public JedisManager getJedisManager() {
        return jedisManager;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
