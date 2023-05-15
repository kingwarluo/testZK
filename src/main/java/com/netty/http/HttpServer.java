package com.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * netty模拟http服务器
 * @author kingwarluo
 * @date 2023/5/15 18:01
 */
@Slf4j
public class HttpServer {

    /**
     * 启动netty
     */
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(5);
        EventLoopGroup workerGroup = new NioEventLoopGroup(10);
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            server.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline()
                        .addLast(new LoggingHandler(LogLevel.DEBUG))
                        .addLast(new HttpServerCodec())
                        .addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, HttpRequest httpRequest) throws Exception {
                                log.debug("uri:{}", httpRequest.getUri());
                                // 构造响应内容
                                DefaultFullHttpResponse response = new DefaultFullHttpResponse(httpRequest.getProtocolVersion(), HttpResponseStatus.OK);
                                byte[] bytes = "<h1>hello world</h1>".getBytes();
                                response.headers().setInt("Content-Length", bytes.length); // 让浏览器不转圈，告诉浏览器响应长度
                                response.content().writeBytes(bytes);
                                // 写回响应
                                ctx.writeAndFlush(response);
                            }
                        });
                        /*.addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("{}", msg.getClass());
                                if(msg instanceof HttpRequest) {

                                } else if(msg instanceof HttpContent){

                                }
                            }
                        });*/
                }
            });
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
