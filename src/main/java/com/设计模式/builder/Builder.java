package com.设计模式.builder;

import com.设计模式.builder.exception.MessageException;

/**
 * 构建者
 * @author kingwarluo
 * @date 2019/1/11 16:27
 */
public interface Builder {

    /**
     * 构建消息
     * @return
     */
    Message build() throws MessageException;

}
