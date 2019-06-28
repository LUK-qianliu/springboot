package com.qianliu.springboot_test.controller;

import com.qianliu.springboot_test.model.ResponseBean;
import com.qianliu.springboot_test.model.User3;
import com.qianliu.springboot_test.service.UserService;
import com.qianliu.springboot_test.utils.jwt.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 这是一个用户接入的controller
 */
@RestController
public class UserAccessController {
    @Autowired
    private UserService userService;

    /**
     * 登陆：完成JWT的注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        User3 userBean = userService.getUserByUserName(username);
        if (userBean.getPassword().equals(password)) {
            return new ResponseBean(200, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * 所有人都可以访问，但是用户与游客看到的内容不同
     * @return
     */
    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    /**
     * 登入的用户才可以进行访问
     * @return
     */
    @GetMapping("/require_auth")
    @RequiresAuthentication  //需要登陆的用户，也就是用户请求request的头部含有“Authentication”标签
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    /**
     * admin的角色用户才可以登入
     * @return
     */
    @GetMapping("/require_role")
    @RequiresRoles("admin") //需要角色为admin
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    /**
     * 拥有view和edit权限的用户才可以访问
     * @return
     */
    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"}) //需要permission是view或者edit
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }

    /**
     * 未授权的用户访问，跳转到此页面
     * @return
     */
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }

}
