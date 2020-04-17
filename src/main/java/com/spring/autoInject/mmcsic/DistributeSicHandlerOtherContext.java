package com.spring.autoInject.mmcsic;


import com.spring.autoInject.mmcsic.annotation.DistributeSicHandlerType;
import com.spring.autoInject.mmcsic.handler.AbstractDistributeSicHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jianhua.luo
 * @Description: 处理器上下文，根据类型获取相应的处理器
 *
 * 用AnnotationUtils.findAnnotation实现
 *
 * @Date: Created in 10:07 2019/11/29
 */
@Component
@SuppressWarnings("unchecked")
@Data
public class DistributeSicHandlerOtherContext {

    private Map<String, Object> handlerMap;

    @Autowired
    private List<AbstractDistributeSicHandler> distributeSicHandlerList;

    public AbstractDistributeSicHandler getInstance(String type) {
        Object object = handlerMap.get(type);
        if (object == null) {
            throw new IllegalArgumentException("not found handler for type: " + type);
        }

        return (AbstractDistributeSicHandler) object;
    }

    @PostConstruct
    public void init() {
        if(null != distributeSicHandlerList) {
            for (AbstractDistributeSicHandler distributeSicHandler : distributeSicHandlerList) {
                DistributeSicHandlerType storeClueStatisticsHandlerType = AnnotationUtils.findAnnotation(distributeSicHandler.getClass(), DistributeSicHandlerType.class);
                if(storeClueStatisticsHandlerType == null) {
                    throw new IllegalArgumentException(distributeSicHandler.getClass() + " not found annotation: " + DistributeSicHandlerType.class);
                }
                if (handlerMap == null) {
                    handlerMap = new HashMap<>(8);
                }
                handlerMap.put(storeClueStatisticsHandlerType.value(), distributeSicHandler);
            }
        }
    }

}
