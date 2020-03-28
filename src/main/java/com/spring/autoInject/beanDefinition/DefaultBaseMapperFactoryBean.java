package com.spring.autoInject.beanDefinition;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 定义默认的BaseMapper的FactoryBean
 *
 * @author jianhua.luo
 * @date 2020/3/28
 */
public class DefaultBaseMapperFactoryBean implements FactoryBean<BaseMapper>, InitializingBean, ApplicationListener<ApplicationEvent> {
    @Override
    public BaseMapper getObject() throws Exception {
        return new CustomBaseMapper();
    }

    @Override
    public Class<?> getObjectType() {
        return BaseMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //判断属性的注入是否正确
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("MapperManagerFactoryBean applicationEvent:" + applicationEvent.toString());
    }
}
