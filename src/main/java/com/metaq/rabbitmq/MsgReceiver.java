package com.metaq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 接收direct类型消息
 *
 * @author jianhua.luo
 * @date 2021/4/6
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @RabbitHandler
    public void process(@Payload String content) {
        logger.info("接收处理队列A当中的消息： " + content);
    }
 
}