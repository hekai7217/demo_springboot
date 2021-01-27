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
 * @date 2021-01-25 14:20
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   自定义realm
 */
public class CustomRealm  extends AuthorizingRealm {

    /**
     *  授权的
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     *   认证的方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        /**
         *  认证:01
         *
         *     01 拿到 token 唯一标识
         *     02 从数据库中拿到  user的信息
         *         密码 / 盐值
         *     03 返回  认证信息
         */

        String  user = (String) token.getPrincipal();

        List<String> names = Arrays.asList("jack", "rose", "scott");
        if (!names.contains(user)){
            return null;
        }

        String pwd = "123";

        //返回一个认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,pwd,this.getName());

        return simpleAuthenticationInfo;
    }
}
