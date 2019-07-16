package com.proxy.fieldConverter.convertor;

import java.lang.annotation.*;

/**
 * @description:字段描述类
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FieldDescriber {

    String value() default "";

    Class<? extends FieldValueConverter> converter() default FieldValueConverter.DefaultFieldValueConverter.class;

}
