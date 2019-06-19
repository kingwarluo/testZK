package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {

    // 这其实是业务实现类对象，用来调用具体方法
    private Object target;

    public Object bind(Object target) {
        // 接收业务实现类对象参数
        this.target = target;

        // 通过反射机制，创建一个代理类对象实例并返回，用户进行方法调用时使用
        // 创建代理对象时，
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 不要用proxy，容易造成死循环
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("预处理操作——————");
        result = method.invoke(target, args);
        System.out.println("调用后处理——————");
        return result;
    }

    public static void main(String[] args) {
        BookFacadeImpl impl = new BookFacadeImpl();
        JDKProxy proxy = new JDKProxy();
        BookFacade bf = (BookFacade) proxy.bind(impl);
        bf.addBook();
    }

}
