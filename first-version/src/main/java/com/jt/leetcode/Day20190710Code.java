package com.jt.leetcode;

/**
 * @author: jingteng
 * @date: 2019/7/10 16:28
 */
public class Day20190710Code {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long freeMemoryBegin = runtime.freeMemory();
        long begain = System.currentTimeMillis();
        //题目6-开始
        String result = convert("",110);
        System.out.println(result);
        //题目6-结束
        long end = System.currentTimeMillis();
        long freeMemoryEnd = runtime.freeMemory();
        System.out.println("总共耗时： "+(end-begain)+" 毫秒");
        System.out.println("总共耗内存： "+(freeMemoryBegin-freeMemoryEnd));
    }

    public static String convert(String s, int numRows) {
        String [] resultList = new String[numRows];
        if (s != null && s.length() > 0){
            String result = "";
            for (int i = 1 ; i<= s.length() ; i ++){
                int strNum = 1;
                if (numRows > 1){
                    strNum = i % (numRows * 2 - 2);//一个循环中的第几个字符串
                }
                //将循环中的下表对应到数组的下表
                if (strNum == 0){
                    strNum = numRows * 2 - 2;
                }
                if (strNum > numRows){
                    //反序
                    strNum = numRows - (strNum - numRows);
                }



                String resultSpli = resultList[strNum - 1];
                if (resultSpli == null) resultSpli = "";
                resultList[strNum - 1] = resultSpli + s.charAt(i-1);
            }
            for (int i = 0 ; i<resultList.length; i++){
                if (resultList[i] != null){
                    String trim = resultList[i].trim();
                    result = result + trim;
                }
            }
            return result;
        }
        return s;

    }

    /**
     * 编号 ：6
     *
     * 地址：https://leetcode-cn.com/problems/zigzag-conversion/
     *
     *
     * 状态：待改进
     *
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     *
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     * 示例 1:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     *
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     *
     * L     D     R
     * E   O E   I I
     * E C   I H   N
     * T     S     G
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * */
}
