package com.jt.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: jingteng
 * @date: 2019/6/19 14:19
 */
public class Day20190619Code {
    /*
    编号：2
    题目来源：https://leetcode-cn.com/problems/add-two-numbers/
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例：

    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
    */
    /**
     * 解题：卧槽，看不懂题目，下一题
     * */

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long freeMemoryBegin = runtime.freeMemory();
        long begain = System.currentTimeMillis();
        //题目3-开始
        String test = "pwwkew";
        int length = leetcode3(test);
        System.out.println(test+" 中最长不重复的字串长度为： "+length+" 位");
        //题目3-结束

        long end = System.currentTimeMillis();
        long freeMemoryEnd = runtime.freeMemory();
        System.out.println("总共耗时： "+(end-begain)+" 毫秒");
        System.out.println("总共耗内存： "+(freeMemoryBegin-freeMemoryEnd));

    }


/*
编号： 3
题目来源：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    示例 1:

    输入: "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    示例 2:

    输入: "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:

    输入: "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
*/

    private static int leetcode3(String test) {
        int resultSize = 0;//用来存储最长的子串长度
        int resultSizeBefore = 0;//用来存储遇到重复元素之后重新计数的子串长度
        List<String> result = Lists.newLinkedList();//用来存储当前无重复字符的字串
        if (test != null){
            for (int i = 0 ; i <= test.length()-1 ;i++ ){
                String testi = test.substring(i, i + 1);
                /**
                 * 先判断结果字串中是否有当前下标的元素
                 *      如果有
                 *          那么从上一个重复元素开始将之前的集合元素删除
                 *          将 resultSize 进行重置为：resultSize和resultSizeBefore的较大值
                 *          并将 resultSizeBefore 进行重置未当前集合的长度
             *          如果没有重复
                 *          那么将元素加入当前的集合中
                 *          并将 resultSizeBefore +1
                 *
                 * */
                if (result.contains(testi)){
                    //如果有重复
                    result = result.subList(result.indexOf(testi)+1, result.size());
                    resultSize = resultSizeBefore > resultSize ? resultSizeBefore :resultSize;
                }else {
                    //如果没有重复:不做任何特殊操作
                }
                result.add(testi);
                resultSizeBefore = result.size();
            }
            resultSize = resultSizeBefore > resultSize ? resultSizeBefore :resultSize;
            return resultSize;
        }
        return 0;
    }

}
