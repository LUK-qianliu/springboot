package com.qianliu.springboot_test.shiro.realm;

import com.qianliu.springboot_test.model.User3;
import com.qianliu.springboot_test.service.UserService;
import com.qianliu.springboot_test.shiro.tokenBean.JwtToken;
import com.qianliu.springboot_test.utils.jwt.JWTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission和@RequiresPermissions标签之类的
     *
     * 利用 token 中获得的 username，分别从数据库查到该用户所拥有的角色，权限，存入 SimpleAuthorizationInfo 中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo principals");
        String username = JWTUtil.getUsername(principals.toString()); //将token中的内容拿出来
        User3 user = userService.getUserByUserName(username); //从数据库中更具username拿出user

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();//获取授权中心
        simpleAuthorizationInfo.addRole(user.getRole());  //角色

        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(","))); //用户的权限，可能是多种，放在一个set中
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，所有拦截器拦截下来的访问都会经过该方法，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo AuthenticationToken");
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User3 userBean = userService.getUserByUserName(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
