package com.brickworker.advice;

import com.alibaba.fastjson.JSONObject;
import com.brickworker.annotation.Process;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @ Author     ：brickworkers.
 * @ Date       ：Created in 19:17 2019-06-08
 * @ Description：@Process处理,日志打印，耗时打印
 */


@Aspect
public class ProcessAdvice {

    @Around("@annotation(com.brickworker.annotation.Process)")
    public Object invoke(ProceedingJoinPoint point){
        Object obj = null;
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Process process = method.getAnnotation(Process.class);
        //日志打印
        Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
        //类.方法
        String name = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        //计算耗时
        long start = System.currentTimeMillis();
        //日志输入打印
        logger.info(">>>> - {}|{}", name, JSONObject.toJSONString(point.getArgs()));
        //执行
        try {
            obj = point.proceed();
        }catch (Throwable e){
            logger.warn(">><<< - {}", name, e);
        }finally {
            if(null != obj){
                long costTime = System.currentTimeMillis() - start;
                logger.info("<<<< - {}|{}|{}", name, costTime, JSONObject.toJSONString(obj));
            }
        }
        return obj;
    }

}

