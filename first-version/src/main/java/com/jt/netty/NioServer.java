package com.jt.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: jingteng
 * @date: 2020/5/14 21:01
 */
public class NioServer {
    public static void main(String[] args) throws Exception {

        //创建ServerSocketChannel 和 ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7331);
        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //得到Selector对象
        Selector selector = Selector.open();

        //将ServerSocketChannel注册到Selector，注册事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true){

            //等待一秒，如果没有事件发生，那么返回
            if (selector.select(1000) == 0){
                System.out.println("服务器等待了1秒，没有发现需要执行的事件");
                continue;
            }

            //如果返回的 > 0，就获取相关的SelectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //通过SelectionKey反向获取通道
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //通过判断通道注册时的事件，分别进行处理
                if (key.isAcceptable()){//事件为OP_ACCEPT，有新的客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将socketChannel设置为一个非阻塞的
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功，生成了一个socketChannel : " + socketChannel.hashCode());
                    //简单处理：这里假设注册的连接是读的请求：将生成的socketChannel注册到selector，关注事件为 OP_READ，同时给一个Buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }
                if (key.isReadable()){//事件为OP_READ，读数据
                    //通过SelectionKey反向获取通道
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取该通道关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("从客户端读取数据： " + new String(buffer.array()));

                }

                //手动从集合中删除当前的SelectionKey，防止重复操作
                iterator.remove();

            }

        }

    }
}
