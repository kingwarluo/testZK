package com.netty.server;

import com.netty.server.handler.EchoServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author kingwarluo
 * @{description}
 * @date 2023/5/15 9:21
 */
public class EchoClient {

    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) throws InterruptedException {
        try {
            Bootstrap client = new Bootstrap();
            client.group(workerGroup).channel(NioSocketChannel.class);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf buffer = ctx.alloc().buffer(16);
                            byte[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
                            ctx.writeAndFlush(buffer.writeBytes(ints));
                        }
                    });
                }
            });
            client.option(ChannelOption.SO_BACKLOG, 128);
            // ChannelFuture描述的是异步回调处理操作
            ChannelFuture future = client.connect(new InetSocketAddress(9999)).sync();
            System.out.println("启动完成");
        } finally {
            //最终要关闭
            workerGroup.shutdownGracefully();
        }
    }

}
