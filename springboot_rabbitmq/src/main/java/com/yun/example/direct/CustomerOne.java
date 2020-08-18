package com.yun.example.direct;

import com.rabbitmq.client.*;
import com.yun.example.utils.MqConstant;
import com.yun.example.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-13 20:54
 */
public class CustomerOne {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建交换机
        channel.exchangeDeclare( MqConstant.DIRECT_LOG, "direct" );
        //声明队列并绑定交换机
        //临时队列
        String queueName = channel.queueDeclare().getQueue();
        //queueBind(String queue, String exchange, String routingKey)
        channel.queueBind( queueName, MqConstant.DIRECT_LOG, "error" );

        //处理消息 basicConsume(String queue, boolean autoAck, Consumer callback) 第一个参数是队列 第二个参数是是否自动确认 第三个参数是回调
        channel.basicConsume( queueName, true, new DefaultConsumer( channel ) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println( "消费者1：" + new String( body ) );
            }
        } );


    }
}
