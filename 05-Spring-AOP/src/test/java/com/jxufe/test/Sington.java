package com.jxufe.test;

import java.security.Signature;

/**
 * Create Date: 2017/10/29
 * Description:
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class Sington {
    private Sington(){
        System.out.println("私有构造方法1");
    }
    private static class SingtonFactory{
        public static Sington sington = new Sington();
    }
    public static Sington getInstance(){
        return SingtonFactory.sington;
    }

    public static void main(String[] args) {
        System.out.println(Sington.getInstance());
    }
}
