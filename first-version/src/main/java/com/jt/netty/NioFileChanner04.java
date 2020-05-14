package com.jt.netty;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author: jingteng
 * @date: 2020/5/14 17:32
 * 拷贝文件：transferForm
 */
public class NioFileChanner04 {
    public static void main(String[] args) throws IOException {

        //创建一个输入流
        File file = new File("C:\\Users\\17610\\Documents\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过输出流获取对应的 FileChanner（真实类型是FileChannerImpl）
        FileChannel fileChannelInput = fileInputStream.getChannel();

        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\17610\\Documents\\file03.txt");
        FileChannel fileChannelOutput = fileOutputStream.getChannel();

        //使用transForm进行拷贝
        fileChannelOutput.transferFrom(fileChannelInput,0,fileChannelInput.size());


        fileChannelInput.close();
        fileInputStream.close();

        fileChannelOutput.close();
        fileOutputStream.close();


    }
}
