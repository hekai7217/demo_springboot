package com.jutixueyuan.config;

import com.jutixueyuan.bean.Car;
import com.jutixueyuan.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 黄药师
 * @date 2021-01-22 11:06
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Configuration
public class MyConfig {

    /**
     *    @Bean 只能用在方法上面
     *       注入到ioc容器中的id为 方法的名称 user
     *
     * @return
     */
    @Bean(value = "u1")
    public User user(){

        return new User();
    }

    @Bean
    public Car car(){

        return new Car();
    }
}
