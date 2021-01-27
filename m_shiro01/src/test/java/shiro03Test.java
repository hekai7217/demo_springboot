import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * @author 黄药师
 * @date 2021-01-25 9:32
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
public class shiro03Test {

    @Test
    public void test01(){

        // 01 获取 factory
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro03.ini");

        // 02 获取安全管理
        SecurityManager securityManager = factory.getInstance();
        // 03 把安全管理器设置到 环境中

        SecurityUtils.setSecurityManager(securityManager);

        // 04 创建token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");

        //05 拿到subject
        Subject subject = SecurityUtils.getSubject();
        // 06 登录
        // 没有认证 进行认证
        if (!subject.isAuthenticated()){
            subject.login(token);
            System.out.println("认证是否成功:"+subject.isAuthenticated());
        }

    }
}
