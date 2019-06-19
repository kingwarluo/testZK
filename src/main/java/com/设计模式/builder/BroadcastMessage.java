package com.设计模式.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kingwarluo
 * 广播消息类
 * @date 2019/1/12 10:06
 */
public class BroadcastMessage extends BaseMessage {

    private List<Long> cityIds;

    BroadcastMessage(){
        this.cityIds = new ArrayList<Long>();
    }

    BroadcastMessage(List<Long> cityIds){
        this.cityIds = cityIds;
    }

    @Override
    public Builder rebuild() {
        return BroadcastMessageBuilder.newBuilder((BroadcastMessage) this.clone());
    }

    @Override
    public BroadcastMessage clone(){
        /**
         * 对广播消息对象深拷贝
         */
        BroadcastMessage broadcastMessage = (BroadcastMessage) super.clone();
        broadcastMessage.cityIds = (List<Long>) ((ArrayList<Long>) cityIds).clone();
        return broadcastMessage;
    }

    public List<Long> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<Long> cityIds) {
        this.cityIds = cityIds;
    }

    @Override
    public String toString() {
        return "BroadcastMessage{" +
                "cityIds=" + cityIds +
                '}';
    }
}
