package com.设计模式.builder;

/**
 * 消息接口
 * @author kingwarluo
 * @date 2019/1/11 16:27
 */
public interface Message extends Rebuildable<Builder> {

    /**
     * 获取UUID
     * @return
     */
    String getUuid();

}
