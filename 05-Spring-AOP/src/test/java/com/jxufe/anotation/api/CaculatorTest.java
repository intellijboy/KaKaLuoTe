package com.jxufe.anotation.api;

import com.jxufe.api.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuburu on 2017/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-anotation.xml"})
public class CaculatorTest {

    @Autowired
    private Caculator caculator;
    @Test
    public void div() throws Exception {
        int reuslt = caculator.div(100,0);
        System.out.println(reuslt);
    }

}