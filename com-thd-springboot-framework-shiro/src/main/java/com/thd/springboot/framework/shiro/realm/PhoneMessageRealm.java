package com.thd.springboot.framework.shiro.realm;

import com.thd.springboot.framework.shiro.bean.ShiroPermissions;
import com.thd.springboot.framework.shiro.bean.ShiroRole;
import com.thd.springboot.framework.shiro.bean.ShiroUser;
import com.thd.springboot.framework.shiro.token.PhoneMessageToken;
import com.thd.springboottest.shiro.entity.Permissions;
import com.thd.springboottest.shiro.entity.Role;
import com.thd.springboottest.shiro.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * com.thd.springboottest.shiro.service.CustomRealm
 * User: devil13th
 * Date: 2020/1/23
 * Time: 16:27
 * Description: No Description
 */
public class PhoneMessageRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;


    public PhoneMessageRealm(){
        this.setAuthenticationTokenClass(PhoneMessageToken.class);
    }
    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户信息
        ShiroUser user = (ShiroUser)principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (ShiroRole role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (ShiroPermissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("[PhoneMessageRealm] doGetAuthenticationInfo");
        PhoneMessageToken token = null;

        // 如果是PhoneToken，则强转，获取phone；否则不处理。
        // 如果该类添加了 supports 方法则不用判断(模板模式中，已经事先调用supports进行了判断，如果符合的token才会进入到本代码方法)
        if(authenticationToken instanceof PhoneMessageToken){
            token = (PhoneMessageToken) authenticationToken;
        }else{
            return null;
        }

        String validateCode = (String) token.getCredentials();

        ShiroUser user = (ShiroUser)token.getPrincipal();

        if (user == null) {
            throw new UnknownAccountException("手机号不存在!");
        }

        // 验证手机获取的验证码

        // 这个code应该是根据token的principal查询发送的验证码
        String code = "123456";




       // return new SimpleAuthenticationInfo(phone, "123456", this.getName());
        return new SimpleAuthenticationInfo(user, code, this.getName());

    }

    /**
     * 用来判断是否使用当前的 realm
     * @param var1 传入的token
     * @return true就使用，false就不使用
     */
    public boolean supports(AuthenticationToken var1) {
        System.out.println("supports");
        return var1 instanceof PhoneMessageToken;
    }


}
