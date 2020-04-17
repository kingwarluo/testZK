package com.spring.autoInject.mmcsic;

import com.spring.autoInject.mmcsic.annotation.DistributeSicHandlerType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: jianhua.luo
 * @Description: 初始化留咨自动分配策略处理类的上下文
 * @Date: 2019/11/28
 */
@Component
public class DistributeSicHandlerProcessor implements ApplicationContextAware, BeanFactoryPostProcessor {

    private static final String HANDLER_PACKAGE = "com.spring.autoInject.mmcsic.handler";

    private ApplicationContext applicationContext;

    /**
     * 扫描@DistributeSicHandlerType，DistributeSicHandlerContext，将其注册到spring容器
     *
     * @param beanFactory bean工厂
     * @see DistributeSicHandlerType
     * @see DistributeSicHandlerContext
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Class> handlerMap = new HashMap<>(8);

        Set<Class<?>> classes = ClassScaner.scan(HANDLER_PACKAGE, DistributeSicHandlerType.class);
        for (Class<?> handlerClass : classes) {
            handlerMap.put(handlerClass.getAnnotation(DistributeSicHandlerType.class).value(), handlerClass);
        }

        //将分单上下文动态注入spring中，通过BeanFactoryPostProcessor实现
        DistributeSicHandlerContext context = new DistributeSicHandlerContext(handlerMap);
        context.setApplicationContext(applicationContext);
        beanFactory.registerSingleton(DistributeSicHandlerContext.class.getName(), context);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
