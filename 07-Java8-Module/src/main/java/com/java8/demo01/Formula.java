package com.java8.demo01;

/**
 * 允许在接口中有默认方法实现,允许我们使用default关键字
 * Created by liuburu on 2017/6/8.
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
