package com.proxy.fieldConverter.convertor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description:转换代理类
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
public class ConverterProxy implements MethodInterceptor {

    private final String SETTER_PREFIX = "set";

    /**
     * 被代理的对象
     */
    private Object target;

    public ConverterProxy(Object target) {
        this.target = target;
    }

    /**
     * @description:代理方法
     *
     * @author jianhua.luo
     * @date 2019/7/16
     * @param o             CGLIB动态生成的代理类实例
     * @param method        被代理的方法
     * @param objects       方法参数
     * @param methodProxy   为生成的代理类对方法的代理引用
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String methodName = method.getName();

        //设置只代理setter方法
        if(!methodName.startsWith(SETTER_PREFIX) || SETTER_PREFIX.equalsIgnoreCase(methodName)) {
            return method.invoke(target, objects);
        }

        // commons-lang3 : StringUtils.uncapitalize，首字母小写
        String fieldName = StringUtils.uncapitalize(StringUtils.substringAfter(methodName, SETTER_PREFIX));
        Method getter = target.getClass().getMethod("get" + StringUtils.capitalize(fieldName));

        FieldDescriber describer = getter.getAnnotation(FieldDescriber.class);
        describer = describer != null ? describer : method.getAnnotation(FieldDescriber.class);
        /**
         * 如果某個setter和getter方法没有注解FieldDescriber，那么默认为不代理
         */
        if(describer == null) {
            return method.invoke(target, objects);
        }

        /**
         * 获取注解的描述信息
         */
        String fieldDescribe = StringUtils.isNotBlank(describer.value()) ? describer.value() : fieldName;

        /**
         * 1、如果某方法为FieldValueConverter
         */
        FieldValueConverter converter = describer.converter().newInstance();
        Object originValue = converter.convert(getter.invoke(target));
        Object result = method.invoke(target, objects);
        Object newValue = converter.convert(getter.invoke(target));

        if(!Objects.equals(originValue, newValue)) {
            //确保builder已创建
            System.out.println("originValue:" + originValue);
            System.out.println("newValue:" + newValue);
        }
        return result;
    }

    public static Object getProxy(Object target) {
        ConverterProxy proxy = new ConverterProxy(target);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxy.target.getClass());
        enhancer.setCallback(proxy);
        return enhancer.create();
    }
}
