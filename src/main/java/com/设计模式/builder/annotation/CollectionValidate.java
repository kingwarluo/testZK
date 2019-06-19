package com.设计模式.builder.annotation;

import java.lang.annotation.*;

/**
 * @author kingwarluo
 * 集合类型校验
 * @date 2019/1/11 16:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CollectionValidate {

    /**
     * 校验空元素
     * @return
     */
    boolean empty() default false;

    /**
     * 校验最小size
     * @return
     */
    int min() default Integer.MIN_VALUE;

    /**
     * 校验最大size
     * @return
     */
    int max() default Integer.MAX_VALUE;

}
