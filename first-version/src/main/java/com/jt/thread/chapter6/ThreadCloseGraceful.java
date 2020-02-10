package com.jt.thread.chapter6;

/**
 * @author: jingteng
 * @date: 2020/2/10 16:37
 * 优雅地结束线程：设置一个开关，标记线程是否继续执行
 */
public class ThreadCloseGraceful {
    private static class  Worker extends Thread{
        private volatile boolean start = true;
        @Override
        public void run(){
            while (start){

            }
        }

        public void shutdown(){
            this.start = false;
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
    }
}
