package com.netty.server;


import com.netty.server.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author kingwarluo
 * netty服务器
 * @date 2019/1/29 15:45
 */
public class EchoServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup(5);
    private EventLoopGroup workerGroup = new NioEventLoopGroup(10);

    /**
     * 启动netty
     */
    public void start() throws InterruptedException {
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            server.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                }
            });
            server.option(ChannelOption.SO_BACKLOG, 128);
            server.childOption(ChannelOption.SO_KEEPALIVE, true);
            // ChannelFuture描述的是异步回调处理操作
            ChannelFuture future = server.bind(new InetSocketAddress(9999)).sync();
            System.out.println("启动完成");
            future.channel().closeFuture().sync();//等待socket被关闭
        } finally {
            //最终要关闭
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
