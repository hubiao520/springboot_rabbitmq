package com.yun.example.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Description
 *
 * @author hubiao
 * @since 2020-08-11 20:15
 */
public class RabbitMqUtils {

    private static ConnectionFactory connectionFactory;

    /**
     * 在类加载的时候执行，且只执行一次
     */
    static {
        //创建rabbitmq的工厂类
        connectionFactory = new ConnectionFactory();
        //设置ip地址
        connectionFactory.setHost( "49.235.152.179" );
        //设置端口号
        connectionFactory.setPort( 5672 );
        //设置连接的虚拟主机
        connectionFactory.setVirtualHost( "/ems" );
        //设置用户名密码
        connectionFactory.setUsername( "zwhw" );
        connectionFactory.setPassword( "zwhw123456" );
    }

    /**
     * 创建一个连接的公共方法
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建一个关闭连接和通道的方法
     *
     * @param channel
     * @param connection
     */
    public static void closeConnectionAndCannel(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
