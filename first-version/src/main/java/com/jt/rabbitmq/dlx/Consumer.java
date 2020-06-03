package com.jt.rabbitmq.dlx;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

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
        String exchangeName = "test_dlx_exchange";
        String routeingKey = "dlx.#";
        String exchangeType = "topic";
        String queueName = "test_dlx_queue";

        //声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        //声明一个队列
        //绑定一个死信队列
        String dlxExchangeName = "mydlx.exchange";
        String dlxQueueName = "mydlx.queue";
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",dlxExchangeName);

        channel.queueDeclare(queueName,true,false,false,arguments);
        //建立一个绑定关系
        channel.queueBind(queueName,exchangeName,routeingKey);


        //声明一个自定义的死信队列，路由key设置为接收全部
        channel.exchangeDeclare(dlxExchangeName,exchangeType,true,false,null);
        channel.queueDeclare(dlxQueueName,true,false,false,null);
        channel.queueBind(dlxQueueName,dlxExchangeName,"#");



        //创建消费者

        //设置channel,参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName,true,new MyConsumer(channel));

    }
}
