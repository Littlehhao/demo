package com.sz.demo.thread.threadLocal;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-08 15:45
 */
public class test {

    public static void main(String[] args) throws InterruptedException {

        MyThreadLocal myThreadLocal=new MyThreadLocal();

        TranSef tranSef=new TranSef(myThreadLocal);

        Thread thread1=new Thread(tranSef);
        thread1.start();

        Thread thread2=new Thread(tranSef);
        thread2.start();

        thread1.join();
        thread2.join();

        //输出thread初始值
        System.out.println(myThreadLocal.get());


    }
}
