package com.jt.rabbitmq.exchange.fanout;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: jingteng
 * @date: 2020/4/21 21:53
 */
public class Producer4FanoutExchange {
    public static void main(String[] args)   throws IOException, TimeoutException {
        //通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test_fanout_exchange";

        //发送
        for (int i = 0; i < 10; i++) {
            String msg1 = "Hello RabbitMQ 4 Fanout Exchange Message ..." + i;
            channel.basicPublish(exchangeName,"",null,msg1.getBytes());
        }
        channel.close();
        connection.close();
    }
}
