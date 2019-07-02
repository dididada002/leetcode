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
        int[] nums1 = new int[]{3};
        int[] nums2 = new int[]{-2,-1};
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
        int index = 0;//记录当前需要移动指针的数组是1还是2,如果移动数组1的下表指针，那么将index赋值为1，数组2亦然
        if (length1 == 0){
            //数组1为空
            if ((length1+length2) % 2 == 0){//总长度是偶数
                result1 = nums2[length2/2 - 1 ];
                result2 = nums2[length2/2];
            }else {
                result2 = nums2[length2/2];
            }
        }else if (length2 == 0){
            //数组2为空
            if ((length1+length2) % 2 == 0){//总长度是偶数
                result1 = nums1[length1/2 - 1];
                result2 = nums1[length1/2];
            }else {
                result2 = nums1[length1/2];
            }
        }else {
            //数组1和数组2都不为空
            while ((index1+index2)<((length1+length2)/2)){
                //找寻中位数：比较当前两个数组的下标所指的元素，比较小的，将指针数+1
                int int1 = nums1[index1];
                int int2 = nums2[index2];
                //对4个数进行排序，并将最大的两个数给 result1 和 result2，并判断是将哪个指针向后移一位-------这是错误的思路
                //纠正：不是将两个最大的数给 result1 和 result2，应该是给数组排序，找出int1和int2谁应该排在result2后面
                if (int1 < int2){
                    if (index == 1){
                        //说明数组1是两个连续的数，并且比数组2的当前元素小
                        result1 = result2;
                        result2 = int1;
                    }else if (index == 2){
                        //说明数组2移动下标之后的值比当前数组1的值大
                        result1 = result2;
                        result2 = int2;
                    }else {
                        result1 = int1;
                        result2 = int2;
                    }
                    //判断当前指针的移动：
                    if (index1 == length1-1){
                        //数组1已经指到尽头了：只能移动数组2，且数组1不能再参与比较
                        index2 ++;
                        index = 2;
                    }else {
                        index1 ++;
                        index = 1;
                    }
                }else {
                    if (index == 2){
                        //说明数组2是两个连续的数，并且比数组1的当前元素小
                        result1 = result2;
                        result2 = int2;
                    }else if (index == 1){
                        //说明数组1移动下标之后的值比当前数组2的值大
                        result1 = result2;
                        result2 = int1;
                    }else {
                        result1 = int2;
                        result2 = int1;
                    }
                    //判断当前指针的移动：
                    if (index2 == length2-1){
                        //数组1已经指到尽头了：只能移动数组2，且数组1不能再参与比较
                        index1 ++;
                        index = 1;
                    }else {
                        index2 ++;
                        index = 2;
                    }
                }
            }
        }
        if ((length1+length2) % 2 == 0){//总长度是偶数
            //返回大数和小数的平均数
            double d1 = result1;
            double d2 = result2;
            return (d1 + d2)/2;
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
