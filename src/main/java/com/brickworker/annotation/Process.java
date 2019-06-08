package com.brickworker.annotation;

import java.lang.annotation.*;

/**
 * @ Author     ：brickworkers.
 * @ Date       ：Created in 19:15 2019-06-08
 * @ Description：切面，统计方法执行消耗时间
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Process {

}
