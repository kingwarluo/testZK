package com.设计模式.builder.annotation;

import java.lang.annotation.*;

/**
 * @author kingwarluo
 * String类型校验
 * @date 2019/1/11 16:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface StringValidate {

    /**
     * 校验empty
     * @return
     */
    boolean empty() default false;

    /**
     * 校验空串
     * @return
     */
    boolean blank() default false;
}
