package com.jxufe.service;

import com.jxufe.security.vo.SecurityUser;

import java.util.List;

/**
 * Created by liuburu on 2017/6/28.
 */
public interface UserService {
    SecurityUser selectUserInfoByName(String userName);

    List<String> selRoleInfoByName(String userName);
}
