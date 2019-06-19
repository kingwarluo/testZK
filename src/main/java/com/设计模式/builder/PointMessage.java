package com.设计模式.builder;

import com.设计模式.builder.annotation.Validate;
import com.设计模式.builder.enums.TargetTypeEnum;

/**
 * @author kingwarluo
 * 单点消息类
 * @date 2019/1/12 10:50
 */
public class PointMessage extends BaseMessage {

    /** 用户ID */
    @Validate(nullable = true)
    private String userId;
    /** 用户类别 {@link TargetTypeEnum#getCode()}*/
    @Validate(nullable = true)
    private Integer userType;
    /** 设备ID */
    private String deviceId;

    @Override
    public Builder rebuild() {
        return PointMessageBuilder.newBuilder((PointMessage) super.clone());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
