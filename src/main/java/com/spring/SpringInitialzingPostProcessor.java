package com.spring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.lang.reflect.Method;

/**
 * @author kingwarluo
 * 测试加载顺序
 * @date 2019/1/23 16:08
 */
public class SpringInitialzingPostProcessor implements FactoryBean<Object>, InitializingBean, BeanDefinitionRegistryPostProcessor {

    private Object proxyObject;

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * 类加载器该用哪个？所有类都是由同一个类加载器加载，用本类的加载器就行
         */
//        proxyObject = Proxy.newProxyInstance(this.getClass().getClassLoader(),
//                new Class[]{IDemo.class}, new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        System.out.println("before");
//                        Object object = method.invoke(new DemoImpl(), args);
//                        System.out.println("after");
//                        return object;
//                    }
//                });

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DemoImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                Object obj = methodProxy.invokeSuper(o, args);
                System.out.println("after");
                return obj;
            }
        });
        proxyObject = enhancer.create();
        System.out.println("afterPropertiesSet");
    }

    public void sayHello(){
        System.out.println("hello");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("BeanDefinition");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcess");
    }

    @Override
    public Object getObject() throws Exception {
        return proxyObject;
    }

    @Override
    public Class<?> getObjectType() {
        return proxyObject == null ? Object.class : proxyObject.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
