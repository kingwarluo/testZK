package com.netty.log;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.StringUtil;

/**
 * @author kingwarluo
 * @{description}
 * @date 2023/5/15 9:41
 */
public class LogUtil {

    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(32);
        byteBuf.writeBytes(new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32
                ,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32});
        log(byteBuf);
    }

    public static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        if (length == 0) {
            StringBuilder buf = new StringBuilder("length: ").append("0B");
            System.out.println(buf.toString());
        } else {
            StringBuilder buf = new StringBuilder();
            buf.append("readerIndex: ").append(buffer.readerIndex()).append(", writerIndex: ").append(buffer.writerIndex())
                    .append(", capacity: ").append(buffer.capacity()).append(", maxCapacity: ").append(buffer.maxWritableBytes());
            buf.append(", length: ").append(length).append('B').append(StringUtil.NEWLINE);
            ByteBufUtil.appendPrettyHexDump(buf, buffer);
            System.out.println(buf.toString());
        }
    }

}
