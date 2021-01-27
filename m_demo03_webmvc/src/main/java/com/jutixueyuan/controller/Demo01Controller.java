package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄药师
 * @date 2021-01-19 15:38
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class Demo01Controller {

    @RequestMapping("demo01")
    public String req01(Model m){

        m.addAttribute("name1","王语嫣");
        m.addAttribute("name2","段誉");

        return "demo01";
    }

}
