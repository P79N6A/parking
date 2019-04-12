package com.zhuyitech.parking.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author walkman
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String value() default "";
}
