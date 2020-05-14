package com.jt.netty;

import java.nio.IntBuffer;

/**
 * @author: jingteng
 * @date: 2020/5/14 17:07
 */
public class NioBufferTest {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //写
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);

        }
        //读
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
