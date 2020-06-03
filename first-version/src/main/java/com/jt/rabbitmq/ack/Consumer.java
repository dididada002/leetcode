package com.jt.rabbitmq.ack;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author: jingteng
 * @date: 2020/6/3 21:12
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
//通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test_ack_exchange";
        String routeingKey = "ack.test";
        String exchangeType = "topic";
        String queueName = "test_ack_queue";

        //声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明一个队列
        channel.queueDeclare(queueName,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(queueName,exchangeName,routeingKey);


        //创建消费者

        //设置channel,参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName,false,new MyConsumer(channel));

    }
}
