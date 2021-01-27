import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * @author 黄药师
 * @date 2021-01-23 17:05
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   测试自定义 realm
 *
 *
 */
public class Shiro02Test {

    @Test
    public void test01(){

        // 01 获取securityManger的factory
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro02.ini");

        // 02  securityManger对象
        SecurityManager securityManager = factory.getInstance();
        // 03 把 securityManger设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        // 04 创建登录的token
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");

        // 05 拿到subject
        Subject subject = SecurityUtils.getSubject();

        // 06 进行判断 /  认证
        if (!subject.isAuthenticated()){

            try {
                subject.login(token);
            }catch (UnknownAccountException e){
                // UnknownAccountException 不知道账号异常
                System.out.println("不知道账户的异常 ");

            }catch (IncorrectCredentialsException ex){

                System.out.println("密码错误异常");
            }

            System.out.println("认证后: " + subject.isAuthenticated());
        }

    }
}
