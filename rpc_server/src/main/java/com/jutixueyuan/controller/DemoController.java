package com.jutixueyuan.controller;

import com.jutixueyuan.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 黄药师
 * @date 2021-02-06 9:39
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@CrossOrigin  // 可以解决 跨域问题
@Controller
public class DemoController {

    @ResponseBody
    @RequestMapping("demo01")
    public String helloRpc(String name){

        System.out.println(" 服务器调用了 ...");
        return "rpc " + name;

    }

    /**
     *  @RequestBody 如果请求 的数据是json的格式化
     *    @RequestBody 拿到请求体 直接把json的字符串拿到 赋值给参数变量name
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("demo02")
    public String helloRpc02(@RequestBody String name){


        System.out.println(" 服务器调用 json ...");
        return "rpc " + name;

    }

    @ResponseBody
    @RequestMapping("demo03")
    public String helloRpc03(@RequestBody User user){
        System.out.println("user = " + user);
        return "rpc obj";

    }
}
