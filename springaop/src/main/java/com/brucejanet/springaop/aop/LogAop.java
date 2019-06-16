package com.brucejanet.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogAop {


//    //申明一个切点 里面是 execution表达式 要怎么
    @Pointcut("execution(  * com.brucejanet.springaop.controller.*.*(..) )")
    public void MyAssertControllerAspect(){}

    @Pointcut("execution(  * com.brucejanet.springaop.service.*.*(..) )")
    public void MyAssertServiceAspect(){}

    @After("annotationPointCut()")
    public void after_annotation(JoinPoint joinPoint){
        Object [] args = joinPoint.getArgs();
        String argsstring = Arrays.toString(joinPoint.getArgs());
        MethodSignature signature = (MethodSignature)(joinPoint.getSignature());

        Method method = signature.getMethod();
        LogActionPointCut action = method.getAnnotation(LogActionPointCut.class);
        System.out.println(action.name()+" : "+argsstring);
    }

    @Pointcut("@annotation(LogActionPointCut)")
    public void annotationPointCut(){};




    @Around("annotationPointCut()")
    public Object around_Service(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = proceedingJoinPoint.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;


        String className = proceedingJoinPoint.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature)(proceedingJoinPoint.getSignature());

        String methodName = signature.getMethod().getName();
        System.out.println(className + "." + methodName + "(),execute:"+time);
        return result;

    }

}
