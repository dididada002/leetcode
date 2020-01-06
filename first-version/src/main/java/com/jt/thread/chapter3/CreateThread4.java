package com.jt.thread.chapter3;

/**
 * @author: jingteng
 * @date: 2019/12/25 17:47
 */
public class CreateThread4 {

    private static int counter = 1;

    public static void main(String[] args) {

        //指定栈帧的构造方法
        Thread thread = new Thread(null,new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                }catch (Error error){
                    System.out.println(counter);
                }
            }

            private void add(int i){
                counter ++;
                add(i +1);
            }
        },"Test",1 << 24);
        thread.start();
    }
}
