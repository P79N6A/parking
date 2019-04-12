package com.zhuyitech.parking.common.annotation;

import java.lang.annotation.*;

/**
 * 在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，未登录返回401错误
 *
 * @author walkman
 * @date 2017/12/27
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    String value() default "";
}
