package com.jutixueyuan;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *   dubbo 的consumer的实现方式 :
 *
 *      01   写一个公共的 commons
 *  *
 *  *    02   添加dubbo,zookeeper的依赖
 *  *
 *  *    03   在dubbo中实现 commons中接口方法
 *  *
 *  *    04    在service中
 *                  引入 zookeeeper 中注册的服务
 *
 *              @DubboReference
 *              private DemoService demoService;
 *
 *       05    完成controller的开发
 *  *
 *  *    06   开启zookeeper, springboot 添加配置文件
 *  *
 *  *        dubbo:
 *  *           application:
 *  *              name: dubbo-provider   服务名称
 *  *
 *  *          registry:   注册中心的配置
 *  *                      注册中心名称://ip:端口
 *  *              address: zookeeper://192.168.154.111:2181
 *
 *         开启 dubbo注解使用  @EnableDubbo
 *
 */
@EnableDubbo
@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
