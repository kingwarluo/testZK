package com.metaq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 把大象装进冰箱的三步：
 *  配置类：创建交换机、创建队列、创建绑定关系
 *  消费者：监听类（监听队列）
 *  生产者：调用template发消息（Exchange）
 *
 * @author jianhua.luo
 * @date 2021/4/6
 */
@Configuration
public class RabbitConfig {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Value("${spring.rabbitmq.host}")
    private String host;
 
    @Value("${spring.rabbitmq.port}")
    private int port;
 
    @Value("${spring.rabbitmq.username}")
    private String username;
 
    @Value("${spring.rabbitmq.password}")
    private String password;
 
 
    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";
 
 
    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    // routing key 指根据rabiitmq设置的路由规则，来指定消息推送到哪个队列
    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "*.gupao.*";

    public static final String BINDINGKEY_A = "shanghai.gupao.china";
    public static final String BINDINGKEY_B = "changsha.gupao.india";
 
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }
 
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配（*代表一个匹配，#代表一个或多个匹配）
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_B);
    }

    /**
     * 获取队列B
     * @return
     */
    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, true); //队列持久
    }

    /**
     * 交换机和队列是多对多
     * 交换机和队列绑定规则在rabbitmq平台匹配规则
     *
     * @return
     */
    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queueB()).to(topicExchange()).with(RabbitConfig.ROUTINGKEY_B);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_C);
    }

    /**
     * 获取队列C
     * @return
     */
    @Bean
    public Queue queueC() {
        return new Queue(QUEUE_C, true); //队列持久
    }

    /**
     * 交换机和队列是多对多
     * 交换机和队列绑定规则在rabbitmq平台匹配规则
     *
     * @return
     */
    @Bean
    public Binding bindingC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

    /**
     * 另外一种消息处理机制的写法如下，在RabbitMQConfig类里面增加bean
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        //加载处理消息A的队列
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        //设置接收多个队列里面的消息，这里设置接收队列A
        //假如想一个消费者处理多个队列里面的信息可以如下设置：
        //container.setQueues(queueA(),queueB(),queueC());
        container.setQueues(queueA());
        container.setExposeListenerChannel(true);
        //设置最大的并发的消费者数量
        container.setMaxConcurrentConsumers(10);
        //最小的并发消费者的数量
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                /**通过basic.qos方法设置prefetch_count=1，这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message，
                 换句话说,在接收到该Consumer的ack前,它不会将新的Message分发给它 */
                channel.basicQos(1);
                byte[] body = message.getBody();
                logger.info("接收处理队列A当中的消息:" + new String(body));
                /**为了保证永远不会丢失消息，RabbitMQ支持消息应答机制。
                 当消费者接收到消息并完成任务后会往RabbitMQ服务器发送一条确认的命令，然后RabbitMQ才会将消息删除。*/
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return container;
    }

}