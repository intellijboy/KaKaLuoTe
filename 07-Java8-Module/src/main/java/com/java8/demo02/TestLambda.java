package com.java8.demo02;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by liuburu on 2017/6/8.
 */
public class TestLambda {

    /**
     * 传统比较字符串方式
     */
    @Test
    public void test1() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.println(names);
    }

    /**
     * 使用Lambda表达式
     */
    @Test
    public void test2() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        /*方法一*/
//        Collections.sort(names, (String a, String b) -> {
//            return a.compareTo(b);
//        });
        /*方法二*/
//        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        /*方法三*/
        Collections.sort(names, (a,b) -> a.compareTo(b));
        System.out.println(names);
    }
}
