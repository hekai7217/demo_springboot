package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄药师
 * @date 2021-01-22 11:12
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@RestController
public class IndexController {

    @RequestMapping("demo01")
    public String hello01(){
        System.out.println("IndexController.hello01");
        System.out.println("IndexController.hello01");
        System.out.println("IndexController.hello01");
        System.out.println("IndexController.hello01");
        System.out.println("IndexController.hello01");

        return "hello 01   maven";
    }

    @RequestMapping("demo02")
    public String hello02(){
        System.out.println("IndexController.hello02");
        System.out.println("IndexController.hello02");
        return "hello02   maven";
    }
}
