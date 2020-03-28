package com.spring.autoInject.beanDefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Proxy;

/**
 * 动态生产BaseMapper
 * 
 * @author jianhua.luo
 * @date 2020/3/28
 */
public class BaseMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean, ApplicationListener<ApplicationEvent>, ApplicationContextAware {

    /**
     * 要注入的接口类定义
     */
    private Class<T> mapperInterface;

    private ApplicationContext applicationContext;

    /**
     * 也应该走工厂方法注入得来
     */
    private BaseMapper baseMapper;

    @Override
    public T getObject() throws Exception {
        // 采用动态代理生成接口实现类，核心实现
        // 返回的是代理后的对象
        return (T) Proxy.newProxyInstance(applicationContext.getClassLoader(), new Class[]{mapperInterface}, new MapperJavaProxy(baseMapper, mapperInterface));
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //判断属性的注入是否正确-如mapperInterface判空
        if (null == mapperInterface)
            throw new IllegalArgumentException("Mapper Interface Can't Be Null!!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("BaseMapperFactoryBean applicationEvent:" + applicationEvent.toString());
    }
}
