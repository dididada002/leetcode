package com.jt.rabbitmq.rerutnlistener;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author: jingteng
 * @date: 2020/6/3 20:38
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
//通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //声明
        String exchangeName = "test_returnlis_exchange";
        String routeingKey = "returnlis.#";
        String exchangeType = "topic";
        String queueName = "test_returnlis_queue";


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
