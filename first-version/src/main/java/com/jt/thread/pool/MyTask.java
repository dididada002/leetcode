package com.jt.thread.pool;

/**
 * @author: jingteng
 * @date: 2020/4/28 20:28
 * 任务类
 */
public class MyTask implements Runnable{

    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程：" + name +" 即将执行任务： " + id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程：" + name +" 完成了任务： " + id);

    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id=" + id +
                '}';
    }
}
