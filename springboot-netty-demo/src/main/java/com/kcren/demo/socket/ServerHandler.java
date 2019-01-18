package com.kcren.demo.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author yuzc
 * @Description: channel处理逻辑
 * @date 2019/1/17 17:25
 */
public class ServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("server receive message :"+ o.toString());
        channelHandlerContext.channel().writeAndFlush("yes server already accept your message" + o.toString());
        channelHandlerContext.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive>>>>>>>>");
    }
}
