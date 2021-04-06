package com.metaq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收topic类型消息
 *
 * @author jianhua.luo
 * @date 2021/4/6
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_B)
public class MsgReceiverC_one {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @RabbitHandler
    public void process(String msgContent, Channel channel, Message message) {
        logger.info("处理器one接收处理队列B当中的消息： " + msgContent);
    }
}