package com.fk.test.motanserver.admin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 10:14 2019/9/17.
 * @Modified By:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private String result = "";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof FullHttpRequest)) {
            result = "未知请求！";
            send(ctx, result, HttpResponseStatus.BAD_REQUEST);
        }
        FullHttpRequest httpRequest = (FullHttpRequest)msg;
        try {
            String path = httpRequest.getUri(); //路径
            String body = getBody(httpRequest); //参数
            HttpMethod method = httpRequest.getMethod(); //方法
            if(!"/test".equalsIgnoreCase(path)){
                result="非法请求!";
                send(ctx,result,HttpResponseStatus.BAD_REQUEST);
                return;
            }
            System.out.println("接收到:"+method+" 请求");
            //如果是GET请求
            if(HttpMethod.GET.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="GET请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }
            //如果是POST请求
            if(HttpMethod.POST.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="POST请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }

            //如果是PUT请求
            if(HttpMethod.PUT.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="PUT请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }
            //如果是DELETE请求
            if(HttpMethod.DELETE.equals(method)){
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:"+body);
                result="DELETE请求";
                send(ctx,result,HttpResponseStatus.OK);
                return;
            }
        } catch (Exception e) {
            System.out.println("处理请求失败");
            e.printStackTrace();
        } finally {
            httpRequest.release();
        }

    }


    //获取body参数
    private String getBody(FullHttpRequest request) {
        ByteBuf byteBuf = request.content();
        return byteBuf.toString(CharsetUtil.UTF_8);
    }

    //发送的返回值 ;返回，消息，状态
    private void send(ChannelHandlerContext ctx, String context, HttpResponseStatus status) {
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }

    //建立时返回消息
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立的客户端地址为：" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功建立连接");
        super.channelActive(ctx);

    }

}
