package com.jutixueyuan.springboot_demo02;

import com.jutixueyuan.springboot_demo02.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({MyConfig.class})
@SpringBootApplication
public class SpringbootDemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo02Application.class, args);
    }

}
