package com.proxy;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kingwarluo
 * Enhancer测试类
 * @date 2019/1/22 17:03
 */
public class EnhancerProxy {

    static {
        /**
         * CallBack家族
         * Dispatcher
         * InvocationHandler
         * FixedValue
         * LazyLoader
         * MethodInterceptor
         * NoOp
         */
    }

    public static void main(String[] args) {
        // 使用FixedValue可以很容易的替换掉方法的返回值。
//        testFixedValue();
        // 使用InvocationHancler回调
//        testInvocationHandler();
        // 使用MethodInterceptor回调
        testMethodInterceptor();
    }

    public static void testMethodInterceptor(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                /**
                 * 唯一需要注意的就是proxy.invokeSuper和proxy.invoke的区别。
                 * invokeSuper是退出当前interceptor的处理，进入下一个callback处理，
                 * invoke则会继续回调该方法，如果传递给invoke的obj参数出错容易造成递归调用。
                 */
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class){
                    return "hello";
                }else{
                    return methodProxy.invokeSuper(o, objects);
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println("hello".equals(proxy.test(null)));
        System.out.println(enhancer.hashCode());
    }

    public static void testInvocationHandler(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if(method.getDeclaringClass() != Object.class
                        && method.getReturnType() == String.class){
                    return "hello";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println("hello".equals(proxy.test(null)));
//        System.out.println("hello".equals(proxy.hashCode()));
        System.out.println(enhancer.hashCode());
    }

    public static void testFixedValue(){
        /**
         * enhancer返回一个SampleClass的子类实例
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        /**
         * 使用FixedValue可以很容易的替换掉方法的返回值。
         */
        enhancer.setCallback(new FixedValue(){
            @Override
            public Object loadObject() throws Exception {
                return "hello";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println("hello".equals(proxy.test(null)));
        System.out.println(enhancer.hashCode());
    }

}
