package com.设计模式.builder;

/**
 * @author kingwarluo
 * 广播消息构建
 * @date 2019/1/12 10:03
 */
public class BroadcastMessageBuilder extends AbstractBuilder {

    /**
     * 广播消息
     */
    private BroadcastMessage message;

    BroadcastMessageBuilder(){
        message = new BroadcastMessage();
    }

    BroadcastMessageBuilder(BroadcastMessage message){
        this.message = message;
    }

    /**
     * 创建新消息广播构建
     * @return
     */
    public static BroadcastMessageBuilder newBuilder(){
        return new BroadcastMessageBuilder();
    }

    /**
     * 创建新消息广播构建
     * @param message
     * @return
     */
    public static BroadcastMessageBuilder newBuilder(BroadcastMessage message){
        return new BroadcastMessageBuilder(message);
    }

    @Override
    protected BaseMessage getMessage() {
        return message;
    }

    public BroadcastMessageBuilder addCityId(Long cityId){
        message.getCityIds().add(cityId);
        return this;
    }

}
