import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-25 10:44
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   shiro的授权:
 *
 *    授权有3中方式:
 *     01 编码授权 通过subject 判断是否有权限
 *
 *     02 通过shiro提供的注解 授权
 *
 *     03 jsp/thymeleaf 页面的标签授权
 *
 *     这里是 javase 项目通过  编码授权 实现授权
 *
 *
 */
public class Shiro04Test {

    @Test
    public void test01(){

        // 01 认证

        // 01 获取securityManger的factory
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro04.ini");

        // 02  securityManger对象
        SecurityManager securityManager = factory.getInstance();
        // 03 把 securityManger设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        // 04 创建登录的token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");

        // 05 拿到subject
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()){ // 认证

            // 认证操作
            subject.login(token);
            System.out.println("subject.isAuthenticated() = " + subject.isAuthenticated());

            // 02 授权

            //通过方法判断是否有权限

            //判断 jack 用户有哪些权限 (jack用户的权限在ini 配置文件中已经设置好了)
            boolean permitted = subject.isPermitted("user:list");
            System.out.println("user:list 权限: permitted = " + permitted);

            // 可以判断是否有多个权限
            // 在多个权限中 是否有一个权限
            boolean permittedAll = subject.isPermittedAll("user:list", "user:delete");
            System.out.println("permittedAll = " + permittedAll);

            //判断权限
            // 01  isPermitted 判断权限
            // checkPermission 判断权限

//            try {
//                subject.checkPermission("user:delete");
//            }catch (AuthorizationException e){
//                System.out.println(e.getMessage());
//                System.out.println("异常错误...");
//            }

            // 判断角色
            boolean b = subject.hasRole("role1");
            System.out.println("角色: b = " + b);

            // 判断多个角色
//            subject.hasAllRoles()

        }
    }
}
