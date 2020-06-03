package com.jt.rabbitmq.consumerlimit;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author: jingteng
 * @date: 2020/6/3 21:16
 */
public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("--------consumer message----------");
        System.out.println("consumerTag : " + consumerTag);
        System.out.println("envelope : " + JSON.toJSONString(envelope));
        System.out.println("properties : " + properties);
        System.out.println("body : " + new String(body));

        //手动签收消息
        channel.basicAck(envelope.getDeliveryTag(),false);

    }
}
