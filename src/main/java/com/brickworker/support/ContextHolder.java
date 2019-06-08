package com.brickworker.support;

/**
 * @ Author     ：brickworkers.
 * @ Date       ：Created in 21:37 2019-06-08
 * @ Description：线程上下文，获取每个tracer的耗时情况,threadLocal实现
 */
public class ContextHolder {

    // 存储一个线程内的请求所有的堆栈信息
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    /**
     * 获取信息
     * @return
     */
    public static String get(){
        return THREAD_LOCAL.get();
    }


    public static void set(String str){
        String o = THREAD_LOCAL.get();
        if(o == null){
            o = "";
        }
        //拼接
        THREAD_LOCAL.set(o + str);
    }
}
