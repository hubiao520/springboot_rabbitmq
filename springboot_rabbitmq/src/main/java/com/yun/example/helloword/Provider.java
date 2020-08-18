package com.yun.example.helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yun.example.utils.RabbitMqUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-03 21:34
 */
public class Provider {

    /**
     * 生产消息
     */
    @Test
    public void creatMessage() throws IOException {

        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        //通道绑定对应队列
        channel.queueDeclare( "hello", false, false, false, null );
        //发布消息
        channel.basicPublish( "", "hello", null, "hello word".getBytes() );
        //关闭连接和通道
        RabbitMqUtils.closeConnectionAndCannel( channel, connection );

    }

}
