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
 * @date 2021-01-23 15:40
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *   
 *   测试 shiro 的登入和登出效果 
 */
public class Shiro01Test {


    /**
     *  测试shiro的认证
     */
    @Test
    public void test(){

        // 01 拿到 securityManager 工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro01.ini");
        // 02 创建 securityManger对象 (安全管理器)

        /**
         *  securityManger对象
         *   01 认证器
         *   02 授权器
         *   03 会话管理
         */
        SecurityManager securityManager = factory.getInstance();

        // 03 把 securityManger设置 当前的运行环境中
        // 通过SecurityUtils 把 securityManager 设置到工作环境中

        SecurityUtils.setSecurityManager(securityManager);

        // 04 创建用户认证授权的对象 token
        // 把当前的用户 和 realm中的用户进行 认证
        // realm中的用户 可以是配置文件 ini中的配置的用户 , 也可以是数据库中的用户

        // 如果 用户和密码一致 就是认证成功
        UsernamePasswordToken token = new UsernamePasswordToken("jack", "123");

        // 05 拿到主题 subject 这里提供了很方法
        Subject subject = SecurityUtils.getSubject();
        //  认证 / 授权 等等

        // 判断 是否 认证通过
        // isAuthenticated 判断是否认证
        System.out.println("认证前: = " + subject.isAuthenticated());

        // 认证不通过 进行 认证
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

        /**
         *
         *  认证流程 :
         *    01  通过 shiro.ini 文件 创建  SecurityManager
         *
         *    02 SecurityManager 是一个接口 :
         *         具体的认证对象: ModularRealmAuthenticator 进行认证
         *
         *       调用 ModularRealmAuthenticator IniRealm(给realm传入token) 去ini配置文件中查询用户信息
         *
         *       具体的认证信息是:
         *         SimpleAccountRealm realm来实现的  SimpleAccountRealm继承  AuthorizingRealm 实现
         *                 doGetAuthenticationInfo() 实现认证的的
         *
         *             01   UsernamePasswordToken upToken = (UsernamePasswordToken)token;
         *                  拿到 getUserName()
         *             02  去 ini或者 数据库 找是否存在 account
         *
         *             03 返回  AuthenticationInfo
         *
         */
    }

}
