package org.mybatis.example.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.bean.Student;

/**
 * Mybatis缓存接口
 *
 * @author liuburu
 * @create 2017/10/13
 **/
public interface CacheMapper {

    Employ selectEmploy(String empno);

    int updateStudent( @Param("stuNo") String stuNo, @Param("name") String name);

    int updateEmploy(Employ employ);
}
