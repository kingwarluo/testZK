package com.spring.autoInject.mmcsic;


import com.spring.autoInject.mmcsic.handler.AbstractDistributeSicHandler;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @Author: jianhua.luo
 * @Description: 处理器上下文，根据类型获取相应的处理器
 * @Date: Created in 10:07 2019/11/29
 */
@SuppressWarnings("unchecked")
@Data
public class DistributeSicHandlerContext {

    private Map<String, Class> handlerMap;

    private ApplicationContext applicationContext;

    DistributeSicHandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractDistributeSicHandler getInstance(String type) {
        Class clazz = handlerMap.get(type);
        if (clazz == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }

        return (AbstractDistributeSicHandler) applicationContext.getBean(clazz);
    }
}
