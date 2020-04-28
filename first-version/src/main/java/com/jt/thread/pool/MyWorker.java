package com.jt.thread.pool;

import java.util.List;

/**
 * @author: jingteng
 * @date: 2020/4/28 20:32
 * 线程类
 */
public class MyWorker extends Thread{

    private String name;
    private List<Runnable> tasks;

    public MyWorker(String name,List<Runnable> tasks){
        super(name);
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (tasks.size() > 0){
            Runnable r = tasks.remove(0);
            r.run();
        }
    }
}
