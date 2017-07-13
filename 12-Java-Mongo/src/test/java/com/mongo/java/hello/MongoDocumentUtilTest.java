package com.mongo.java.hello;

import com.jxufe.mongo.gridfs.CustomGridFsTemplate;
import com.jxufe.mongo.vo.Student;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by liuburu on 2017/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-fs.xml"})
public class MongoDocumentUtilTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testGetTemplete() {
        System.out.println("evaMontoTemplete==>" + mongoTemplate);
    }

    @Test
    public void testSave() {
        Student student = new Student();
        student.setStuId(33848);
        student.setName("黄铷");
        student.setAge(24);
        student.setSex(0);
        student.setBirthday(new Date());
        mongoTemplate.insert(student);
        System.out.println("插入成功！！！");
    }

    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setStuId(33846);
        student.setAge(26);
        Query query = new Query(Criteria.where("student._id").is(33846));
        Update update = new Update().set("student.$.name", "刘氏有");
        Student andModify = mongoTemplate.findAndModify(query, update, Student.class);
        System.out.println("更新成功！！！==>" + andModify);
    }

    @Test
    public void testQuery() {
        /*1.查询所有*/
//        List<Student> students = mongoTemplate.findAll(Student.class);
//        for(Student student:all){
//            System.out.println(student);
//        }
        /*2.按照id进行查询*/
//        Query query = new Query(Criteria.where("_id").is(33846));
//        List<Student> students = mongoTemplate.find(query, Student.class);

        /*3.条件and,or,模糊，分页，统计值，属性值*/
        //3.1 or条件
//        Query query = new Query();
//        query.addCriteria(new Criteria().andOperator(Criteria.where("age").gte(25),Criteria.where("sex").is(1)));
        //3.2 and条件
//        Query query = new Query();
//        query.addCriteria(new Criteria().orOperator(Criteria.where("_id").is(33846),Criteria.where("sex").is(1)));
        //3.3 模糊条件
//        Query query = new Query(Criteria.where("name").regex(".*?铷.*"));
        //3.4 分页条件
        int pageSize =2;
        int pageNumber = 2;
        Query query = new Query().skip((pageNumber - 1) * pageSize).limit(pageSize);
        List<Student> students = mongoTemplate.find(query, Student.class);
        for (Student student : students) {
            System.err.println(student);
        }
    }


}
