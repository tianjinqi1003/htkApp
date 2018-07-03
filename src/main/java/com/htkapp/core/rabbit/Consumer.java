package com.htkapp.core.rabbit;

import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.modules.admin.common.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * Created by terabithia on 11/12/17.
 * 功能概要：消息接收
 */
public class Consumer implements MessageListener {

//    @Resource
//    private AmqpTemplate amqpTemplate;

    private Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Override
    public void onMessage(Message message) {
        logger.info("receive message:{}",message);
    }
}
