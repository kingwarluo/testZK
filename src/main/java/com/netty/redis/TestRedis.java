package com.netty.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 模拟redis客户端
 * @author kingwarluo
 * @date 2023/5/15 17:11
 */
public class TestRedis {

    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) {
        final byte[] LINE = {13, 10};
        try {
            Bootstrap client = new Bootstrap();
            client.group(workerGroup).channel(NioSocketChannel.class);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new LoggingHandler());
                    channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 发送redis命令 set name zhangsan
                            ByteBuf buffer = ctx.alloc().buffer();
                            buffer.writeBytes("*3".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("$3".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("set".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("$4".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("name".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("$8".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("zhangsan".getBytes());
                            buffer.writeBytes(LINE);
                            ctx.writeAndFlush(buffer);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf byteBuf = (ByteBuf) msg;
                            System.out.println("======================read======================");
                            System.out.println(byteBuf.toString(Charset.defaultCharset()));
                            ctx.close();
                        }

                    });
                }
            });
            // ChannelFuture描述的是异步回调处理操作
            ChannelFuture channelFuture = client.connect(new InetSocketAddress("101.43.122.53", 6379)).sync();
            channelFuture.channel().closeFuture().sync();
            System.out.println("启动完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
