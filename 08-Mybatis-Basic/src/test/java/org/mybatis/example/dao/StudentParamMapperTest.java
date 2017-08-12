package org.mybatis.example.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Student;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

/**
 * ${DESCRIPTION}
 *
 * @author liuburu
 * @create 2017/08/08
 **/
public class StudentParamMapperTest {



    /**
     * 非线程安全的，用完之后需要马上关闭,请求或方法作用域
     */
    private SqlSession sqlSession;

    /**
     * sqlSessionFactory的作用域：一旦被创建就应该在应用的运行期间一直存在
     */
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-mapper-param.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void destorySqlsession(){
        this.sqlSession.close();
    }

    @Test
    public void stuQuery0() throws Exception {
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        Student student = studentParamMapper.stuQuery0("12306");
        System.out.println(student);
    }

    @Test
    public void stuQuery1() throws Exception {
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        Student student = new Student();
        student.setName("铷973");
        student.setPassword("liuburu");
        student.setAge(25);
        Student student1 = studentParamMapper.stuQuery1(student);
        System.out.println(student1);

    }

    @Test
    public void stuQuery2() throws Exception {
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        Student student1 = studentParamMapper.stuQuery2("铷973","liuburu",25);
        System.out.println(student1);
    }

    @Test
    public void stuQuery3() throws Exception {
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("name","铷973");
        paramMap.put("password","liuburu");
        paramMap.put("age",25);
        Student student1 = studentParamMapper.stuQuery3(paramMap);
        System.out.println(student1);
    }

    @Test
    public void stuQuery4() throws Exception {
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(975);
        ids.add(976);
        ids.add(977);
        List<Student> students = studentParamMapper.stuQuery4(ids);
        for (Student stu:students){
            System.out.println(stu);
        }
    }
    @Test
    public void stuQuery5() throws Exception {
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        Collection<Integer> collection = new HashSet<>();
        collection.add(975);
        collection.add(976);
        collection.add(977);
        List<Integer> ids = new ArrayList<>();
        ids.add(975);
        ids.add(976);
        ids.add(977);
        List<Student> students = studentParamMapper.stuQuery5(ids);
        for (Student stu:students){
            System.out.println(stu);
        }
    }

    @Test
    public void stuQuery6(){
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);

        //list
        List<Integer> list = new ArrayList<>();
        list.add(95);
        list.add(96);
        list.add(97);
        //collection
        Collection<Integer> collection = new HashSet<>();
        collection.add(98);
        collection.add(99);
        collection.add(100);
        //array
        Integer[] array = new Integer[]{101,102};
        //student
        Student student = new Student();
        student.setSex(1);
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(101,"zhangsan"));
        studentList.add(new Student(102,"lisi"));


        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("mylist",list);
        paramMap.put("mycollection",collection);
        paramMap.put("myarray",array);
        paramMap.put("age",30);
        paramMap.put("student",student);
        paramMap.put("studentList",studentList);

        List<Student> students = studentParamMapper.stuQuery6(paramMap);
        for (Student stu:students){
            System.out.println(stu);
        }
    }

    @Test
    public void stuQuery7(){
        this.sqlSession = sqlSessionFactory.openSession();
        StudentParamMapper studentParamMapper = this.sqlSession.getMapper(StudentParamMapper.class);
        //collection
        Collection<Integer> collection = new HashSet<>();
        collection.add(98);
        collection.add(99);
        collection.add(100);
        Integer age = 30;
        List<Student> students = studentParamMapper.stuQuery7(null,null);
        for (Student stu:students){
            System.out.println(stu);
        }
    }

}