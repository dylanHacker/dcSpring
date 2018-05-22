package com.dylan.annotation;

import java.lang.annotation.*;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/22 下午10:25
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
}
