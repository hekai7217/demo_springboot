package com.jutixueyuan.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 黄药师
 * @date 2021-01-25 15:03
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
@RequestMapping("stu")
public class StuController {
    /**
     * 这个请求就是 登出 跳转到 登录页面的处理
     *
     * @return
     */
    @RequestMapping("stuPage")
    // 直接在 url的映射上面进行权限的配置
    @RequiresPermissions("stu:list")
    public String toLoginPage() {
        return "stuPage";
    }

    @RequestMapping("update")
    @RequiresPermissions("stu:list")
    public String update() {
        return "stuPage";
    }

    @RequestMapping("delete")
    @RequiresPermissions("stu:delete")
    public String delete() {
        return "stuPage";
    }

}
