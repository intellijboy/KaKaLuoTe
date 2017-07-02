package com.jxufe.service;

import com.jxufe.entity.User;
import com.jxufe.security.vo.SecurityUser;

import java.util.List;

/**
 * Created by liuburu on 2017/6/28.
 */
public interface UserService {
    SecurityUser selectUserInfoByName(String userName);

    List<String> selectRoleInfoByName(String userName);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    SecurityUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void permitAll();

    void denyAll();
}
