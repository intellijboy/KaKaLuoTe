package org.mybatis.example.dao;

import org.mybatis.example.bean.Employ;

/**
 * Created by liuburu on 2017/6/9.
 */
public interface EmployMapper {

    Employ selectEmploy(int empno);

}
