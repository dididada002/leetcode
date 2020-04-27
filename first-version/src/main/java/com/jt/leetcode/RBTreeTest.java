package com.jt.leetcode;

import java.util.Scanner;

/**
 * @author: jingteng
 * @date: 2020/4/27 22:26
 */
public class RBTreeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedBlackTree<String, Object> rbt = new RedBlackTree();
        while (true){
            System.out.println("请输入 key:");
            String key = scanner.next();
            System.out.println();
            rbt.insert(key,null);
            rbt.inOrderPrint();
        }
    }
}
