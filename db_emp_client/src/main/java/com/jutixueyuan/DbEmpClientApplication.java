package com.jutixueyuan;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class DbEmpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbEmpClientApplication.class, args);
    }

}
