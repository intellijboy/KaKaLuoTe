package com.jxufe.security.session;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liuburu on 2017/6/30.
 */
public class MyConcurrentSessionControlStrategy implements MessageSourceAware,SessionAuthenticationStrategy {

    @Override
    public void setMessageSource(MessageSource messageSource) {
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SessionAuthenticationException {

    }
}
