#mq发送的四个环节

## 1.确保消息发送到MQ服务器
### 事务机制（非常耗时，不采用）
### confirm机制

## 2.确保消息路由到正确队列
### mandatory = true + returnListener
### 备份交换机

## 3.确保消息在队列正常存储
### 队列持久化（durable=true）
### 交换机持久化（durable=true）
### 消息持久化（deliveryMode=2）

## 4.确保消息正确投递到消费者
### ACK机制，默认自动ack，可以设置手动模式（Acknowlage=Manual）；缺陷：如果消费者更一直未返回ack，消息不会从queue删除，queue后续的消息都不会发送（慎用）

## 5.其他
### 消费者回调
    1.消费者给生产者发送确认消息
    2.消费者调用生产者api
### 补偿机制（如果消费者很久没有通知生产者）
    1.重发消息（ATM存款：发送确认；ATM存款：发送冲正）
    2.控制次数（消息落库）
### 消息幂等性
    1.消费端做消息幂等性处理（msgId,BizId）
    2.重帐控制（落库）
    
### 一些疑问
    1、rabbitmq发送确认机制
        见5
    2、rabbitmq交换机  路由key  队列绑定机制  
        生产者指定 交换机、路由
        消费者指定 队列、交换机、绑定方式
        由消费队列机绑定
    3、多次绑定spring中如何存储  
        beanName为方法名
    4、如何查看spring中所有bean  
        actuator监控查看
    5、confirmCallBack & returnCallBack区别
        confirmCallBack消息到达交换机
        returnCallBack消息到达队列