package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.example.bean.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 接口参数测试
 *
 * @author liuburu
 * @create 2017/08/08
 **/
public interface StudentParamMapper {

    Student stuQuery0(String stuNo);

    Student stuQuery1(Student student);

    Student stuQuery2(
            @Param("name") String name,
            @Param("password") String password,
            @Param("age") int age);

    Student stuQuery3(Map<String,Object> paramMap);


    /**
     * 多个参数
     * @param ids
     * @return
     */
    List<Student> stuQuery4(List<Integer> ids);

    /**
     * 多个参数
     * @param ids
     * @return
     */
    List<Student> stuQuery5(Collection<Integer> ids);


    /**
     * 多个参数
     * @param paramMap
     * @return
     */
    List<Student> stuQuery6(Map<String,Object> paramMap);

    /**
     * 多个参数
     * @param col
     * @return
     */
    List<Student> stuQuery7(
            @Param("mycollection")Collection<Integer> col,
            @Param("age")Integer age
    );





}
