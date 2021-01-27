package com.jutixueyuan.config;

import com.jutixueyuan.intercept.LoginIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 黄药师
 * @date 2021-01-23 14:18
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   在springmvc 之前
 *      拦截器 需要在 springmvc.xml 中进行配置
 *
 *   springboot 中 也需要 配置
 *      使用注解@Configuration  配置拦截器
 *
 *    01  WebMvcConfigurer mvc的配置接口
 *         提供了很多方法  可以进行mvc的配置 (这里的方法和springmvc的配置是一样的)
 *
 *    02  addInterceptors 就是注册 指定的 拦截器
 *      01 注册 拦截器
 *
 *      02  addPathPatterns()  拦截器设置拦截器规则
 *           你要拦截哪些url   /**  拦截所有的请求
 *         excludePathPatterns(); excludePathPatterns();
             你要放行那些url  /login  放行 登录页面
 *
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    // 把ioc容器中的拦截器 注入到 这里
    @Autowired
    private LoginIntercept loginIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginIntercept)
                // 给 拦截器设置拦截器规则
                .addPathPatterns("/**")
                // 给 拦截器设置排除 规则
                .excludePathPatterns("/login");

    }
}
