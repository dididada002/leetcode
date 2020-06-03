package com.jt.rabbitmq.confirm;

import com.jt.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author: jingteng
 * @date: 2020/6/3 20:08
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
        String exchangeName = "test_confirm_exchange";
        String routeingKey = "confirm.save";

        //发送
        String msg = "Hello RabbitMQ 4 Direct Exchange Message ...";
        channel.basicPublish(exchangeName,routeingKey,null,msg.getBytes());

        //添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("---------Ack---------");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("---------No  Ack---------");
            }
        });

    }
}
