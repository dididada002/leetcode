package com.jt.rabbitmq.ack;

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
//        System.out.println("consumerTag : " + consumerTag);
//        System.out.println("envelope : " + JSON.toJSONString(envelope));
//        System.out.println("properties : " + properties);
        System.out.println("body : " + new String(body));

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ((Integer)properties.getHeaders().get("num") == 0){

            //第二个参数：是否批量NACK，第三个参数：是否重回队列
            channel.basicNack(envelope.getDeliveryTag(),false,true);
        }else {
            //手动签收消息：参数2：是否批量ACK
            channel.basicAck(envelope.getDeliveryTag(),false);
        }
//        channel.basicAck(envelope.getDeliveryTag(),false);


    }
}
