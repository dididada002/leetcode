package com.jt.leetcode;

/**
 * @author: jingteng
 * @date: 2019/6/24 9:37
 */
public class Day20190624Code {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long freeMemoryBegin = runtime.freeMemory();
        long begain = System.currentTimeMillis();
        //题目4-开始
        int[] nums1 = new int[]{1,8,15,59,174,178};
        int[] nums2 = new int[]{2,5,15,16,17,25,60,257};
        double result = findMedianSortedArrays(nums1,nums2);
        //题目4-结束
        long end = System.currentTimeMillis();
        long freeMemoryEnd = runtime.freeMemory();
        System.out.println("总共耗时： "+(end-begain)+" 毫秒");
        System.out.println("总共耗内存： "+(freeMemoryBegin-freeMemoryEnd));
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //通过手动创建两个指针，因为是有序数组，那么在找中位数肯定是让指针逐个往后移
        //假设这两个数组，都是从小到大排序的
        int index1 = 0;//数组1的指针下标
        int index2 = 0;//数组2的指针下表
        int length1 = nums1.length;
        int length2 = nums2.length;
        int result1 = 0;//较小的数
        int result2 = 0;//较大的数
        while ((index1+index2)<((length1+length2)/2)){
            //找寻中位数：比较当前两个数组的下标所指的元素，比较小的，将指针数+1
            int int1 = nums1[index1];
            int int2 = nums2[index2];
            //对4个数进行排序，并将最大的两个数给 result1 和 result2，并判断是将哪个指针向后移一位
            if (int1 < int2){
                if (int1 > result2){//这种情况不可能发生
                    result1 = int1;
                    result2 = int2;
                }else if (int2 =< result2 && int2 >= result1){
                result1 = int1;
                result2 = int2;
                index1 ++;
            }else {
                result2 = int1;
                result1 = int2;
                index2 ++;
            }
        }
        if ((length1+length2) % 2 == 0){//总长度是偶数
            //返回大数和小数的平均数
            return (result1+result2)/2;
        }else {
            return result2;
        }
    }
/*
编号：4
地址：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/


    给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

    请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

    你可以假设 nums1 和 nums2 不会同时为空。

    示例 1:

    nums1 = [1, 3]
    nums2 = [2]

    则中位数是 2.0
    示例 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    则中位数是 (2 + 3)/2 = 2.5
*/

}
