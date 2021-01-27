package com.jutixueyuan.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 黄药师
 * @date 2021-01-26 9:44
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *    自定义了一个过滤器
 *       继承 FormAuthenticationFilter 过滤器
 *       重写  onLoginSuccess()方法
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     *  装饰者模式
     *      在不修改原来的代码上 重写父类的方法 实现增强
     *      IO 流中很多
     *      FileInputStream()
     *        增强
     *         BuffreInputStream() extend FileInputStream()
     *           有缓存的功能
     *
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        // 把之前 报错的req错误请求进行清理
        WebUtils.getAndClearSavedRequest(request);

        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     *   自定义认证过滤器重写isAccessAllowed 方法
     *      把cookie中存储从认证信息存储在 session中
     *
     *
     *    记住我的时候, 认证信息存储在cookie, 在当前的index 不用认证
     *    其他的页面 还是需要认证
     *    解决 :
     *     重写 isAccessAllowed 方法
     *     把cookie的认证信息存储到session中
     *     其他的页面能共享数据,不用在进行认证的操作
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //从请求中获取Shiro的 主体
        Subject subject = getSubject(request, response);
        //从主体中获取Shiro框架的Session
        Session session = subject.getSession();
        //如果主体没有认证（Session中认证）并且 主体已经设置记住我了
        if (!subject.isAuthenticated() && subject.isRemembered()) {
            //获取主体的身份（从记住我的Cookie中获取的）
            String principal = (String) subject.getPrincipal();
            //将身份认证信息共享到 Session中
            session.setAttribute("USER_IN_SESSION", principal);
            System.out.println(" 记住我的时候  把我们的 认证信息存储到session中 ");
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }
}
