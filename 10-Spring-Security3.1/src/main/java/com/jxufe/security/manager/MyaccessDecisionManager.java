package com.jxufe.security.manager;

import com.jxufe.security.vo.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * Created by liuburu on 2017/6/28.
 */
public class MyaccessDecisionManager implements AccessDecisionManager {

    private Logger logger = LoggerFactory.getLogger(MyaccessDecisionManager.class);

    /**
     * @param authentication 认证用户
     * @param o              请求资源
     * @param collection     请求资源对象所需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUser) {
            collection.contains(principal);
            return;
        }
        logger.info("==>没有{}访问权限",o);
        throw new AccessDeniedException(" 没有权限访问！ ");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
