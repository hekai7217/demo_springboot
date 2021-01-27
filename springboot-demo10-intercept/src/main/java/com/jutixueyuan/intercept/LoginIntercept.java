package com.jutixueyuan.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 黄药师
 * @date 2021-01-23 14:10
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   拦截器: 可以在 执行 controller 的方法付之前进拦截
 */
@Component  // 把拦截器  注入ioc容器中
public class LoginIntercept implements HandlerInterceptor {

    /**
     *  preHandle 这个方法是 在执行controller之前执行的方法
     *    可以在这里进行拦截
     *
     *  返回值
     *     true   放行
     *     false  不用放行拦截了
     *
     *  和之前的springmvc 拦截器一样的使用
     *
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 01 从session 拿到 user
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        // user 为空    拦截

        if(username != null && username.length() > 0 ){
            System.out.println("LoginIntercept 拦截器放行 ");
            return true;

        }else{
            // user 不为空  放行
            System.out.println("LoginIntercept 没有登录 拦截不放行  ");
            return false;
        }
    }
}
