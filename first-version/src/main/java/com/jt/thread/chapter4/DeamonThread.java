package com.jt.thread.chapter4;

/**
 * @author: jingteng
 * @date: 2019/12/25 17:47
 * 守护线程
 */
public class DeamonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(100000);
                    System.out.println(Thread.currentThread().getName() + " done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5_1000);
        System.out.println(Thread.currentThread().getName());
    }
}
