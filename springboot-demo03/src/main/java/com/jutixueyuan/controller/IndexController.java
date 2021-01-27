package com.jutixueyuan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄药师
 * @date 2021-01-20 15:35
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
