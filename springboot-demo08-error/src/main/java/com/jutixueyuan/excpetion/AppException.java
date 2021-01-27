package com.jutixueyuan.excpetion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 黄药师
 * @date 2021-01-23 9:37
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@ControllerAdvice
public class AppException {

    @ExceptionHandler(ArithmeticException.class)
    public String mathException(Exception ex, Model m){

        m.addAttribute("msg",ex.getMessage());
        return "demo01";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerException(Exception ex, Model m){

        m.addAttribute("msg",ex.getMessage());
        return "demo02";
    }

}
