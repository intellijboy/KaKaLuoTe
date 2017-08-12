package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.example.bean.Student;

import java.util.Map;

/**
 * 动态SQL查询Mapper
 *
 * @author liuburu
 * @create 2017/08/08
 **/
public interface StudentSQLInjectMapper {

    Student rightLoginQuery(Student student);

    Student wrongLoginQuery(Student student);


}
