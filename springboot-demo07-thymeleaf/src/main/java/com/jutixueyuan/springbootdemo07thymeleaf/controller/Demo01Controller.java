package com.jutixueyuan.springbootdemo07thymeleaf.controller;

import com.jutixueyuan.springbootdemo07thymeleaf.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-22 14:16
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Controller
public class Demo01Controller {

    /**
     *
     *    处理请求 返回视图(thymeleaf)的名称
     *
     *
     * @return
     */
    @RequestMapping("index")
    public String req01(){

        return "index";
    }

    /**
     *   把数据分享到  demo01 页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("demo01")
    public String demo01(Model model, HttpSession session){

        model.addAttribute("name","周芷若");
        model.addAttribute("age",24);

        session.setAttribute("sessionName","赵敏");

        return "demo01";
    }

    @RequestMapping("demo02")
    public String demo01(Model model){

        List<User> users = new ArrayList<>();

        User u1 = new User();
        u1.setId(1);
        u1.setName("赵敏");
        u1.setAge(20);
        u1.setPhone("123");

        User u2 = new User();
        u2.setId(2);
        u2.setName("杨不悔");
        u2.setAge(22);
        u2.setPhone("123");

        User u3 = new User();
        u3.setId(3);
        u3.setName("纪晓芙");
        u3.setAge(40);
        u3.setPhone("123");

        users.add(u1);
        users.add(u2);
        users.add(u3);

        model.addAttribute("users",users);

        List<String> strs = Arrays.asList("java", "sql", "html", "springboot");

        model.addAttribute("strs", strs);

        return "demo02";
    }

    @RequestMapping("demo03")
    public String demo03(Model model){
        model.addAttribute("name","周芷若");
        return "demo03";
    }

    @RequestMapping("demo04")
    public String demo04(String name,String age){

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "demo03";
    }

    /**
     *
     *  restful 拿到数据
     *    01   @RequestMapping("/demo05/detail/{id}/{name}/{age}")
     *      参数在url上面
     *
     *    02 拿到url上面从参数
     *      通过 @PathVariable 获取url上面从参数
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/demo05/detail/{id}/{name}/{age}")
    public String demo05(
            @PathVariable String id,
            @PathVariable String name,
                         @PathVariable String age){

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "demo03";
    }

    @RequestMapping("/demo06")
    public String demo06(Model m){

        m.addAttribute("msg","hellojava");

        m.addAttribute("date",new Date());

        return "demo04";
    }
}
