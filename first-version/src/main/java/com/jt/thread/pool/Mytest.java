package com.jt.thread.pool;

/**
 * @author: jingteng
 * @date: 2020/4/28 20:53
 */
public class Mytest {

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(2, 4, 20);
        for (int i = 0; i < 30; i++) {
            MyTask myTask = new MyTask(i);
            pool.submit(myTask);
        }
    }

}
