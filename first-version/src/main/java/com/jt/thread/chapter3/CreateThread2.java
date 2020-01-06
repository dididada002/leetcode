package com.jt.thread.chapter3;

import java.util.Arrays;

/**
 * @author: jingteng
 * @date: 2019/12/25 17:47
 */
public class CreateThread2 {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        //查看当前运行的线程数量
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("当前一共运行的线程总数为： " + threadGroup.activeCount());
        //查看当前运行的线程详情
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
        for (Thread t : threads) {
            System.out.println(t);
        }
    }
}
