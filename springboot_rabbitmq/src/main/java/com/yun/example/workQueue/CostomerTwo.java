package com.yun.example.workQueue;

import com.rabbitmq.client.*;
import com.yun.example.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-11 21:20
 */
public class CostomerTwo {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //每次只能消费一次
        channel.basicQos( 1 );
        //通道绑定队列
        channel.queueDeclare( "work queue", true, false, false, null );
        //处理消息
        channel.basicConsume( "work queue", false, new DefaultConsumer( channel ) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( "消费者2：" + new String( body ) );
                //手动确认  第一个参数：确认的是那条消息的标识 第二个参数：是否开启多条消息同时确认
                channel.basicAck( envelope.getDeliveryTag(), false );
            }
        } );

    }
}
