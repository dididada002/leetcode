package com.jt.netty;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: jingteng
 * @date: 2020/5/14 17:32
 * 拷贝文件：读 - 写
 */
public class NioFileChanner03 {
    public static void main(String[] args) throws IOException {

        //创建一个输入流
        File file = new File("C:\\Users\\17610\\Documents\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过输出流获取对应的 FileChanner（真实类型是FileChannerImpl）
        FileChannel fileChannelInput = fileInputStream.getChannel();

        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\17610\\Documents\\file02.txt");
        FileChannel fileChannelOutput = fileOutputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //通道的数据读入缓冲区，并且同时写入另一个通道
        while (true){

            //清空buffer
            byteBuffer.clear();

            int read = fileChannelInput.read(byteBuffer);
            if (read == -1){
                break;
            }
            //写入到另一个通道中
            byteBuffer.flip();
            fileChannelOutput.write(byteBuffer);
        }

        //读取缓冲区的数据
        System.out.println(new String(byteBuffer.array()));

        fileChannelInput.close();
        fileInputStream.close();

        fileChannelOutput.close();
        fileOutputStream.close();


    }
}
