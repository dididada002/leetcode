package com.jt.rabbitmq.rerutnlistener;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

/**
 * @author: jingteng
 * @date: 2020/6/3 20:38
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        //通过连接工厂创建连接
        Connection connection = RabbitMQUtil.getConnetion();

        //通过连接创建一个channel
        Channel channel = connection.createChannel();

        //指定消息投递模式：确认模式
        channel.confirmSelect();

        //声明
        String exchangeName = "test_returnlis_exchange";
        String routeingKey = "returnlis.save";
        String routeingKeyError = "error.save";

        //return机制，监听消息消费情况
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode,
                                     String replyText,
                                     String exchange,
                                     String routingKey,
                                     AMQP.BasicProperties basicProperties,
                                     byte[] bytes) throws IOException {
                System.out.println("-------handle return--------");
                System.out.println("replyCode: " + replyCode);
                System.out.println("replyText: " + replyText);
                System.out.println("exchange: " + exchange);
                System.out.println("routingKey: " + routingKey);
                System.out.println("basicProperties: " + basicProperties);
                System.out.println("bytes: " + new String(bytes));
            }
        });

        //发送
        String msg = "Hello RabbitMQ 4 Direct Exchange Message ...";

        //第三个参数为ture,则可以监听到不可送达的消息，如果为false，则mq会自动删除不可送达的消息
//        channel.basicPublish(exchangeName,routeingKey,true,null,msg.getBytes());


        channel.basicPublish(exchangeName,routeingKeyError,true,null,msg.getBytes());
    }
}
