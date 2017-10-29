package com.jxufe.test;

import com.jxufe.api.Caculator;
import com.jxufe.api.iml.ArithmeticCaculator;
import com.jxufe.proxy.CaculatorProxy;
import org.junit.Test;

/**
 * Created by liuburu on 2017/5/23.
 */
public class TestProxy {

    /**
     * 日志代理测试
     */
    @Test
    public void testLogginProxy1() {
        ArithmeticCaculator caculator = new ArithmeticCaculator();
        Caculator caculator1Proxy = null;
        try {
            caculator1Proxy = new CaculatorProxy(caculator).getLoggingCaculatorProxy();
        } catch (Exception e) {
        }
        if (caculator1Proxy != null) {
            int reuslt = caculator1Proxy.div(100, 10);
            System.out.println(reuslt);
        }

    }

    /**
     * 日志代理异常测试
     */
    @Test
    public void testLogginProxy2() {
        ArithmeticCaculator caculator = new ArithmeticCaculator();
        Caculator caculator1Proxy = null;
        try {
            caculator1Proxy = new CaculatorProxy(caculator).getLoggingCaculatorProxy();
        } catch (Exception e) {
        }
        if (caculator1Proxy != null) {
            int reuslt = caculator1Proxy.div(100, 0);
            System.out.println(reuslt);
        }
    }

    /**
     * 验证代理测试
     */
    @Test
    public void testValidatorProxy1() {
        ArithmeticCaculator caculator = new ArithmeticCaculator();
        Caculator validatorProxy = null;
        try {
            validatorProxy = new CaculatorProxy(caculator).getValicatorCaculatorProxy();
        } catch (Exception e) {
        }
        if (validatorProxy != null) {
            int reuslt = validatorProxy.div(100, 10);
            System.out.println(reuslt);
        }
    }

    /**
     * 验证代理异常测试
     */
    @Test
    public void testValidatorProxy2() throws Exception {
        ArithmeticCaculator caculator = new ArithmeticCaculator();
        Caculator validatorProxy = null;
        try {
            validatorProxy = new CaculatorProxy(caculator).getValicatorCaculatorProxy();
        } catch (Exception e) {
            throw e;
        }
        int reuslt1 = validatorProxy.div(100, -10);
        System.out.println(reuslt1);
    }

    /**
     * 同时为target对象做两层代理
     */
    @Test
    public void testTowProxy() {
        ArithmeticCaculator caculator = new ArithmeticCaculator();
        Caculator proxy = null;
        try {
            //1.做验证代理
            //2.做日志代理
        } catch (Exception e) {
        }
        if (proxy != null) {
            int reuslt = proxy.div(100, 10);
            System.out.println(reuslt);
        }
    }
}
