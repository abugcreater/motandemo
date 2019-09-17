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
    //交换机名称
    private static final String EXCHANGE_NAME = "test_exchange_direct";
    //生产者
    public static void send() throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        //从连接中选取一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //说明交换机direct路由器模式
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
/*
        //说明交换机fanout不处理路由建
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
*/

        String message = "test mq" ;
        //发送消息
        channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("utf-8"));
        System.out.println("send success");

      /*  工作队列模式
      //每个消费者发送确认消息之前，消息队列不发送下一个消息到消费者，一次只处理一个消息
        int perfetchCount = 1;
        channel.basicQos(perfetchCount);
        for (int i = 0; i < 50; i++) {
            String message = "test mq" + i;
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
            System.out.println("send success");
            Thread.sleep(10);
        }
*/
        //关闭连接
        channel.close();
        connection.close();
    }
    //生产者
    public static void txSend() throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        //从连接中选取一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //说明交换机direct路由器模式
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String message = "test mq" ;

        try {
            //开启事务模式
            channel.txSelect();

            //发送消息
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("utf-8"));
            System.out.println("send success");
            channel.txCommit();
        } catch (IOException e) {
            channel.txRollback();
            e.printStackTrace();
        }

        //关闭连接
        channel.close();
        connection.close();
    }

    //消费者
    public static void receiver()throws IOException, TimeoutException {
        Connection connection = getConnection();
        //从连接中选取一个通道
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicQos(1);
        //绑定队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"error");
 /*       工作队列
        //保证一次只分发一个
        channel.basicQos(1);*/
        //定义消费者
        Consumer consumer = new DefaultConsumer(channel){
            //有消息时回调
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("receiver msg:  " + msg);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("done");
                    //手动回执消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
/*
        //设置autoack=false自动应答关闭（工作队列）
        channel.basicConsume(QUEUE_NAME, false, consumer);
*/
    }


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        send();
        receiver();

    }

}
