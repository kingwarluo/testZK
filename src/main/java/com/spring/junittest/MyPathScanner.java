package com.spring.junittest;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class MyPathScanner extends ClassPathBeanDefinitionScanner {

    private String[] packages;

    private MyFactoryBean myFactoryBean = new MyFactoryBean();

    public MyPathScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if(beanDefinitionHolders.isEmpty()) {
            System.out.println("No bean was found in " + Arrays.toString(basePackages) + " package. Please check your configuration.");
        } else {
            this.processBeanDefinitions(beanDefinitionHolders);
        }
        return beanDefinitionHolders;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitionHolders) {
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            if (holder.getBeanName().endsWith("Test")) {
                return;
            }

            GenericBeanDefinition definition = (GenericBeanDefinition)holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            // 理解：bean的名字还是原来的名字，但是bean的实现类，替换成代理后的实现类，注入到spring中。
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            // beanClass替换成代理后生成的class，后续注入时，调用getObject方法，生成代理bean
            definition.setBeanClass(this.myFactoryBean.getClass());
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
    }

    public void registerFilters() {
        boolean acceptAllInterfaces = true;
        if (acceptAllInterfaces) {
            this.addIncludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }

        this.addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    /**
     * 判断是否合法的组件，类似过滤器
     *
     * @author jianhua.luo
     * @date 2021/2/5
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
