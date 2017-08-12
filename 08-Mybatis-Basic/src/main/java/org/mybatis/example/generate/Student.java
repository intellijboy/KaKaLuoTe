package org.mybatis.example.generate;

public class Student {
  private String stu_no;
  private String name;
  private java.util.Date birthday;
  private String age;
  private String sex;
  private String password;

  public String getStu_no() {
    return stu_no;
  }

  public void setStu_no(String stu_no) {
    this.stu_no = stu_no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public java.util.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.util.Date birthday) {
    this.birthday = birthday;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
