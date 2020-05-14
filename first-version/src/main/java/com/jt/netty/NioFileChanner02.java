package com.jt.netty;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: jingteng
 * @date: 2020/5/14 17:32
 * 输入流
 */
public class NioFileChanner02 {
    public static void main(String[] args) throws IOException {

        //创建一个输入流
        File file = new File("C:\\Users\\17610\\Documents\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过输出流获取对应的 FileChanner（真实类型是FileChannerImpl）
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //通道的数据读入缓冲区
        fileChannel.read(byteBuffer);

        //读取缓冲区的数据
        System.out.println(new String(byteBuffer.array()));

        fileChannel.close();
        fileInputStream.close();


    }
}
