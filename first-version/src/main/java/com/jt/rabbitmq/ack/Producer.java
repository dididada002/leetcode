package com.jt.rabbitmq.ack;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jingteng
 * @date: 2020/6/3 21:11
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test_ack_exchange";
        String routeingKey = "ack.test";

        //发送
        String msg = "Hello RabbitMQ 4 ACK Exchange Message ...";
        for (int i = 0; i < 5; i++) {
            Map<String, Object> headers = new HashMap<>();
            headers.put("num",i);
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .headers(headers)
                    .build();


            channel.basicPublish(exchangeName,routeingKey,properties,(msg + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
