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