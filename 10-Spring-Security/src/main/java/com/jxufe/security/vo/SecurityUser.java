package com.jxufe.security.vo;

import com.jxufe.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by liuburu on 2017/6/25.
 */
@Component
public class SecurityUser extends User implements UserDetails{

    private Logger logger = LoggerFactory.getLogger(SecurityUser.class);

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public int hashCode() {
        String result = getId()+getUsername();
        return result.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        SecurityUser other = (SecurityUser) obj;
        if(obj==null){
            return false;
        }
        if(getUsername().equals(other.getUsername())){
            return true;
        }
        if(getId().equals(other.getId())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
