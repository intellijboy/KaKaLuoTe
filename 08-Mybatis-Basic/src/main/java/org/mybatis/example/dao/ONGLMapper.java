package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.bean.Student;

import java.util.List;

/**
 * ONGL测试
 *
 * @author liuburu
 * @create 2017/08/09
 **/
public interface ONGLMapper {

    List<Employ> selectEmploy1(String ename);

    List<Employ> selectEmploy2( String ename);

    List<Employ> selectEmploy3(@Param("ename") String ename);

    List<Employ> selectEmploy4(@Param("employ") Employ employ);



    //ONGL参数:[0, 1, param1, param2]
    List<Employ> selectEmploy5( String ename,String job);

    //ONGL参数:parameters are [ename, job, param1, param2]
    List<Employ> selectEmploy6(@Param("ename") String ename,@Param("job")String job);


    List<Employ> selectEmploy7(@Param("job")String job);


    List<Employ> selectEmploy8(@Param("ename")String ename);


    List<Employ> selectEmploy9(@Param("ename")String ename);


    List<Employ> selectEmploy10(@Param("ename")String ename);


    List<Student> selectStudent(@Param("name")String name);




}
