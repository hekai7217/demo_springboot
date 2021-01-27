package com.jutixueyuan.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-23 16:49
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *    realm   领域，范围
 *
 *      通常是 : 实际开发中主要调用开发者开发操作数据库的相关Service获取Dao来进行授权认证
 *       看下入门程序中的 simpleAccountRealm
 *           具体的认证信息是:
 *          *         SimpleAccountRealm realm来实现的  SimpleAccountRealm继承  AuthorizingRealm 实现
 *          *                 doGetAuthenticationInfo() 实现认证的的
 *          *
 *          *             01   UsernamePasswordToken upToken = (UsernamePasswordToken)token;
 *          *                  拿到 getUserName()
 *          *             02  去 user找是否存在 account
 *          *
 *          *             03 返回  AuthenticationInfo
 *
 *      如果你要自定义  realm 要和  SimpleAccountRealm 操作一样
 *
 *
 */
public class CustomerRealm extends AuthorizingRealm {

    /**
     *  授权  可以做那些事情
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *  认证
     *     登录的操作
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("调用了自定义realm === > CustomerRealm.doGetAuthenticationInfo");
        /**
         *
         *  01  通过token 拿到 username
         *  02 查询 数据库拿到user
         *  03 创建 一个 AuthenticationInfo 认证信息对象 返回
         */
        //01  getPrincipal() 这个方法获取 主要的用户名
        String username = (String) token.getPrincipal();

        // 02  去数据库查询user (入门项目中是查询 ini配置  实际开发中都是查询 db )
        List<String> names = Arrays.asList("lisi", "jack", "tiger");

        // 判断用户是否存在
        if (!names.contains(username)){
            // 从数据库查询一个对象过来了
            return null;
        }

        // 这里的数据是从数据库或者  ini配置  中获取的
        String user = "jack";
        String credentials = "123";

        // 要创建 一个 认证信息对象
        // 创建一个简单的认证信息对象
        /**
         *
         *   SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
         *
         *    01 principal    主角
         *    02 credentials  凭证 密码
         *    03 realmName    直接调用父类的方法就可以 getName()
         *
         */
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,credentials,getName());

        return simpleAuthenticationInfo;
    }
}
