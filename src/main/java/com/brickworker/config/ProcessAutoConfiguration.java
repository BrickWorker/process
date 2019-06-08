package com.brickworker.config;

import com.brickworker.advice.ProcessAdvice;
import com.brickworker.advice.TracerAdvice;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @ Author     ：brickworkers.
 * @ Date       ：Created in 20:45 2019-06-08
 * @ Description：
 */

@Configurable
@ConditionalOnClass(ProcessAdvice.class)
public class ProcessAutoConfiguration {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public ProcessAdvice processAdvice(){
        return new ProcessAdvice();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public TracerAdvice tracerAdvice(){
        return new TracerAdvice();
    }
}
