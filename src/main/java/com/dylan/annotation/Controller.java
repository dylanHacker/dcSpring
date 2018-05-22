package com.dylan.annotation;

import java.lang.annotation.*;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/22 下午10:18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";
}
