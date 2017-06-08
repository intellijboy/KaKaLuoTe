package com.jxufe.test;

import com.jxufe.utils.JDBCUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by liuburu on 2017/6/5.
 */
public class testUtil {
    @Test
    public void testQuery() throws Exception {
        String sql = "SELECT studentid id,sname stuName,password,age FROM student";
        List<Student> students = JDBCUtil.queryList(Student.class, sql);
        System.out.println(students);
    }
}
