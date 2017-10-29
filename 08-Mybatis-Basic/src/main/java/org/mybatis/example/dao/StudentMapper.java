package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.example.bean.Student;

/**
 * Created by liuburu on 2017/6/9.
 */
public interface StudentMapper {

    int insertStudent(Student student);

    int updateStudent(@Param("stuNo") String stuNo, @Param("name") String name);

}
