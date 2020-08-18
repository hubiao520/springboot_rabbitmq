package com.yun.example.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yun.example.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-13 20:07
 */
public class Provider {

    @Test
    public void createMessage() throws IOException {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //建立交换机  第一个参数：交换机名称 第二个参数：交换机类型
        channel.exchangeDeclare( "direct_log", "direct" );
        //指定路由名称
        String routingKey = "error";
        //发送消息
        channel.basicPublish( "direct_log", routingKey, null, ("这是direct 模型基于routing key：[" + routingKey + "] 发送的消息 ").getBytes() );
        //关闭连接与通道
        RabbitMqUtils.closeConnectionAndCannel( channel, connection );
    }
}
