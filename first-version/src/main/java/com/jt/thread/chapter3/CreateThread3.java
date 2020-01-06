package com.jt.thread.chapter3;

/**
 * @author: jingteng
 * @date: 2019/12/25 17:47
 */
public class CreateThread3 {

    private static int counter =0;

    public static void main(String[] args) {
        try {
            add(0);
        }catch (Error error){
            error.printStackTrace();
            System.out.println(counter);
        }
    }

    private static void add(int i){
        counter++;
        add(i+1);
    }

    //java.lang.StackOverflowError
    //线程中的stacksize指数，是允许线程不断递归，直到抛出错误StackOverflowError
}
