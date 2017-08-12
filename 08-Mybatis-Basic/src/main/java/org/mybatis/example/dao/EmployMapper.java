package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.bean.Student;

import java.util.List;

/**
 * Created by liuburu on 2017/6/9.
 */
public interface EmployMapper {

    Employ selectEmploy(int empno);

    List<Student> selectStudent(String stuNo);

    int updateStudent(int stuNo);

    List<Student> selectEmploys(@Param("name") String name);

}
