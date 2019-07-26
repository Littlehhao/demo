package com.sz.demo.sort.example;

/**
 * @program: demo
 * @description: 归并排序 时间复杂度为 n*log2n
 *      归并排序基于分治法，将待排序的元素序列分成两个长度相同的子序列。为每个子序列排序，最后再将子序列归并，合并两个子序列。
 *
 *      9,6,8,5,3,2 首先需要两个指针，p1、p2 分别指向 left,(left+right)/2+1。还需要一个真实改变的索引位置k=left
 *      递归子序列依次排序 最后复制回原数组
 *
 *
 * @author: jie fu
 * @create: 2019-07-24 17:35
 */
public class MergeSort {

    public void mergeSort(int [] a,int start,int end){

        if(start<end){

            int mid=(start+end)/2;
            mergeSort(a,start,mid);//左侧递归排序
            mergeSort(a,mid+1,end);//右侧递归排序
            merge(a,start,mid,end);//归并

        }

    }


    /**
     *  归并排序
     * @param a
     * @param left
     * @param mid
     * @param right
     */
    public void merge(int [] a,int left,int mid,int right){

        int [] tmp=new int[a.length];

        int p1=left,p2=mid+1,k=left;

        while (p1 <= mid && p2 <= right){

            if(a[p1] <= a[p2]){
                tmp[k++]=a[p1++]; //这里注意 a++ 先赋值 再+  ++a 先+再赋值
            }else{
                tmp[k++]=a[p2++];
            }
        }

        while (p1 <= mid) tmp[k++]=a[p1++];
        while (p2<right) tmp[k++]=a[p2++];


        for(int i=left;i<=right;i++){
            a[i]=tmp[i];
        }

    }

    public static void main(String[] args) {

        int [] a={9,3,4,7,1,2};
        int end=a.length;
        new MergeSort().mergeSort(a,0,end);
    }
}
