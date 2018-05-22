package com.dylan.annotation;

import java.lang.annotation.*;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/22 下午10:22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

    String value() default "";
}
