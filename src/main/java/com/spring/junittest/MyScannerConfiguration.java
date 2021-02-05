package com.spring.junittest;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 扫描配置
 *
 * @author jianhua.luo
 * @date 2021/2/5
 */
@Data
public class MyScannerConfiguration implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, InitializingBean, BeanNameAware {

    private String url;

    private String remoteType;

    private String beanName;

    private String basePackage;

    private ApplicationContext context;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        MyPathScanner scanner = new MyPathScanner(registry);
        scanner.setResourceLoader(this.context);
        scanner.registerFilters();
        scanner.scan(new String[]{this.basePackage});
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
