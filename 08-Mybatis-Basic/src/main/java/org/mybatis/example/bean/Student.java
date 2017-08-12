package org.mybatis.example.bean;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by liuburu on 2017/6/9.
 */
@Alias("highStudent")
public class Student {
    private Integer stuNo;
    private String name;
    private String password;
    private Date birthday;
    private Integer age;
    private Integer sex;

    public Student() {
    }

    public Student(Integer stuNo, String name) {
        this.stuNo = stuNo;
        this.name = name;
    }

    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
