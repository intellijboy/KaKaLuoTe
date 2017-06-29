package com.jxufe.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by liuburu on 2017/6/22.
 */
public class MyAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("authentication success==>" + authentication);
        String userName1 = getCurrentUsername1();
        String userName2 = getCurrentUsername2();
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authentication);
        System.out.println("userName1=" + userName1);
        System.out.println("userName2=" + userName2);
        String redirectURL = "/security/success";
        response.sendRedirect(redirectURL);
    }

    /**
     * 通过SecurityContextHolder获取用户名
     *
     * @return
     */
    public String getCurrentUsername1() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String passwordd = ((UserDetails) principal).getPassword();
            System.out.println("password==>" + passwordd);
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }
        return String.valueOf(principal);
    }

    public String getCurrentUsername2() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
