package com.jt.thread.chapter6;

/**
 * @author: jingteng
 * @date: 2020/2/10 16:37
 * 暴力地结束线程：利用守护线程在执行线程结束的时候必须结束，这一特性
 */
public class ThreadCloseForceService {
    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run(){
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutdowm(long mills){
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if ((System.currentTimeMillis() - currentTime) >= mills){
                System.out.println("任务超时，需要结束！");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断！");
                break;
            }
        }
        finished = false;
    }

}
