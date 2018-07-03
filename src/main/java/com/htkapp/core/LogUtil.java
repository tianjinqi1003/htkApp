package com.htkapp.core;

import org.apache.log4j.Logger;

public class LogUtil {
    /**
     * Error 输出
     *
     * @param clazz   目标.Class
     * @param message 输出信息
     * @param e       异常类
     */
    public static void error(Class<? extends Object> clazz, String message, Exception e) {
        Logger logger = Logger.getLogger(clazz);
        if (null == e) {
            logger.error(message);
            return;
        }
        logger.error(message, e);
    }

    public static void error(Class<? extends Object> clazz, String message) {
        Logger logger = Logger.getLogger(clazz);
        logger.error(message);
    }


    public static void info(Class<? extends Object> clazz, String message){
        Logger logger = Logger.getLogger(clazz);
        logger.info(message);
    }
}
