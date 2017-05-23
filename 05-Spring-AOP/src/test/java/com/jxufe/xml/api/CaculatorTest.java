package com.jxufe.xml.api;

import com.jxufe.anotation.api.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by liuburu on 2017/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-xml.xml"})
public class CaculatorTest {
    @Autowired
    private Caculator caculator;
    @Test
    public void div() throws Exception {
        System.out.println(caculator.div(100,10));
    }

}