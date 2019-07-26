package com.sz.demo.thread.parent;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-22 14:55
 */
public class Student extends Person {


    @Override
    public void eat(int a) {
        super.eat(1);
        System.out.println("吃鸡腿");
    }

    public void eat() {

        System.out.println("吃鸡翅");
    }



    public static void main(String[] args) {
        new Student().eat(1);
    }
}
