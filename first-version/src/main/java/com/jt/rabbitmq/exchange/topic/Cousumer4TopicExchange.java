package com.jt.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: jingteng
 * @date: 2020/4/21 21:38
 */
public class Cousumer4TopicExchange {
    public static void main(String[] args)  throws IOException, TimeoutException, InterruptedException  {
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

        //声明
        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
//        String routeingKey = "user.#";
        String routeingKey = "user.*";


        //声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明一个队列
        channel.queueDeclare(queueName,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(queueName,exchangeName,routeingKey);

        //创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        //设置channel,参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName,true,queueingConsumer);

        //获取消息
        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            byte[] body = delivery.getBody();
            String msg = new String(body);
            System.out.println("消费端消息： " + msg);
        }

    }
}
