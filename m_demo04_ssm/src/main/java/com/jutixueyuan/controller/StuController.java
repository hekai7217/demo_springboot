package com.jutixueyuan.controller;

import com.jutixueyuan.pojo.Stu;
import com.jutixueyuan.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-19 16:01
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    @RequestMapping("listStus")
    public String list(Model m){
        List<Stu> stus = stuService.findStus();
        m.addAttribute("stus",stus);

        return "stus";
    }

    @RequestMapping("addstu")
    public String addStu(Stu stu){
        stuService.addStu(stu);
        return "redirect:/listStus";
    }

}
