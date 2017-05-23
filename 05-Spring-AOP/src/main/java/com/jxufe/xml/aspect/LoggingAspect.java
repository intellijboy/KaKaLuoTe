package com.jxufe.xml.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志切面
 * Created by liuburu on 2017/5/23.
 */
public class LoggingAspect {

    /**
     * 前置通知：方法执行前进行调用
     * @param joinPoint
     */
    public void beforedMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object args[] = joinPoint.getArgs();
        System.out.println("[Xml日志前置通知]:"+"method is "+methodName+" args is"+ Arrays.asList(args));
    }

    /**
     * 后置通知：无论目标方法是否发生异常，该后置通知均会进行执行
     * 注意：后置通知不能获取返回结果。
     * @param joinPoint
     */
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object args[] = joinPoint.getArgs();
        System.out.println("[Xml日志后置通知]:"+"method is "+methodName+" args is"+ Arrays.asList(args));
    }

    /**
     * 返回通知：若发生异常，则不执行；若正常执行，那么就能够获取到返回值
     * @param result
     */
    public void afterReturnMethod( Object result){
        System.out.println("[Xml日志返回通知]：reuslt="+result);
    }

    /**
     * 异常通知：目标方法抛出异常，则会执行该方法
     * @param ex
     */

    public void afterThrowMethod(Exception ex){
        System.out.println("[Xml日志返回通知]：reuslt="+ex.getMessage());
    }

}
