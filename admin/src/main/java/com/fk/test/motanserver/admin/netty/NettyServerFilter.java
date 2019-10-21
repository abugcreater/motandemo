package com.fk.test.motanserver.admin.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @Author: fengk
 * @Description 自定义过滤器
 * @Date: Create in 9:57 2019/9/17.
 * @Modified By:
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        ph.addLast("encoder",new HttpResponseEncoder());
        ph.addLast("decoder",new HttpRequestDecoder());
        ph.addLast("aggregator", new HttpObjectAggregator(10*1024*1024));//把单个http请求转为FullHttpReuest或FullHttpResponse
        ph.addLast("handler", new NettyServerHandler());// 服务端业务逻辑

    }
}
