package com.jt.thread.pool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: jingteng
 * @date: 2020/4/28 20:38
 */
public class MyThreadPool {
    //任务队列
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());
    //当前线程数量
    private int num;
    //核心线程数量
    private int corePoolSize;
    //最大线程数量
    private int maxSize;
    //任务队列的长度
    private int workSize;

    public MyThreadPool(int corePoolSize, int maxSize, int workSize) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    //提交任务
    public void submit(Runnable runnable){
        if (tasks.size() >= workSize){
            //拒绝策略
            System.out.println("任务：" + runnable + "被丢弃了。。。");

        }else {
            tasks.add(runnable);
            execTask(runnable);
        }
    }

    //执行任务
    private void execTask(Runnable runnable) {
        if (num < corePoolSize){//当前线程数量小于核心线程数量
            new MyWorker("核心线程" + num,tasks).start();
            num ++;

        }else if (num < maxSize){//当前线程数量小于最大线程数量
            new MyWorker("核心线程" + num,tasks).start();
            num ++;

        }else {
            System.out.println("任务：" + runnable +"被缓存起来了。。。");
        }
    }

}

















