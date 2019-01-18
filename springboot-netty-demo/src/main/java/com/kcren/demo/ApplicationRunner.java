package com.kcren.demo;


import com.kcren.demo.config.NettyConfig;
import com.kcren.demo.socket.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author yuzc
 * @Description: 启动sock监听
 * @date 2019/1/17 11:50
 */
@Component
public class ApplicationRunner implements CommandLineRunner {

    @Resource
    private NettyServer socketServer;

    @Resource
    private NettyConfig nettyConfig;

    public void run(String... args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(nettyConfig.getUrl(), nettyConfig.getPort());
        ChannelFuture future = socketServer.run(address);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                socketServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
