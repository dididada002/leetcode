package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/23 17:08
 */
public class BankRunnable {
    public static void main(String[] args) {
        final TicketWindowRunable ticketWindowRunable = new TicketWindowRunable();
        Thread thread1 = new Thread(ticketWindowRunable, "1号柜台");
        thread1.start();
        Thread thread2 = new Thread(ticketWindowRunable, "2号柜台");
        thread2.start();
        Thread thread3 = new Thread(ticketWindowRunable, "3号柜台");
        thread3.start();
    }
}
