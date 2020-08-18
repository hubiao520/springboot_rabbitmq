package com.yun.example.fanout;

import com.rabbitmq.client.*;
import com.yun.example.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-12 20:49
 */
public class CustomerOne {

    public static void main(String[] args) {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = null;
        try {
            //创建通道
            channel = connection.createChannel();
            //获取临时队列名称
            String queueTemp = channel.queueDeclare().getQueue();
            //绑定交换机
            channel.exchangeDeclare( "order", "fanout" );
            //交换机与队列绑定
            channel.queueBind( queueTemp, "order", "" );
            //消费消息
            channel.basicConsume( queueTemp, true, new DefaultConsumer( channel ) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println( "消费者1：" + new String( body ) );
                }
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
