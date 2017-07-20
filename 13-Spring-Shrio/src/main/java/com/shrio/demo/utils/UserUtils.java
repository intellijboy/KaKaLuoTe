package com.shrio.demo.utils;

import com.shrio.demo.entity.ShrioUser;

/**
 * 用户查询工具类
 *
 * @author liuburu
 * @create 2017/07/18
 **/
public class UserUtils {
    public static ShrioUser findUserByName(String username) {
        ShrioUser shrioUser = new ShrioUser(10086, "liuburu@outlook.com", 24, true, true, true, true);
        shrioUser.setUsername(username);
        shrioUser.setPassword("123456");
        if (username.equals("lockuser")) {
            shrioUser.setAccountNonLocked(false);
        }
        if (username.equals("unknown")) {
        }
        if (username.equals("expired")) {
            shrioUser.setCredentialsNonExpired(false);
        }
        if (username.equals("incorrect")) {
            shrioUser.setPassword("error_password");
        }
        return shrioUser;
    }
}
