package com.jxufe.proxy;

import com.jxufe.api.Caculator;
import com.jxufe.exception.MyArithmeticException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by liuburu on 2017/5/23.
 */
public class CaculatorProxy {
    /**
     * 需要代理的对象
     */
    Caculator target;

    public CaculatorProxy(Caculator target) {
        this.target = target;
    }

    /**
     * 返回日志代理对象
     *
     * @return
     */
    public Caculator getLoggingCaculatorProxy() {
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = new Class[]{Caculator.class};
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                System.out.println("[日志前置通知]:method name is " + methodName + "and args is " + Arrays.asList(args));
                Object result = null;
                try {
                    result = method.invoke(target, args);
                    System.out.println("[日志返回通知]:method result is " + result);
                } catch (Exception e) {
                    System.out.println("**************************");
                    System.out.println("[日志异常通知]:exception is " + e);
                    System.out.println(e.getCause());
                    System.out.println(e.getMessage());
                    StackTraceElement[] stackTrace = e.getStackTrace();
                    for (StackTraceElement element : stackTrace) {
                        System.out.println(element.getFileName() + " " + element.getClassName() +
                                " " + element.getMethodName() + " " + element.getLineNumber());
                    }
                    System.out.println("[日志代理目标失败]:" + target);
                    return -9999;
                }
                System.out.println("[日志后置通知]:method name is " + methodName);
                return result;
            }
        };
        Caculator caculatorProxy = (Caculator) Proxy.newProxyInstance(loader, interfaces, h);
        return caculatorProxy;
    }


    /**
     * 返回验证代理对象
     *
     * @return
     */
    public Caculator getValicatorCaculatorProxy() {
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = new Class[]{Caculator.class};
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                System.out.println("[验证前置通知]:method name is " + methodName + "and args is " + args);
                Object result = method.invoke(target, args);
                System.out.println("[验证返回通知]:method result is " + result);
                System.out.println("[日志后置通知]:method name is " + methodName);
                return result;
            }
        };
        Caculator caculatorProxy = (Caculator) Proxy.newProxyInstance(loader, interfaces, h);
        return caculatorProxy;
    }


    /**
     * 获取验证代理对象的日志代理对象
     *
     * @return
     */
    public Caculator getTwoProxy() {
        return null;
    }

}
