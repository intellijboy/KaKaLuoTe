package com.jxufe.dao;

import com.jxufe.entity.ResourcesFile;

public interface ResourceFileMapper {
    int deleteByPrimaryKey(String resourceid);

    int insert(ResourcesFile record);

    int insertSelective(ResourcesFile record);

    ResourcesFile selectByPrimaryKey(String resourceid);

    int updateByPrimaryKeySelective(ResourcesFile record);

    int updateByPrimaryKey(ResourcesFile record);
}