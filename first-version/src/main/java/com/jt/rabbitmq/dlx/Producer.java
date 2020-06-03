package com.jt.rabbitmq.dlx;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

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
        String exchangeName = "test_dlx_exchange";
        String routeingKey = "dlx.test";

        //发送
        String msg = "Hello RabbitMQ 4 Consumer Exchange Message ...";
        for (int i = 0; i < 5; i++) {
            Thread.sleep(3_000);
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .build();
            channel.basicPublish(exchangeName,routeingKey,properties,(msg + i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
