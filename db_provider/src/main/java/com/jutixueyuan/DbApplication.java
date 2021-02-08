package com.jutixueyuan;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 黄药师
 * @date 2021-02-07 14:51
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@MapperScan("com.jutixueyuan.mapper")
@EnableDubbo
@SpringBootApplication
public class DbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }

}
