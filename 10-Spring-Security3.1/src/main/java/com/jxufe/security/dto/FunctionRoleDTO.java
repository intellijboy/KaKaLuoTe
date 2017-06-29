package com.jxufe.security.dto;

import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;

/**
 * Created by liuburu on 2017/6/28.
 */
public class FunctionRoleDTO {
    private String url;
    private Collection<ConfigAttribute> roles;

    public FunctionRoleDTO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<ConfigAttribute> getRoles() {
        return roles;
    }

    public void setRoles(Collection<ConfigAttribute> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "FunctionRoleDTO{" +
                "url='" + url + '\'' +
                ", roles=" + roles +
                '}';
    }
}
