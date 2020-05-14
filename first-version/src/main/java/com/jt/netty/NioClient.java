package com.jt.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: jingteng
 * @date: 2020/5/14 21:26
 */
public class NioClient {
    public static void main(String[] args) throws Exception{
        //创建一个连接通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //提供连接的地址和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",7331);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("连接未完成，因为是非阻塞的，所以连接的过程可以做其他的事情");
            }
        }
        //连接成功
        String str = "hello,jingteng。";
        //创建buffer
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        //发送数据，将buffer写入channel
        socketChannel.write(buffer);
        System.in.read();


    }
}
