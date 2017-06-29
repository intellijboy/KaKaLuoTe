package com.java8.demo01;

import org.junit.Test;

/**
 * Created by liuburu on 2017/6/8.
 */
public class TestFormula {
    @Test
    public void test(){
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        double r1 = formula.calculate(100);     // 100.0
        double r2 = formula.sqrt(16);           // 4.0
        System.out.println("r1="+r1);
        System.out.println("r2="+r2);

    }
}
