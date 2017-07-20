package com.shrio.demo.realms;

import com.shrio.demo.entity.ShrioUser;
import com.shrio.demo.utils.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 认证和授权的Realm
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public class AuthenticateAuthorizeRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(HelloRealm.class);

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("AuthenticateAuthorizeRealm getAuthenticationInfo执行认证");
        String realmName = getName();
        Object credentials = "123456";
        String username = (String) authenticationToken.getPrincipal();
        ShrioUser principal = UserUtils.findUserByName(username);
        AuthenticationInfo authenticationInfo = null;
        if (!principal.isAccountNonLocked()) {
            throw new LockedAccountException("账户锁定");
        } else if (!principal.isCredentialsNonExpired()) {
            throw new IncorrectCredentialsException("账号密码错误");
        } else if (!principal.isCredentialsNonExpired()) {
            throw new ExpiredCredentialsException("账户过期用户");
        } else if ("unknow".equals(principal.getUsername())) {
            throw new UnknownAccountException("账号不存在");
        } else {
            authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);
        }
        return authenticationInfo;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("AuthenticateAuthorizeRealm getAuthenticationInfo执行授权");
        ShrioUser principal = (ShrioUser) principalCollection.getPrimaryPrincipal();

        //1.利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>();

        //1.1加载角色
        roles.add("user");
        if ("admin".equals(principal.getUsername())) {
            roles.add("admin");
        }
        //1.2加载权限
        List<String> permissions = new ArrayList<String>();
        if(roles.contains("admin")){
            permissions.add("sys:user:add");
            permissions.add("sys:user:update");
            permissions.add("sys:user:delete");
            permissions.add("sys:user:query");
        }else if(roles.contains("user")){
            permissions.add("sys:user:query");
        }
        //2. 封装 SimpleAuthorizationInfo, 并设置其 reles 属性和Permission属性.
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.addStringPermissions(permissions);
        //3. 返回 SimpleAuthorizationInfo 对象.
        return info;
    }


}
