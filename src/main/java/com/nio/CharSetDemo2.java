package com.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharSetDemo2 {

    public static void main(String[] args) throws CharacterCodingException {
        Charset charset = Charset.forName("ISO-8859-1");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer cb = CharBuffer.wrap("kingwarluo");
        ByteBuffer byteBuffer = encoder.encode(cb);
        while(byteBuffer.hasRemaining()){
            byte b = byteBuffer.get();
            System.out.println(b);
        }
        byteBuffer.flip();
        System.out.println("11111:" + decoder.decode(byteBuffer));
    }

}
