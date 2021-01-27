package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄药师
 * @date 2021-01-25 14:33
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
