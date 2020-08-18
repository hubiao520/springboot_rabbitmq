package com.yun.example.topic;

import com.rabbitmq.client.*;
import com.yun.example.utils.MqConstant;
import com.yun.example.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-13 21:27
 */
public class CustomerTwo {
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //绑定交换机
        channel.exchangeDeclare( MqConstant.TOPIC_LOG, "topic" );
        //声明一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        //交换机与队列和路由绑定
        channel.queueBind( queueName, MqConstant.TOPIC_LOG, "user.#" );
        //消费消息
        channel.basicConsume( queueName, true, new DefaultConsumer( channel ) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println( "消费者2：" + new String( body ) );
            }
        } );
    }
}
