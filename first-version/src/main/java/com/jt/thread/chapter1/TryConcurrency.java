package com.jt.thread.chapter1;

/**
 * @author: jingteng
 * @date: 2019/12/19 14:03
 */
public class TryConcurrency {

    public static void main(String[] args) {

        Thread thread1 = new Thread("read-thread") {
            @Override
            public void run() {
                readFromDataBase();
            }
        };
        thread1.start();
        Thread thread2 = new Thread("write-thread") {
            @Override
            public void run() {
                writeDataToFile();
            }
        };
        thread2.start();

    }

    private static void readFromDataBase(){
        try {
            println("开始从数据库进行读取");
            Thread.sleep(1000* 30L);
            println("数据库进行读取完成，开始处理数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("处理数据结束");
    }

    private static void writeDataToFile(){
        try {
            println("开始将数据写入文件");
            Thread.sleep(1000* 30L);
            println("数据写入文件完成，开始处理数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("写入处理数据结束");
    }

    private static void println(String message){
        System.out.println(message);
    }
}
