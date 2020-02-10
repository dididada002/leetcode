package com.jt.thread.chapter6;

/**
 * @author: jingteng
 * @date: 2020/2/10 16:37
 * 优雅地结束线程：通过线程的interrupt方法，设置一个标志
 *                  在线程的逻辑实现处，interrupted 进行这个标志的检测，如果检测到需要结束，那么在代码中进行手动结束处理
 */
public class ThreadCloseGraceful2 {
    private static class  Worker extends Thread{
        private volatile boolean start = true;
        @Override
        public void run(){
            while (true){
                if (Thread.interrupted()){//检测线程是否要进行结束
                    break;
                }
            }
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
        worker.interrupt();//设置线程打断的标志位
    }
}
