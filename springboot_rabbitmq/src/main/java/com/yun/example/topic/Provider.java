package com.yun.example.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yun.example.utils.MqConstant;
import com.yun.example.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-13 21:19
 */
public class Provider {

    @Test
    public void createMessage() throws IOException {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建交换机
        channel.exchangeDeclare( MqConstant.TOPIC_LOG, "topic" );
        //声明routingkey
        //String routingKey = "user.save";
        //String routingKey = "user.delete";
        String routingKey = "user.delete.ok";
        //发送消息
        channel.basicPublish( MqConstant.TOPIC_LOG, routingKey, null, ("这是Topic模型基于routing key：[" + routingKey + "] 发送的消息 ").getBytes() );
        //关闭连接
        RabbitMqUtils.closeConnectionAndCannel( channel, connection );

    }
}
