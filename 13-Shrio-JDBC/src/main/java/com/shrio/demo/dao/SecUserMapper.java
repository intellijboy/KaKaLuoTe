package com.shrio.demo.dao;

import com.shrio.demo.entity.SecUser;

import java.util.List;
import java.util.Set;

public interface SecUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SecUser record);

    int insertSelective(SecUser record);

    SecUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SecUser record);

    int updateByPrimaryKey(SecUser record);

    SecUser selectSecUser(String userName);

    Set<String> selectRoles(String userName);

    List<String> selectPermissions(String userName);


}