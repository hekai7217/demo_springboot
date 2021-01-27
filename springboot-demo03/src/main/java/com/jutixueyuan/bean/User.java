package com.jutixueyuan.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 黄药师
 * @date 2021-01-20 15:43
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */

@Component
public class User {

    @Value("zhang")
    private String name;

    @Value("123")
    private String pwd;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
