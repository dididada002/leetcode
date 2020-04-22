package com.jt.rabbitmq.exchange.topic;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: jingteng
 * @date: 2020/4/21 21:38
 */
public class Producer4TopicExchange {
    public static void main(String[] args)   throws IOException, TimeoutException {
        //通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test_topic_exchange";
        String routeingKey1 = "user.save";
        String routeingKey2 = "user.update";
        String routeingKey3 = "user.delete.abc";

        //发送
        String msg1 = "Hello RabbitMQ 4 Topic Exchange Message ...save";
        String msg2 = "Hello RabbitMQ 4 Topic Exchange Message ...update";
        String msg3 = "Hello RabbitMQ 4 Topic Exchange Message ...delete.abc";
        channel.basicPublish(exchangeName,routeingKey1,null,msg1.getBytes());
        channel.basicPublish(exchangeName,routeingKey2,null,msg2.getBytes());
        channel.basicPublish(exchangeName,routeingKey3,null,msg3.getBytes());
        channel.close();
        connection.close();
    }
}
