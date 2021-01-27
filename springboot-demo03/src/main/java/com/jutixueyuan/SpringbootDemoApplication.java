package com.jutixueyuan;

import com.jutixueyuan.bean.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *   // @Import({MapperScannerRegistrar.class})  内部就是注册扫描 mapper到ioc容器中
 */
@MapperScan(basePackages = "com.jutixueyuan")
@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
       SpringApplication.run(SpringbootDemoApplication.class, args);

    }

}
