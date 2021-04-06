package com.metaq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setReturnCallback(this);
    }
 
    public void sendMsg(String content) {
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content);
        // 主题交换机
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_B, RabbitConfig.BINDINGKEY_A, content);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_B, RabbitConfig.BINDINGKEY_B, content);
        // 广播交换机
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_C, "", content);
    }
    /**
     * 消息投递确认
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(" 回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }
    }

    /**
     * 当消息无法路由到queue时，触发的方法
     * mandatory为true时触发
     * @param message
     * @param replyCode
     * @param replyText
     * @param routingKey
     * @param exchange
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String routingKey, String exchange) {
        logger.info("消息无法投递到队列");
    }
}