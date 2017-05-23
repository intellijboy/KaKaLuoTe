package com.jxufe.anotation.api.iml;

import com.jxufe.anotation.api.Caculator;
import org.springframework.stereotype.Component;

/**
 * Created by liuburu on 2017/5/23.
 */
@Component
public class ArithmeticCaculator implements Caculator {
    @Override
    public int add(int a, int b) {
        int result = a+b;
        return result;
    }

    @Override
    public int sub(int a, int b) {
        int result = a-b;
        return result;
    }

    @Override
    public int mul(int a, int b) {
        int result = a*b;
        return result;
    }

    @Override
    public int div(int a, int b) {
        int result = a/b;
        return result;
    }
}
