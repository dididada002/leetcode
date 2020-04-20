package com.jt.rabbitmq.test;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: jingteng
 * @date: 2020/3/31 21:16
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
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

        //声明一个队列
        channel.queueDeclare("test001",true,false,false,null);

        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //设置channel
        channel.basicConsume("test001",true,queueingConsumer);

        //获取消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            byte[] body = delivery.getBody();
            String msg = new String(body);
            System.out.println("消费端消息： " + msg);
            Envelope envelope = delivery.getEnvelope();
        }

    }
}
