package com.jutixueyuan.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 黄药师
 * @date 2021-01-26 16:24
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Retention(RetentionPolicy.RUNTIME)  // 注解的生命周期
@Target({ElementType.METHOD,ElementType.TYPE})  //注解的使用范围
public @interface IncludeSwagger {
}
