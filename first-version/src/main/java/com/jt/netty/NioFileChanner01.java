package com.jt.netty;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: jingteng
 * @date: 2020/5/14 17:32
 */
public class NioFileChanner01 {
    public static void main(String[] args) throws IOException {
        String str = "hello,channer";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\17610\\Documents\\file01.txt");

        //通过输出流获取对应的 FileChanner（真实类型是FileChannerImpl）
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //bytebuffer写数据
        byteBuffer.put(str.getBytes());

        //缓冲区的数据写入通道
        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();


    }
}
