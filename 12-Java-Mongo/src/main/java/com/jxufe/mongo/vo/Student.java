package com.jxufe.mongo.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by liuburu on 2017/6/9.
 */
@Document
public class Student {

    @Id
    private Integer stuId;
    private String name;
    private Date birthday;
    private Integer age;
    private Integer sex;

    public Student() {
    }

    public Student(Integer stuId, String name, Date birthday, Integer age, Integer sex) {
        this.stuId = stuId;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
