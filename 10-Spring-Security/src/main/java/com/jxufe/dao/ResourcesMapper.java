package com.jxufe.dao;

import com.jxufe.entity.Resources;

public interface ResourcesMapper {
    int insert(Resources record);

    int insertSelective(Resources record);
}