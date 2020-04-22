package com.jt.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author: jingteng
 * @date: 2020/4/22 21:37
 */
public class RabbitMQUtil {

    private static ConnectionFactory factory;
    static {
        factory = new ConnectionFactory();
        //创建一个连接工厂，并进行配置
        factory.setHost("192.168.244.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");
//        factory.setUsername("jingteng");
//        factory.setPassword("jingteng");
    }

    public static Connection getConnetion(){
        try {
            //通过连接工厂创建连接
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Connection connection, Channel channel){
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
