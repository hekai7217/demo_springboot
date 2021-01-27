package com.jutixueyuan.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-23 16:49
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   自定义realm 授权的操作
 */
public class CustomerPermissionRealm extends AuthorizingRealm {

    /**
     *  授权  可以做那些事情
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        /**
         * 01 拿到 token 拿到 getPrincipal()
         * 02 通过principal去数据库中查询 当前用户对应的 权限信息
         * 03 把权限数据返回给 授权器
         */
         String username = (String) principalCollection.getPrimaryPrincipal();

         // 去数据库中查询数据 模拟数据库的操作
        /**
         *  权限都是: 资源:操作:具体对象
         *
         */
        // 权限的数据源
        List<String> perms = Arrays.asList("user:list", "user:delete", "user:create");

        // username 在数据库中有哪些角色
        List<String> roles = Arrays.asList("role1");

        // 03 把权限数据返回给
        // 授权信息对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 把授权信息设置到 授权信息对象中
        simpleAuthorizationInfo.addStringPermissions(perms);
        // 添加角色
        simpleAuthorizationInfo.addRoles(roles);

        return simpleAuthorizationInfo;
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
        // 不是明文 加密的 密码
        // 加密的密码是从数据库中获取的
        String credentials = "123";

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
