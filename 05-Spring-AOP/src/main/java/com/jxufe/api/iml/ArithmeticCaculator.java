package com.jxufe.api.iml;

import com.jxufe.api.Caculator;

/**
 * Created by liuburu on 2017/5/23.
 */
public class ArithmeticCaculator implements Caculator {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int mul(int a, int b) {
        return a*b;
    }

    @Override
    public int div(int a, int b) {
        return a/b;
    }
}
