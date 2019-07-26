package com.sz.demo.sort.example;

/**
 * @program: demo
 * @description: 堆排序  选择排序 快速定位索引元素 大根堆  小根堆 完全二叉树
 * @author: jie fu
 * @create: 2019-07-24 14:36
 */
public class HeapSort {

    /**
     *  建堆
     * @param arrays    完全二叉树
     * @param currentRootNode   父节点位置
     * @param size  节点总数
     */
    public void heapify(int [] arrays,int currentRootNode,int size){

        if(currentRootNode<size){

            int left=2*currentRootNode+1;
            int right=2*currentRootNode+2;

            //当前父节点元素最大
            int max=currentRootNode;

            if(left<size){
                //如果大于根元素
                if(arrays[left]>arrays[max]){
                    max=left;
                }
            }

            if(right<size){
                //如果大于根元素
                if(arrays[right]>arrays[max]){
                    max=right;
                }
            }

            //如果最大的不是根元素就交换
            if(max != currentRootNode){
                int temp=arrays[max];
                arrays[max]=arrays[currentRootNode];
                arrays[currentRootNode]=temp;

                heapify(arrays,max,size);
            }
        }

    }


    /**
     * 完成一次建堆，最大堆在堆的顶部（根节点）
     * @param arrays
     * @param size
     */
    public void maxHeadIfy(int[] arrays,int size){

        //从数组的尾部开始，知道第一个元素
        for (int i=arrays.length-1;i>=0;i--){
            heapify(arrays,i,size);
        }
    }

    public void test(int[] arrays,int size){

        for(int i=0;i<arrays.length;i++){

            maxHeadIfy(arrays,arrays.length-i);

            int temp=arrays[0];
            arrays[0]=arrays[(arrays.length-1)-i];
            arrays[(arrays.length-1)-i]=temp;
        }

    }



    public static void main(String[] args) {

        int [] arrays={5,3,7,6,4};
        int size = arrays.length;
        new HeapSort().test(arrays,size);
        for(int i=0;i<arrays.length;i++){
            System.out.println(arrays[i]);
        }
    }
}
