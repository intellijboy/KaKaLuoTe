package com.jxufe.dao;

import com.jxufe.entity.SysOrg;

public interface SysOrgMapper {
    int deleteByPrimaryKey(String orgid);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(String orgid);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}