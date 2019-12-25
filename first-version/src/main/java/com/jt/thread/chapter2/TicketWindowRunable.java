package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/23 17:07
 */
public class TicketWindowRunable implements Runnable {

    private int index = 1;

    private final static int MAX = 200;

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println(Thread.currentThread()+"出票成功，当前的号码是： " + (index ++));
        }
    }
}
