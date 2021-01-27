import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * @author 黄药师
 * @date 2021-01-26 14:16
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 * shiro 管理session 在javaSe项目中也是可以使用
 */
public class Shiro06Test {

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

            // 获取session

            Session session = subject.getSession();

            //设置 值到session 中
            session.setAttribute("key1","乔峰");

            // 获取session中的值
            String key1 = (String) session.getAttribute("key1");
            System.out.println("key1 = " + key1);
        }

    }
}
