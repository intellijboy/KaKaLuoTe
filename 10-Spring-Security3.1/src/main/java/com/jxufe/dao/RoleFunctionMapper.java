package com.jxufe.dao;

import com.jxufe.entity.RoleFunction;
import com.jxufe.security.dto.FunctionRoleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface RoleFunctionMapper {
    int deleteByPrimaryKey(@Param("rid") Integer rid, @Param("fid") Integer fid);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);
    /**
     * 需要转换成Map<String, Collection<ConfigAttribute>>
     * @return
     */
    Collection<FunctionRoleDTO> selAllResourseRole();
}