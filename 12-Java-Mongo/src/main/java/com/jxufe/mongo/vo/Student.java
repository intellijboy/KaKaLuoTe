package com.jxufe.mongo.vo;

import java.util.Date;

/**
 * Created by liuburu on 2017/6/9.
 */
public class Student {
    private Integer id;
    private String name;
    private Date birthday;
    private Integer age;
    private Integer sex;

    public Student() {
    }

    public Student(Integer id, String name, Date birthday, Integer age, Integer sex) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "stu_no=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
