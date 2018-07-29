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
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2. 从 UsernamePasswordToken 中来获取 username
        String username = usernamePasswordToken.getUsername();
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        //credentical为数据库中的SHA1加密密码
        SecUser secUser = secUserService.queryByUserName(username);
        Object credentials = secUser.getPassword();
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if(secUser==null){
            throw new UnknownAccountException("用户不存在!");
        }
        String realName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
        //2). credentials: 密码.
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
