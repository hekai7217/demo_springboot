package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 黄药师
 * @date 2021-01-23 14:15
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@RestController
public class UserController {

    @RequestMapping("login")
    public String login(String name, HttpSession session){

        session.setAttribute("username",name);
        return "login success";
    }

    @RequestMapping("demo01")
    public String demo01(){

        return "demo01 success";
    }

    @RequestMapping("demo02")
    public String demo02(){

        return "demo02 success";
    }
}
