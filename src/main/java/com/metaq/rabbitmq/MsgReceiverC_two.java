package com.metaq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收fanout类型消息
 *
 * @author jianhua.luo
 * @date 2021/4/6
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_C)
public class MsgReceiverC_two {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("处理器two接收处理队列C当中的消息： " + content);
    }

}