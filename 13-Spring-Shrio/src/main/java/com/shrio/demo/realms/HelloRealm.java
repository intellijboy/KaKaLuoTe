package com.shrio.demo.realms;

import com.shrio.demo.entity.ShrioUser;
import com.shrio.demo.utils.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

/**
 * 如果需要进行认证而不需要授权，直接继承AuthenticatingRealm即可，
 * 而不必实现Realam接口。
 * Created by liuburu on 2017/7/9.
 */
public class HelloRealm extends AuthenticatingRealm {
    private Logger logger = LoggerFactory.getLogger(HelloRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //principal 认证实体
        //credentials 凭据，密码
        logger.info("HelloRealm getAuthenticationInfo");
        String realmName = getName();
        Object credentials = "123456";
        String username = (String) authenticationToken.getPrincipal();
        ShrioUser principal = UserUtils.findUserByName(username);
        AuthenticationInfo authenticationInfo = null;
        if ("kakaluote".equals(principal.getUsername())) {//正常用户
            authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);
        } else if (!principal.isAccountNonLocked()) {
            throw new LockedAccountException("账户锁定");
        } else if (!principal.isCredentialsNonExpired()) {
            throw new IncorrectCredentialsException("账号密码错误");
        } else if (!principal.isCredentialsNonExpired()) {
            throw new ExpiredCredentialsException("账户过期用户");
        } else {
            throw new UnknownAccountException("账号不存在");
        }
        return authenticationInfo;
    }




}
