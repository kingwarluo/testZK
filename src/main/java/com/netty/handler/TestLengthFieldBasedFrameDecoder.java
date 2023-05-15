package com.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 长度字段解码器
 * @author kingwarluo
 * @date 2023/5/15 13:39
 */
public class TestLengthFieldBasedFrameDecoder {

    public static void main(String[] args) {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                // 总缓冲区长度...长度字段在第几位...长度字段长度...内容偏移多少位...跳过几位开始表示内容
                // 如下：截取出来的就是 版本+内容
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 4, 4),
                new LoggingHandler(LogLevel.DEBUG));

        // 申请个ByteBuf
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "hello kingwarluo!");
        send(buffer, "welcome!");
        embeddedChannel.writeInbound(buffer);
    }

    private static void send(ByteBuf buffer, String message) {
        buffer.writeInt(message.length());
        //版本
        buffer.writeInt(1);
        // 内容
        buffer.writeBytes(message.getBytes());
    }

}
