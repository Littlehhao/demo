package com.sz.demo.thread.threadLocal;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-08 15:42
 */
public class TranSef implements Runnable{

    MyThreadLocal myThreadLocal;



    public TranSef(MyThreadLocal myThreadLocal) {
        this.myThreadLocal = myThreadLocal;
    }

    @Override
    public void run() {

        for (int i=0;i<10;i++){

            myThreadLocal.set();

            //输出不同线程值
            System.out.println(Thread.currentThread()+"线程值"+myThreadLocal.get());
        }
    }
}
