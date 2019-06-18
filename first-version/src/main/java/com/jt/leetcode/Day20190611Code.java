package com.jt.leetcode;

/**
 * @author: jingteng
 * @date: 2019/6/11 14:59
 */
public class Day20190611Code {
/*
    题目：
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

    你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

    给定 nums = [2, 7, 11, 15], target = 9

    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
*/

    public static void main(String[] args) {
        int target = 360;
        int [] nums = {2,7,11,15,7,123,188,23,176,1984,12321,184};
        int [] result = new int[2];

        long begain = System.currentTimeMillis();
        result = getResult(target,nums,result);

        long end = System.currentTimeMillis();
        System.out.println("总共耗时： "+(end-begain)+" 毫秒");
        if (result != null){
            System.out.println("符合条件的下标为： "+result[0]+" ,第二位是： "+result[1]);
        }else {
            System.out.println("不满足条件");
        }
    }

    private static int[] getResult(int target, int[] nums, int[] result) {
        /**
         * 思路：遍历数组，取两数和，第二个数必须在第二个数后边
         * 时间复杂度：
         * 空间复杂度：
         * */
        for (int i = 0 ; i <= nums.length-1 ; i ++){
            int ai = nums[i];
            for (int j = i+1; j <= nums.length-1 ; j++){
                int aj = nums[j];
                if ((ai + aj) == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;

    }

}
