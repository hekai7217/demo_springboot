package com.jutixueyuan.controller;

import com.jutixueyuan.pojo.Dept;
import com.jutixueyuan.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 14:58
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/depts")
    public String listDept(Model model){

        // 查询数据
        List<Dept> dept = deptService.findDept();
        model.addAttribute("depts",dept);
        return "dept";
    }
}
