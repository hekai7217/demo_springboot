package com.jutixueyuan.controller;

import com.jutixueyuan.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 黄药师
 * @date 2021-02-07 10:37
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class IndexController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @RequestMapping("/demo01")
    public String req01(String name){

        return demoService.sayDubbo(name);

    }
}
