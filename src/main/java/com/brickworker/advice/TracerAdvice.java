package com.brickworker.advice;

import com.brickworker.annotation.Tracer;
import com.brickworker.support.ContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @ Author     ：brickworkers.
 * @ Date       ：Created in 21:35 2019-06-08
 * @ Description：方法追踪
 */
@Aspect
public class TracerAdvice {



    @Around("@annotation(com.brickworker.annotation.Tracer)")
    public Object invoke(ProceedingJoinPoint point){
        Object obj = null;
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        //日志打印
        Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
        //类.方法
        String name = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        //计算耗时
        long start = System.currentTimeMillis();
        try {
            obj = point.proceed();
        }catch (Throwable e){
            logger.warn("method invoke error", e);
        }finally {
            // 插入方法执行时间
            long cost = System.currentTimeMillis() - start;
            ContextHolder.set(String.format("\n\r %s invoke cost %s", name, cost) );
        }
        return obj;
    }


}
