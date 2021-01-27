import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * @author 黄药师
 * @date 2021-01-25 10:44
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   shiro的授权的代码的流程:
 *      01 securityManager 中的授权对象是 :
 *          AuthorizingSecurityManager
 *             调用了判断授权方法: isPermitted(PrincipalCollection principals, Permission permission)
 *
 *             到了 AuthorizingRealm 中的方法:
 *              public boolean isPermitted(PrincipalCollection principals, Permission permission) {
 *                   // 获取username的权限的数据  (自定义的realm授权获取数据 (从数据库中获取 )) 存在  AuthorizationInfo对象中
 *                   AuthorizationInfo info = this.getAuthorizationInfo(principals);
 *                   //判断是否有权限
 *                  return this.isPermitted(permission, info);
 *               }
 *
 *              判断是否有权限
 *                   permission 判断的权限  user:list
 *                  Collection<Permission> perms = this.getPermissions(info); 是获取自定义realm中从数据库获取的权限
 *                  逻辑判断
 *              protected boolean isPermitted(Permission permission, AuthorizationInfo info) {
         *         Collection<Permission> perms = this.getPermissions(info);
         *         if (perms != null && !perms.isEmpty()) {
         *             Iterator var4 = perms.iterator();
         *
         *             while(var4.hasNext()) {
         *                 Permission perm = (Permission)var4.next();
         *                 if (perm.implies(permission)) {
         *                     return true;
         *                 }
         *             }
         *         }
 *
 *         return false;
 *     }
 *
 */
public class Shiro05Test {

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

        }
    }
}
