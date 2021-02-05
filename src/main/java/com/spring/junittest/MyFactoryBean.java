package com.spring.junittest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

/**
 * 需添加@Component后，才能实现注入
 * 但是这里只是定义，在扫描后注入
 *
 * @author jianhua.luo
 * @date 2021/2/5
 */
public class MyFactoryBean<T> implements FactoryBean, ApplicationContextAware {

    private ApplicationContext context;

    private Class<T> mapperInterface;

    private T ref;

    public MyFactoryBean() {
    }

    public MyFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return getProxy();
    }

    synchronized T getProxy() {
        if(this.ref == null) {
            MyScannerConfiguration bean = this.context.getBean(MyScannerConfiguration.class);
            this.ref = (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{this.mapperInterface}, new MyInvocationHandler(bean.getUrl(), bean.getRemoteType()));
        }
        return this.ref;
    }

    @Override
    public Class<T> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
