package com.jutixueyuan.controller;

import com.jutixueyuan.pojo.Emp;
import com.jutixueyuan.service.EmpService;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-07 15:39
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("findEmp")
    public String findEmp(Integer did, Model model){
        //查询 emps
        List<Emp> emps = empService.findEmp(did);
        model.addAttribute("emps",emps);
        return "emps";
    }

}
