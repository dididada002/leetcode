package com.jt.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jingteng
 * @date: 2019/7/11 10:49
 */
public class Day20190711Code {

    /**
     * 待完成：学习关于机器中二进制的存储，以及复习int类型的范围
     * */

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long freeMemoryBegin = runtime.freeMemory();
        long begain = System.currentTimeMillis();
        //题目7-开始
        int reverse = reverse(1463847412);
        int reverse2 = reverseTwo(1463847412);
        System.out.println(reverse);
        System.out.println(reverse2);
        //题目7-结束
        long end = System.currentTimeMillis();
        long freeMemoryEnd = runtime.freeMemory();
        System.out.println("总共耗时： "+(end-begain)+" 毫秒");
        System.out.println("总共耗内存： "+(freeMemoryBegin-freeMemoryEnd));
    }


    /**
     * 利用字符串的解法
     * */
    private static int reverseTwo(int x) {
        int flag = x <0 ? -1:1;
        String xx = String.valueOf(Math.abs(x));
        String s = new StringBuilder(xx).reverse().toString();
        try {
            int result = flag * Integer.parseInt(s);
            return result;
        }catch (Exception e){
            return 0;
        }

    }





    /**
     * 编号：7-整数反转
     *
     * 地址：https://leetcode-cn.com/problems/reverse-integer/
     *
     * 状态：待完成---
     *
     * 备注：此题的坑在于要判断反转后的值，如果反转后溢出了，那么返回0
     *
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * 注意:
     *
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * */

    public static int reverse(int x) {
        //排除范围
        if (x >= 2147483647 || x <= -2147483648){
            return 0;
        }
        boolean isFushu = false;
        if (x < 0){
            isFushu = true;
            x = - x;
        }
        //算出个位、十位、百位的数  ------后期将这里改进为可以计算多位数字
        List<Integer> integers = new ArrayList<>();
        reverseInt(x,integers);

        int size = integers.size();

        int result = 0;

        for (int i = 0 ; i < size; i++){
            int integer = integers.get(i);

            if (result > 214748364 || (result == 214748364 && integer != 1)){
                return 0;
            }
            result = result * 10 + integer;

        }
        if (isFushu){
            result = - result;
        }
        return result;

    }

    private static void reverseInt(int x, List<Integer> integers) {
        int yushu = x % 10;
        int beishu = x / 10;
        integers.add(yushu);
        if (beishu != 0) reverseInt(beishu,integers);
    }
}
















