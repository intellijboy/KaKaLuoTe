package com.shrio.demo.dao;

import com.shrio.demo.entity.SecPermission;

public interface SecPermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(SecPermission record);

    int insertSelective(SecPermission record);

    SecPermission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(SecPermission record);

    int updateByPrimaryKey(SecPermission record);
}