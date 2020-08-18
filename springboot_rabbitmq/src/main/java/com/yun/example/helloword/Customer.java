package com.yun.example.helloword;

import com.rabbitmq.client.*;
import com.yun.example.utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-11 19:53
 */

public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = RabbitMqUtils.getConnection();
        //获取连接通道
        Channel channel = connection.createChannel();
        //通道绑定对应队列
        channel.queueDeclare( "hello", false, false, false, null );
        channel.basicConsume( "hello", true, new DefaultConsumer( channel ) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println( "=================rabbitMq:" + new String( body ) );
            }
        } );

    }
}
