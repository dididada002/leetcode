package com.jt.rabbitmq.exchange.derect;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: jingteng
 * @date: 2020/4/20 23:52
 */
public class Producer4DirectExchange {
    public static void main(String[] args)  throws IOException, TimeoutException {
        //通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test_direct_exchange";
        String routeingKey = "test.direct";

        //发送
        String msg = "Hello RabbitMQ 4 Direct Exchange Message ...";
        channel.basicPublish(exchangeName,routeingKey,null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
