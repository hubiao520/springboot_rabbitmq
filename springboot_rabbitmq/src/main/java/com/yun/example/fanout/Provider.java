package com.yun.example.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yun.example.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-12 20:37
 */
public class Provider {

    @Test
    public void createMessage() {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = null;
        try {
            //获取通道
            channel = connection.createChannel();
            //绑定交换机  第一个参数：交换机名称 第二个参数：交换机类型
            channel.exchangeDeclare( "order", "fanout" );
            //发送消息  第一个参数：队列名 第二个参数：路由 第四个参数：发送的消息
            channel.basicPublish( "order", "", null, "fanout message".getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭通道与连接
            RabbitMqUtils.closeConnectionAndCannel( channel, connection );
        }
    }
}
