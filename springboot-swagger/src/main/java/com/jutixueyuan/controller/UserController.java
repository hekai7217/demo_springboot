package com.jutixueyuan.controller;

import com.jutixueyuan.bean.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄药师
 * @date 2021-01-26 15:38
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("getUser")
    public User getUser(Integer id,String name){

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setInfo("你们都喜欢丝袜");
        user.setAge(22);
        return  user;
    }
}
