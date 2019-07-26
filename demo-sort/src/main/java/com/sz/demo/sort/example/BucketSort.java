package com.sz.demo.sort.example;

import java.util.*;

/**
 * @program: demo
 * @description: 桶排序
 * @author: jie fu
 * @create: 2019-07-25 11:34
 */
public class BucketSort {

/*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/


    /**
     * 桶排序
     * @param arr
     */
    public static void bucketSortNew(int[] arr){

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //桶数
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<LinkedList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new LinkedList<Integer>());
        }

        //将每个元素放入桶  这里根据前面计算桶数方法计算arr[i]应该放在哪个位置
        for(int i = 0; i < arr.length; i++){
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        //对每个桶进行排序
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }

        int index=0;
        for (LinkedList<Integer> bucket:bucketArr){
            for (Integer data:bucket){
                arr[index++]=data;
            }
        }

    }





    public static void main(String[] args) {

        int [] array={20,4,5,2,3,1,7,8,0,6};

        bucketSortNew(array);

        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
