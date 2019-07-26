package com.sz.demo.sort.example;

import javax.sound.midi.Soundbank;

/**
 * @program: demo
 * @description: 排序算法
 * @author: jie fu
 * @create: 2019-07-24 09:27
 */
public class Sort {

    public static int[] a={3,1,7,6,4};

    public static int len=a.length;


    /**
     *      直接插入排序，嵌套循环
     *      时间复杂度为o(n^2)
     *      定义一个基准，a[1]为key,每次和索引<1 的值比较，若后小于前,则终止循环。否则继续循环 将前一位赋值给后一位。直至i<0 将最小值赋值给a[0 ]
     * @param a
     * @param len
     */
    public void insertSort(int [] a,int len){

        int begin=1;
        int i=0;

        while (begin<len){

            int key=a[begin];
            for(i=begin-1;i>=0;i--){

                if(a[i]<key){    //稳定

                    a[i+1]=key;
                    break;
                }
                a[i+1]=a[i];
            }

            if(i<0){  //当a[0]>a[1] 时 进入
                a[0]=key;
            }
            begin++;
        }
    }

    /**
     *  希尔排序 时间复杂度 o(n^2)
     * @param a
     * @param len
     */
    public void shellSort(int[] a,int len){

        int gap=len;

        while (gap>1){

            gap=gap/3+1;

            for (int i=gap;i<len;i++){

                int key = a[i];
                int start = i - gap;
                while(start >= 0 && key <= a[start])//对当前key进行一趟直接插入排序
                {
                    a[start+gap] = a[start];
                    start -= gap;
                }
                a[start + gap] = key;
            }
        }
    }

    /**
     *  冒泡排序 嵌套循环依次往后找  换碗 时间复杂度o(n^2)
     * @param a
     * @param len
     */
    public void bubbleSort(int [] a,int len){

        int tmp=0;

        if(len<0) return;

        for (int i=0;i<len-1;i++){

            boolean blog=false;

            for(int j=0;j<len-i-1;j++){

                if(a[j]>a[j+1]){
                    tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                    blog=true;
                }

            }

            if(false == blog){
                break;
            }
        }

    }








    public static void main(String[] args) {
        new Sort().insertSort(a,len);

        for(int i=0;i<a.length;i++){

            System.out.println(a[i]);
        }
    }

}
