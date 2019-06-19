package com.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author kingwarluo
 * springAOP代理
 * @date 2019/1/30 13:51
 */

@Aspect
@Component
@Order(1)
public class SpringProxy {

    /**
     * 定义切入点，执行com.spring.所有类.getName方法时，切入
     */
    @Pointcut("execution(* com.spring.*.getName())")
    public void getName(){}

    @Before("getName()")
    public void getNameBefore(){
        System.out.println("before getName pointcut");
    }

    @After("getName()")
    public void getNameAfter(){
        System.out.println("after getName pointcut");
    }

    /**
     * 环绕触发
     * @return
     */
    @Around("getName()")
    public Object doAroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around getName before");
        Object obj = pjp.proceed();//执行方法
        System.out.println("around getName after");
        return obj;
    }

}
