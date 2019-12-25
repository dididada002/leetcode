package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/23 16:52
 */
public class TicketWindow extends Thread {

    private final static int MAX = 200;

    private static int index = 1;

    private final String name;

    public TicketWindow(String name) {
        this.name = name;
    }


    @Override
    public void run(){
        while (index <= MAX){
            System.out.println(name+"出票成功，当前的号码是： " + (index ++));
        }
    }
}
