package com.dylan.framework.annotation;

import java.lang.annotation.*;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/22 下午10:24
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}
