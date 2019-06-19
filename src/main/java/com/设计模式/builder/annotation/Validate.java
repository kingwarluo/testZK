package com.设计模式.builder.annotation;

import java.lang.annotation.*;

/**
 * @author kingwarluo
 * 普通校验
 * @date 2019/1/11 16:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Validate {

    /**
     * 校验null
     * @return
     */
    boolean nullable() default false;

}
