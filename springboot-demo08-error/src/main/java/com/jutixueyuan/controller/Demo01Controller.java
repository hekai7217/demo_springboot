package com.jutixueyuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 黄药师
 * @date 2021-01-23 9:17
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *
 *  01 springboot 异常从处理 和springmvc的处理一样的
 *
 *     01 局部处理
 *              @ExceptionHandler。但是只能对当前控制器中方法出现异常进行解决。
 *     02 全局处理
 *              通过@ControllerAdvice结合@ExceptionHandler。
 *              当全局异常处理和局部处理同时存在时，局部生效（就近原则）
 *
 *
 */
@Controller
public class Demo01Controller {

    @RequestMapping("demo01")
    public String req01(int type){

        //算术异常 
        if(type == 1){
            int a = 1 / 0 ;
        }
        if (type == 2){
            
            Object obj = null;

            System.out.println("obj.toString() = " + obj.toString());
            
        }

        return "index";

    }

//    @ExceptionHandler(ArithmeticException.class)
//    public String mathException(Exception ex, Model m){
//
//        m.addAttribute("msg",ex.getMessage());
//        return "demo01";
//    }
//
//    @ExceptionHandler(NullPointerException.class)
//    public String nullPointerException(Exception ex, Model m){
//
//        m.addAttribute("msg",ex.getMessage());
//        return "demo02";
//    }
}
