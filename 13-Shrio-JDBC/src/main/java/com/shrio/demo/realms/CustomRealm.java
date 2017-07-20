package com.shrio.demo.realms;

import com.shrio.demo.entity.SecUser;
import com.shrio.demo.service.SecUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * 自定义访问Realm
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SecUserService secUserService;
    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        //credentical为数据库中的SHA1加密密码
        SecUser secUser = secUserService.queryByUserName(username);
        Object credentials = secUser.getPassword();
        String realName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(secUser,credentials,credentialsSalt,realName);
        return authenticationInfo;
    }

    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取认证对象
        SecUser secUser = (SecUser) principalCollection.getPrimaryPrincipal();
        //2.加载该认证对象的角色信息以及该用户的权限信息
        String username = secUser.getUserName();
        Set<String> roles = secUserService.queryRoles(username);
        List<String> permissions = secUserService.queryPermissions(username);
        //3.包装授权信息，并且进行返回
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.addStringPermissions(permissions);
        return info;
    }
}
