package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Select;
import org.mybatis.example.bean.Employ;

/**
 * Created by liuburu on 2017/6/9.
 */
public interface EmployAnotationMapper {

    @Select(value = "select * from emp where empno = #{empno}")
    Employ selectEmploy(int empno);

}
