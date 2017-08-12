package com.jxufe.dao;

import com.jxufe.entity.Employ;
import java.math.BigDecimal;

public interface EmployMapper {
    int deleteByPrimaryKey(BigDecimal empno);

    int insert(Employ record);

    int insertSelective(Employ record);

    Employ selectByPrimaryKey(BigDecimal empno);

    int updateByPrimaryKeySelective(Employ record);

    int updateByPrimaryKey(Employ record);
}