package com.sz.demo.sort.example;

/**
 * @program: demo
 * @description: 选择排序
 * @author: jie fu
 * @create: 2019-07-24 15:48
 */
public class SelectSort {

    /**
     *  选择排序 时间复杂度为 o(n^2) 以 a[0] 为基准，嵌套循环 找到数组内小于a[0] 则赋值变量k
     * @param a
     * @param n
     */
    public void sort(int [] a,int n){

        int key,tmp;

        for(int i=0;i<n;i++){

           int k=i;

           for(int j=i+1;j<n;j++){

               if(a[k]>a[j]){
                   k=j;
               }
           }

           if(k!=i){

               tmp=a[i];
               a[i]=a[k];
               a[k]=tmp;

           }

        }
    }

    /**
     *  二元选择排序 将循环缩小一半  一次循环找到最大值 最小值
     * @param a
     * @param n
     */
    public void selectSort(int [] a,int n){

        int i,j,min,max,tmp;

        for(i=1;i<=n/2;i++){

            min=i;max=i;

            for(j=i+1;j<=n-i;j++){

                if(a[j]>a[max]){

                    max=j;
                    continue;
                }

                if(a[j]<a[min]){

                    min = j;

                }

            }
            tmp = a[i-1]; a[i-1] = a[min]; a[min] = tmp;
            tmp = a[n-i]; a[n-i] = a[max]; a[max] = tmp;

        }



    }

    public static void main(String[] args) {

        int [] arrays={5,3,7,6,4};
        int size = arrays.length;
        new SelectSort().selectSort(arrays,size);
        for(int i=0;i<arrays.length;i++){
            System.out.println(arrays[i]);
        }
    }
}
