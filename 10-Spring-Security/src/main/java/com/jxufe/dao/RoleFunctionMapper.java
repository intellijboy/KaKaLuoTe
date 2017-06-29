package com.jxufe.dao;

import com.jxufe.entity.RoleFunction;
import org.apache.ibatis.annotations.Param;

public interface RoleFunctionMapper {
    int deleteByPrimaryKey(@Param("rid") Integer rid, @Param("fid") Integer fid);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);
}