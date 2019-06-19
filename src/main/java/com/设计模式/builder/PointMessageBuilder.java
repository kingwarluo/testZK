package com.设计模式.builder;

import com.设计模式.builder.constant.MessageCodeConstant;
import com.设计模式.builder.exception.MessageException;

/**
 * @author kingwarluo
 * 单点消息构造
 * @date 2019/1/12 10:50
 */
public class PointMessageBuilder extends AbstractBuilder {

    /**
     * 单点消息
     */
    private PointMessage message;

    /**
     * 无参构造函数
     */
    PointMessageBuilder(){
        message = new PointMessage();
    }

    /**
     * 指定message的构造函数
     * @param message
     */
    PointMessageBuilder(PointMessage message){
        this.message = message;
    }

    public static PointMessageBuilder newBuilder(){
        return new PointMessageBuilder();
    }

    public static PointMessageBuilder newBuilder(PointMessage message){
        return new PointMessageBuilder(message);
    }

    @Override
    public void check() throws MessageException {
        super.check();
        if(message.isPush() && message.getDeviceId() == null){
            throw new MessageException(MessageCodeConstant.FAILURE, "push为true时，设备ID不能为空");
        }
    }

    /**
     * 为单点消息设置userId
     * @param userId
     * @return
     */
    public PointMessageBuilder setUserId(String userId){
        message.setUserId(userId);
        return this;
    }

    /**
     * 为单点消息设置deviceId
     * @param deviceId
     * @return
     */
    public PointMessageBuilder setDeviceId(String deviceId){
        message.setDeviceId(deviceId);
        return this;
    }

    /**
     * 为单点消息设置userType
     * @param userType
     * @return
     */
    public PointMessageBuilder setUserType(Integer userType){
        message.setUserType(userType);
        return this;
    }

    @Override
    protected BaseMessage getMessage() {
        return message;
    }
}
