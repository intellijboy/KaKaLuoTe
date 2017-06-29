package org.mybatis.example.bean;

import java.util.Date;

/**
 * Created by liuburu on 2017/6/9.
 */
public class Student {
    private Integer stu_no;
    private String name;
    private Date birthday;
    private Integer age;
    private Integer sex;

    public Student() {
    }

    public Integer getStu_no() {
        return stu_no;
    }

    public void setStu_no(Integer stu_no) {
        this.stu_no = stu_no;
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
                "stu_no=" + stu_no +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
