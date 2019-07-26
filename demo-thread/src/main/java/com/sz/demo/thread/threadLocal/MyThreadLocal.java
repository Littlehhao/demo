package com.sz.demo.thread.threadLocal;

/**
 * @program: demo
 * @description: 本地线程，用于将私有线程和该线程存放的副本对象做一个映射，
 *              各个线程之间的变量互不干扰。在高并发的情况下，可实现无状态的调用
 *
 *              底层实现就是threadLocalMap,threadLocal就是key,entity是value
 * @author: jie fu
 * @create: 2019-07-08 15:20
 */
public class MyThreadLocal {

    ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){

        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public Integer get(){
        return threadLocal.get();
    }



    public void set(){
        threadLocal.set(threadLocal.get()+10);
    }


}
