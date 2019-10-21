package com.fk.test.motanserver.admin.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 9:47 2019/9/17.
 * @Modified By:
 */
public class NettyServer {
    //设置服务端端口
    private static final int port = 6789;
    //通过nio的方式来接受和处理链接
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static ServerBootstrap bootstrap = new ServerBootstrap();

    /**
     * netty创建全部实现自abstractbootstrap
     * 客户端的是bootstrap服务器端则是ServerBootstrap
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            bootstrap.group(group);
            bootstrap.channel(NioServerSocketChannel.class);
            //设置过滤器
            bootstrap.childHandler(new NettyServerFilter());
            //绑定端口
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("服务端启动成功");
            //监听服务器关闭监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            group.shutdownGracefully();
        }
    }
}
