package com.jxufe.anotation.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liuburu on 2017/5/23.
 */
public class PointCutExpression {
    @Pointcut("execution(public int com.jxufe.anotation.api.Caculator.*(..))")
    public void declareJointPointExpression(){}
}
