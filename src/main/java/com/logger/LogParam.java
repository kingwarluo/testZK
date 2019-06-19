package com.logger;

import java.lang.annotation.*;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-24 11:30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface LogParam {

    String value();
}
