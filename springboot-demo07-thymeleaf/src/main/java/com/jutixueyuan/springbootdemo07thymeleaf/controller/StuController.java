package com.jutixueyuan.springbootdemo07thymeleaf.controller;

import com.jutixueyuan.springbootdemo07thymeleaf.bean.Stu;
import com.jutixueyuan.springbootdemo07thymeleaf.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-22 15:44
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class StuController {

    @Autowired
    private StuService stuService;

    /**
     * 到学生页面
     * @param m
     * @return
     */
    @RequestMapping("listStus")
    public String listStus(Model m){
        List<Stu> stus = stuService.findStus();
        m.addAttribute("stus",stus);

        return "stus";
    }

    /**
     *  到 添加学生页面
     * @return
     */
    @RequestMapping("addStuPage")
    public String addStuPage(){
        return "addStu";
    }

    /**
     * 添加成功后查询所有的数据
     * @param stu
     * @return
     */
    @RequestMapping("add")
    public String add(Stu stu){

        int i = stuService.addStu(stu);

        //查询所有
        return "redirect:/listStus";
    }

    /**
     *  通过 id查询学生
     */
    @RequestMapping("findById")
    public String findById(Integer id,Model model){

       Stu stu  = stuService.findById(id);

        model.addAttribute("stu",stu);

        return "updateStu";
    }

    /**
     *  修改学生 查询所有
     * @param stu
     * @return
     */
    @RequestMapping("updateStu")
    public String updateStu(Stu stu){

        int i = stuService.updateStu(stu);

        //查询所有
        return "redirect:/listStus";
    }

    /**
     * 删除
     * @param stu
     * @return
     */
    @RequestMapping("delStu")
    public String delStu(Integer id){

        int i = stuService.delStu(id);

        //查询所有
        return "redirect:/listStus";
    }



}
