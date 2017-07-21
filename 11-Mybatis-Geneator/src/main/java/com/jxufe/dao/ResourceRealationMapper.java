package com.jxufe.dao;

import com.jxufe.entity.ResourceRealation;
import org.apache.ibatis.annotations.Param;

public interface ResourceRealationMapper {
    int deleteByPrimaryKey(@Param("resourceid") String resourceid, @Param("fileid") String fileid);

    int insert(ResourceRealation record);

    int insertSelective(ResourceRealation record);
}