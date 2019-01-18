package com.kcren.demo.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author yuzc
 * @Description: 服务启动监听器
 * @date 2019/1/17 11:51
 */
@Component
@Slf4j
public class NettyServer {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Channel channel;



    /**
     * 开启服务
     */
    public ChannelFuture run(InetSocketAddress address) {

        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    // step3 设置channel
                    .channel(NioServerSocketChannel.class)
                    // setp4 设置channel hanlder
                    .childHandler(new ServerChannelInitializer())
                    //设置发送和接受缓冲区大小
                    .option(ChannelOption.SO_SNDBUF, 256)
                    .option(ChannelOption.SO_RCVBUF, 256)
                    // 小封包自动连接
                    .option(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO));
            // step 7 Bind 监听端口.
            channelFuture = bootstrap.bind(address).syncUninterruptibly();
            log.info("Netty服务器在[{}:{}]端口启动监听",address.getAddress(),address.getPort());
            channel = channelFuture.channel();
        } catch (Exception e) {
            log.error("Netty start error:", e);
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } finally {
            if (channelFuture != null && channelFuture.isSuccess()) {
                log.info("Netty server listening " + address.getHostName() + " on port " + address.getPort() + " and ready for connections...");
            } else {
                log.error("Netty server start up Error!");
            }
        }
        return channelFuture;
    }

    /**
     * 关闭服务器
     */
    @PreDestroy
    public void destroy() {
        log.info("Shutdown Netty Server...");
        if(channel != null) {
            channel.close();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("Shutdown Netty Server Success!");
    }

}
