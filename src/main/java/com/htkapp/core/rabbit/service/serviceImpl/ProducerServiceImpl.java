package com.htkapp.core.rabbit.service.serviceImpl;

import com.htkapp.core.rabbit.service.ProducerService;
import com.htkapp.core.shiro.common.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by terabithia on 11/12/17.
 * 功能概要：消息生产，提交到队列中去
 */

//@Service
public class ProducerServiceImpl implements ProducerService {

//    @Resource
//    private AmqpTemplate amqpTemplate;

    private Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);


    public void sendMessage(Object message){
        logger.info("to send message:{}",message);
//        amqpTemplate.convertAndSend("queueTestKey",message);
    }

}
