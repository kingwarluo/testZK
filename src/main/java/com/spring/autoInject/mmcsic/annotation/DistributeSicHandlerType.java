package com.spring.autoInject.mmcsic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: jianhua.luo
 * @Description:
 * @Date: Created in 10:07 2019/11/29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributeSicHandlerType {

    String value();
    String description();
}
