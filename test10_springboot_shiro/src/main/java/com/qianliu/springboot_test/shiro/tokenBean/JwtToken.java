package com.qianliu.springboot_test.shiro.tokenBean;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * token的载体
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
