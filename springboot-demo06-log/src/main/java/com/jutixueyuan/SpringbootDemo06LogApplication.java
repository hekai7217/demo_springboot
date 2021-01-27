package com.jutixueyuan;

import com.jutixueyuan.bean.Car;
import com.jutixueyuan.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

//@ImportResource("classpath:beans.xml") // 可以把外部的bean.xml的配置读取到 ioc容器中
@SpringBootApplication
public class SpringbootDemo06LogApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SpringbootDemo06LogApplication.class, args);

        Car car = run.getBean("car", Car.class);
        System.out.println("car = " + car);

        User user = run.getBean("u1", User.class);
        System.out.println("user = " + user);
    }

}
