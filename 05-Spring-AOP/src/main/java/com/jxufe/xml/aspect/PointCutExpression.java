package com.jxufe.xml.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liuburu on 2017/5/23.
 */
public class PointCutExpression {
    @Pointcut("execution(public * com.jxufe.xml.api.Caculator.*(..))")
    public void declareJointPointExpression(){}
}
