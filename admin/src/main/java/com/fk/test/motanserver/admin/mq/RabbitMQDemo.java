package com.fk.test.motanserver.admin.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 17:15 2019/8/25.
 * @Modified By:
 */
public class RabbitMQDemo {

    //获取mq连接
    public static Connection getConnection() throws IOException, TimeoutException {
        Connection connection = null;
        //定义一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务端ip地址
        connectionFactory.setHost("127.0.0.1");
        //设置服务端口
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("/");
        //设置用户名密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //获取连接
        connection = connectionFactory.newConnection();

        return connection;
    }
    //队列名称
    private static final String QUEUE_NAME = "test_simple_queue";
    //生产者
    public static void send() throws IOException, TimeoutException {
        Connection connection = getConnection();
        //从连接中选取一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "test mq";
        //发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
        System.out.println("send success");
        //关闭连接
        channel.close();
        connection.close();
    }

    //消费者
    public static void receiver()throws IOException, TimeoutException {
        Connection connection = getConnection();
        //从连接中选取一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义消费者
        Consumer consumer = new DefaultConsumer(channel){
            //有消息时回调
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("receiver msg:  " + msg);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }


    public static void main(String[] args) throws IOException, TimeoutException {

        send();
        receiver();

    }

}
