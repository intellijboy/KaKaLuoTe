package com.shrio.demo.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

/**
 * Created by liuburu on 2017/7/9.
 */
public class HelloRealm implements Realm {

    @Override
    public String getName() {
        System.out.println("HelloRealm getName");
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        System.out.println("HelloRealm supports");
        return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("HelloRealm getAuthenticationInfo");
        return null;
    }
}
