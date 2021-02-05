package com.spring.junittest;

import com.spring.junittest.annotation.RemoteService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private String url;

    private String remoteType;

    public MyInvocationHandler(String url, String remoteType) {
        this.url = url;
        this.remoteType = remoteType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().isAnnotationPresent(RemoteService.class)) {
            StringBuilder sb = new StringBuilder();
            RemoteService remoteService = method.getDeclaringClass().getAnnotation(RemoteService.class);
            String serviceId = sb.append(remoteService.value()).append(".").append(method.getName()).toString();
            // 执行远程调用过程
            return serviceId;
        } else {
            return null;
        }
    }

}
