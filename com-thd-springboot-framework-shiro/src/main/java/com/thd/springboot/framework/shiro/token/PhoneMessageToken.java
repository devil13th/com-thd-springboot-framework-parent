package com.thd.springboot.framework.shiro.token;

import com.thd.springboot.framework.shiro.bean.ShiroUser;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class PhoneMessageToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    /**
     * The phone
     */
    private String phone;
    private ShiroUser user;
    private boolean rememberMe;
    private String host;
    private String validateCode;


    public PhoneMessageToken(ShiroUser u, String validateCode) {
        this.rememberMe = false;
        this.host = null;
        this.user=u;
        this.validateCode = validateCode;
    }



    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setHost(String host) {
        this.host = host;
    }


    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public boolean isRememberMe() {
        return this.rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return this.validateCode;
    }



}
