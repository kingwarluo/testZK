package com.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * CGlib方式适用于无需频繁创建的代理
 * 如果类需要频繁创建，用JDK的方式要合适一点
 */
public class CglibProxy implements MethodInterceptor {

    // 业务实现类，供代理方法中真正的业务方法调用
    private Object target;

    public Object getInstance(Object target){
        // 赋给业务对象
        this.target = target;
        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        // 后期加上去的
//        enhancer.setClassLoader(target.getClass().getClassLoader());
        return enhancer.create();
    }

    /**
     * 不要手动调用o，容易造成死循环
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("预处理——————");
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("调用后操作——————");
        return obj;
    }

    public static void main(String[] args) {
//        BookFacadeImpl impl = new BookFacadeImpl();
        CglibProxy cglib = new CglibProxy();
//        BookFacade bf = (BookFacade) cglib.getInstance(impl);
//        bf.addBook();

        Book book1 = new Book();
        book1.addA();
        Book book2 = (Book) cglib.getInstance(book1);
        String a = book2.getA();
        System.out.println("proxy:" + a);
    }

}
