package com.unicode;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharTest {

    public static void main(String[] args) {
        String name = "I'm 华哥.";
        toHex(name);
        // 转换成16进制编码
        toHex(name, "ISO-8859-1");
        toHex(name, "GB2312");
        toHex(name, "GBK");
        toHex(name, "UTF-16");
        toHex(name, "UTF-8");

        System.out.println("==================================");

        // 转换成char数组，用不同编码方式编码
        encode(name, "ISO-8859-1");
        encode(name, "GB2312");
        encode(name, "GBK");
        encode(name, "UTF-16");
        encode(name, "UTF-8");
    }

    private static String encode(String str, String charsetName) {
        try {
            String newStr = new String(str.getBytes(), charsetName);
            System.out.println(str + " use " + charsetName + " to encode is: " + newStr);
            return newStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String toHex(String str, String charsetName) {
        try {
            byte[] bytes = str.getBytes(charsetName);
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b)).append(" ");
            }
            System.out.println(str + " use " + charsetName + " to hex is: " + sb.toString());
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String toHex(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int ch = (int) str.charAt(i);
            String s4 = Integer.toHexString(ch);
            result = result + s4;
        }
        System.out.println(str + " to hex is: " + result);
        return result;
    }
}
