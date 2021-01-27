package com.jutixueyuan.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄药师
 * @date 2021-01-25 15:03
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
@RequestMapping("teacher")
public class TeacherController {
    /**
     *  这个请求就是 登出 跳转到 登录页面的处理
     *
     * @return
     */
    @RequestMapping("teacherPage")
    @RequiresPermissions("teacher:list")
    public String toLoginPage(){
        return "teacherPage";
    }

}
