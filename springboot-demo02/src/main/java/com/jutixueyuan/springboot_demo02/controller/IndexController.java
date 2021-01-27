package com.jutixueyuan.springboot_demo02.controller;

import com.jutixueyuan.springboot_demo02.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄药师
 * @date 2021-01-20 14:28
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@RestController
public class IndexController {

    @Autowired
    private MyConfig myConfig;

    @RequestMapping("/demo01")
    public String hello(){
        return "hello springboot";
    }

    @RequestMapping("/demo02")
    public MyConfig demo01(){
        return myConfig;
    }

}
