package com.jxufe.xml.api.iml;

import com.jxufe.xml.api.Caculator;
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
        int result = 0;
        try{
            result = a/b;
        }catch (Exception ex){
        }
        return result;
    }
}
