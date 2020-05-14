package com.jt.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author: jingteng
 * @date: 2020/5/14 20:00
 * Buffer的聚合和分散
 */
public class NioScatteringAndGatheringTest {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7331);

        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLengthMax = 8;
        while (true){
            int byteRead = 0;
            while (byteRead < messageLengthMax){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead = " + byteRead);
                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "position = " + buffer.position() + " , limit = " + buffer.limit())
                        .forEach(System.out::println);
            }

            int byteWrite = 0;
            while (byteWrite < messageLengthMax){
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }

            // buffer进行clear
            Arrays.asList(byteBuffers)
                    .forEach(buffer -> buffer.clear());

            System.out.println("byteRead + " + byteRead + " , byteWrite = " + " , messageLengthMax = " + messageLengthMax);

        }

    }
}















