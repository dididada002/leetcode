package com.jt.thread.chapter7;

import java.util.stream.Stream;

/**
 * @author: jingteng
 * @date: 2020/4/16 19:45
 */
public class ProduceConsumerV2 {
    private int i = 0;
    final private Object LOCK = new Object();
    private volatile boolean isProduced = false;
    public void produce(String name){
        System.out.println(name + "我开始生产了！");
        synchronized (LOCK){
            System.out.println(name + "我拿到锁了！");
            while (isProduced){
                try {
                    System.out.println(name + "我要开始阻塞了！");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println(name +"生产了 "+i+"个了");
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    public void consume(String name){
        System.out.println(name + "我开始消费了！");
        synchronized (LOCK){
            System.out.println(name + "我拿到锁了！");
            while (!isProduced){
                try {
                    System.out.println(name + "我要开始阻塞了！");
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name +"消费了 "+i+"个了");
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    public static void main(String[] args) {
        ProduceConsumerV2 pc = new ProduceConsumerV2();
        Stream.of("P1","P2").forEach(n ->{
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        pc.produce(n);
                    }
                }
            }.start();
        });
        Stream.of("C1","C2").forEach(n ->{
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        pc.consume(n);
                    }
                }
            }.start();
        });
    }
}
