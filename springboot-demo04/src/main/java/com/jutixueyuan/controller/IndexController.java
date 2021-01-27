package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄药师
 * @date 2021-01-22 9:39
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */

@Controller
public class IndexController {


    /**
     *
     *  springboot 默认整合 thymeleaf模板
     *    返回的字符串 会自动去 templates 下面找指定的
     *       模板
     *
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
