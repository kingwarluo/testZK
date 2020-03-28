package com.spring.autoInject.beanDefinition.config;

import com.spring.autoInject.beanDefinition.DefaultClassRegistryBeanFactory;
import com.spring.autoInject.beanDefinition.DefaultBaseMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 声明配置类
 *
 * @author jianhua.luo
 * @date 2020/3/28
 */
@Component
public class ClassRegistryBeanScannerConfig {

    @Bean(name = "defaultBaseMapperFactoryBean")
    public DefaultBaseMapperFactoryBean configMapperManagerFactoryBean() {
        DefaultBaseMapperFactoryBean defaultBaseMapperFactoryBean = new DefaultBaseMapperFactoryBean();
        return defaultBaseMapperFactoryBean;
    }

    @Bean
    public DefaultClassRegistryBeanFactory configDefaultClassRegistryBeanFactory() {
        DefaultClassRegistryBeanFactory defaultClassRegistryBeanFactory = new DefaultClassRegistryBeanFactory();
        defaultClassRegistryBeanFactory.setScanPackage("com.spring.autoInject.beanDefinition.dao");
        defaultClassRegistryBeanFactory.setDefaultBaseMapperFactoryBean("defaultBaseMapperFactoryBean");
        return defaultClassRegistryBeanFactory;
    }

}
