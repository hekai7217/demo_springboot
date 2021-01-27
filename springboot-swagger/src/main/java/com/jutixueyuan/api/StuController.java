package com.jutixueyuan.api;

import com.jutixueyuan.annotation.IncludeSwagger;
import com.jutixueyuan.bean.Stu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄药师
 * @date 2021-01-26 15:38
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@RestController
@RequestMapping("stu")
public class StuController {

    @RequestMapping("getstu")
    public Stu getstu(Integer id,String name){

        Stu stu = new Stu();
        stu.setId(id);
        stu.setName(name);
        stu.setInfo("你们都喜欢丝袜");
        stu.setAge(22);
        return  stu;
    }

    @IncludeSwagger
    @RequestMapping("findId")
    public Stu findId(Integer id){

        Stu stu = new Stu();
        stu.setId(id);
        stu.setName("小伙子");
        stu.setInfo("你们都喜欢丝袜");
        stu.setAge(22);
        return  stu;

    }
}
