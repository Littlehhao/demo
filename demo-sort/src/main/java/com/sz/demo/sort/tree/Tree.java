package com.sz.demo.sort.tree;

import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-25 15:13
 */
@Data
public class Tree {

    private Integer id;

    private Integer pid;

    //排序
    private Integer orderNum;

    private Collection<Tree> children;
}
