package com.logger;

import com.google.common.collect.Maps;
import com.logger.itf.BiFunction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-13 17:22
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    ApplicationContext context;

    @Pointcut("execution(public * com.logger.OperateLogService.*(..))")
    public void logPointController(){}

    @Around("logPointController()")
    public Object injectLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("before");
        final Method method = ((MethodSignature) jp.getSignature()).getMethod();

        Map<String, Object> logParams = Maps.newHashMap();
        logParams.putAll(reslovParams(method, new BiFunction<Object, Integer, LogParam>() {
            @Override
            public LogParam apply(Integer integer, Object o) {
                return new MethodParameter(method, integer).getMethodAnnotation(LogParam.class);
            }
        }, jp.getArgs()));

        Object result = jp.proceed();

        if(method.isAnnotationPresent(LogParam.class)){
            logParams.putAll(reslovParams(method, new BiFunction<Object, Integer, LogParam>() {
                @Override
                public LogParam apply(Integer integer, Object o) {
                    return method.getAnnotation(LogParam.class);
                }
            }, result));
        }
        System.out.println("after");
        return null;
    }

    public Map<String, Object> reslovParams(Method method, BiFunction<Object, Integer, LogParam> logParam, Object... args){
        Map<String, Object> params = Maps.newHashMap();
        for (int i = 0; i < args.length; i++) {
            Object param = args[i];
            LogParam logP = logParam.apply(i, param);
        }
        return params;
    }

}
