package com.shrio.demo.dao;

import com.shrio.demo.entity.SecRolePermission;

public interface SecRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecRolePermission record);

    int insertSelective(SecRolePermission record);

    SecRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecRolePermission record);

    int updateByPrimaryKey(SecRolePermission record);
}