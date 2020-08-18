package com.yun.example.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yun.example.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-11 21:12
 */
public class Provider {

    @Test
    public void creatMessage() throws IOException {
        //获取连接
        Connection connection = RabbitMqUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare( "work queue", true, false, false, null );
        //发送消息
        for (int i = 1; i <= 20; i++) {
            channel.basicPublish( "", "work queue", null, (i + "work queue").getBytes() );
        }
        //关闭通道与连接
        RabbitMqUtils.closeConnectionAndCannel( channel, connection );

    }
}
