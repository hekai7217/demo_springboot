package com.jutixueyuan;

import com.jutixueyuan.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *  01  @SpringBootApplication
 *        是一个组合注解:
 *          1.1 @SpringBootConfiguration   配置类型
 *              和一起的application.xml 配置一样的 或者用@configuration标记的配置类一样
 *
 *          1.2 @EnableAutoConfiguration
 *                 开启自动注解配置类
 *                 @Import(AutoConfigurationImportSelector.class)   给ioc容器中注入了  AutoConfigurationImportSelector类
 *
 *                 AutoConfigurationImportSelector类 ?
 *
 *                 开启扫描,查看哪些类有 AutoConfiguration 类型, 把这类注入到ioc容器中(完成自动配置)
 *                     AopAutoConfiguration 配置  aop的自动配置类
 *
 *                Stringboot-xxx-starter 里面都有一个 AutoConfiguration的包
 *                 WebMvcAutoConfiguration  springmvc的配置
 *                  这里会给我们配置 前端控制器
 *                     DispatcherServletAutoConfiguration
 *                       01 配置前端控制器时候 会加载
 *                         WebMvcProperties
 *                         XXXProperties (这个类的配置就是从 application.properties读取配置的数据 )
 *                         拿到你配置的数据
 *
 *                     MultipartResolver  配置文件解析器
 *
 *               这个注解总结:
 *               @EnableAutoConfiguration
 *                01 完成自动配置
 *                02 拿到 application.properties 的配置的数据
 *
 *
 *          1.3 @ComponentScan  组件扫描
 *           默认扫描 SpringbootDemo03Application 及他的子包下面的组件
 *             @ComponentScan(basePackages = "com")  // 指定扫描 的位置
 *
 *
 *         SpringbootDemo03Application 就是一个启动类型 程序入口
 *
 *            SpringApplication.run()  启动ioc容器
 *
 *
 */
//@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com")  // 指定扫描 的位置
public class SpringbootDemo03Application原理 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(SpringbootDemo03Application原理.class, args);

        // 拿到容器中的内容 
        User bean = ioc.getBean(User.class);
        System.out.println("bean = " + bean);

        // springmvc 的组件  (有)
        DispatcherServlet bean1 = ioc.getBean(DispatcherServlet.class);
        System.out.println("bean1 = " + bean1);

        // ioc 容器中有哪些内容

        // 拿到 ioc容器中 的所有的bean的名称
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();

        System.out.println("**********************************");
        // 打印
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName = " + beanName);
        }

    }

}
