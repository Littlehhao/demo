package com.sz.demo.sort.tree;

import org.springframework.util.CollectionUtils;
import org.springframework.util.CompositeIterator;

import java.util.*;

/**
 * @program: demo
 * @description: 树形结构转换
 * @author: jie fu
 * @create: 2019-07-25 15:08
 */
public class TreeUtils {


    /**
     *  树形结构转换
     * @param list
     * @param
     * @return
     */
    public static  Collection<Tree> convertTree(List<Tree> list){

        Map<Integer,Tree> map=new HashMap<>();

        for (Tree tree : list){
            map.put(tree.getId(),tree);
        }

        TreeMap<Integer, Tree> resultMap = new TreeMap<>();
        for (Integer id : map.keySet()){

            Tree tree = map.get(id);
            Integer pid = tree.getPid();
            Tree parent = map.get(pid);

            if(null != parent){

                Collection<Tree> childList = parent.getChildren();
                if(CollectionUtils.isEmpty(childList)){

                    //优先队列 排序
                    PriorityQueue<Tree> queue = new PriorityQueue<>(new Comparator<Tree>() {

                        @Override
                        public int compare(Tree o1, Tree o2) {
                            return o1.getOrderNum() - o2.getOrderNum();
                        }
                    });
                    parent.setChildren(queue);
                }
                parent.getChildren().add(tree);
            }else{
                resultMap.put(id,tree);
            }
        }

        return resultMap.values();
    }
}
