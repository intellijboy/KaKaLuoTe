package com.jxufe.test;

import org.junit.Test;

/**
 * Created by liuburu on 2017/5/11.
 */
public class TestDebug {
    private String name="default";
    @Test
    public void testDebug(){
        method1();
    }

    public void method1(){
        this.name = "刘卜铷1";
        method2();
        System.out.println("method1 is working....");
    }
    public void method2(){
        this.name = "刘卜铷2";
        method3();
        System.out.println("method2 is working....");
    }

    public void method3(){
        this.name = "刘卜铷3";
        method4();
        System.out.println("method3 is working....");
    }

    public void method4(){
        this.name = "刘卜铷4";
        System.out.println("method4 is working....");
    }


}
