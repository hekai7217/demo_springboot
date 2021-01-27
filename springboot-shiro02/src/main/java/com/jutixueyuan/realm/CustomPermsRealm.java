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

import java.util.Arrays;
import java.util.List;

/**
 * @author 黄药师
 * @date 2021-01-25 14:20
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   授权的realm realm
 */
public class CustomPermsRealm extends AuthorizingRealm {

    /**
     *  授权的
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("CustomPermsRealm.doGetAuthorizationInfo");
        // 01 拿到
        String  username = (String) principalCollection.getPrimaryPrincipal();

        // 02 username 去数据库中查询 权限
        List<String> perms = Arrays.asList("stu:list", "stu:create", "stu:delete", "teacher:list");

        // 03 创建权限信息对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(perms);

        return authorizationInfo;
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

        //加密后的密码
        String pwd = "dcd171b1ae8d58251fbacbf91a89c82d";
        // 盐值
        ByteSource salt = ByteSource.Util.bytes("abc");
        //返回一个认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,pwd,salt,this.getName());

        return simpleAuthenticationInfo;
    }
}
