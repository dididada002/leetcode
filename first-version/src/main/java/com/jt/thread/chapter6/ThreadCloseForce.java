package com.jt.thread.chapter6;

/**
 * @author: jingteng
 * @date: 2020/2/10 16:37
 * 暴力地结束线程：利用守护线程在执行线程结束的时候必须结束，这一特性
 */
public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadCloseForceService service = new ThreadCloseForceService();
        long start = System.currentTimeMillis();
        service.execute(()->{
            while (true){

            }
        });
        service.shutdowm(10_000);
        long stop = System.currentTimeMillis();
        System.out.println("任务耗时： " + (stop - start));
    }
}
