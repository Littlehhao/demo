package com.sz.demo.sort.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-29 10:32
 */
public class ArraysList {

    public static void main(String[] args) {
        //数组转 list  不要用Arrays.asList() 转换的list 底层还是数组 并非list
        ArrayList<Object> list1 = new ArrayList<>();
        Integer[] arrays=new Integer[]{1,2,3};
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(arrays));
        List<Integer> list = Arrays.asList(arrays);
        list1.add(1);
        list2.add(1);
    }


}
