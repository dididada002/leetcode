package com.jt.thread.chapter4;

import java.util.Optional;

/**
 * @author: jingteng
 * @date: 2020/1/8 11:46
 */
public class ThreadSimple {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("Hello").ifPresent(System.out :: println);
            try {
                Thread.sleep(100_1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t.start();
        t.setPriority(Thread.MAX_PRIORITY);//设置优先级
        Optional.of(t.getName()).ifPresent(System.out :: println);
        Optional.of(t.getId()).ifPresent(System.out :: println);
        Optional.of(t.getPriority()).ifPresent(System.out :: println);
    }
}
