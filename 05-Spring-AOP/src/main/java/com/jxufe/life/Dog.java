package com.jxufe.life;

import javax.annotation.PostConstruct;

/**
 * 狗的生命周期
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public class Dog {
    private String name;

    public Dog() {
        System.out.println("构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set方法");
        this.name = name;
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct方法");
    }

}
