package com.jxufe.dao;

import com.jxufe.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("rid") Integer rid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<String> selRoleInfoByName(String userName);
}