package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄药师
 * @date 2021-01-22 10:03
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String req01(Model m){

        m.addAttribute("name","王语嫣");

        return "demo01";

    }
}
