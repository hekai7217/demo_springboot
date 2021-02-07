package com.jutixueyuan;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *    dubbo 的provider的实现步骤:
 *
 *    01   写一个公共的 commons
 *
 *    02   添加dubbo,zookeeper的依赖
 *
 *    03   在dubbo中实现 commons中接口方法
 *
 *    04   把服务注册到 zookeeper中  @DubboService
 *
 *    05   开启zookeeper, springboot 添加配置文件
 *
 *        dubbo:
 *           application:
 *              name: dubbo-provider   服务名称
 *
 *          registry:   注册中心的配置
 *                      注册中心名称://ip:端口
 *              address: zookeeper://192.168.154.111:2181
 */
@EnableDubbo
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
