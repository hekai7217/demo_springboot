package com.jutixueyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 *    Quartz  是定时任务
 *
 *        spring.3.0 提供了定时任务
 *
 *    01 这个任务相关的类在  spring-conext-support.jar
 *
 *    02 springboot 整合 Quartz
 *        01 在pom中依赖  spring-conext-support.jar
 *        02 使用 @Scheduled注解在配置对应的参数就可以完成配置就可以
 *        03 完成一个 一个Scheduled的任务
 *
 */
//@EnableScheduling  // 开启定时任务  把 @Scheduled 标记的类注入到ioc容器中
@SpringBootApplication
public class SpringbootDemo09JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo09JobApplication.class, args);
    }

}
