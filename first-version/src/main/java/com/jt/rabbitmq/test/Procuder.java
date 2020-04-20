package com.jt.rabbitmq.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: jingteng
 * @date: 2020/3/31 21:16
 */
public class Procuder {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂，并进行配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.244.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");
//        factory.setUsername("jingteng");
//        factory.setPassword("jingteng");

        //通过连接工厂创建连接
        Connection connection = factory.newConnection();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();
        String msg = "Hello RabbitMQ";
        channel.basicPublish("","test001",null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
