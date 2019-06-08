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
@Tracer
public @interface Process {

    /**
     * 阈值，设定方法最大可接受的阈值，-1表示不打印
     * @return
     */
    int threshold() default -1;

}
