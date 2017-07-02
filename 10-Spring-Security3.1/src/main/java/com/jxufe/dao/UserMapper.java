package com.jxufe.dao;

import com.jxufe.entity.User;
import com.jxufe.security.vo.SecurityUser;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    SecurityUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<String> selRoleInfoByName(String userName);

    SecurityUser selectUserInfoByName(String userName);
}