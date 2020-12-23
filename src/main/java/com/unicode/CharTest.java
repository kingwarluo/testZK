package com.unicode;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * 深入分析java中的编码问题
 * 参考：https://developer.ibm.com/zh/articles/j-lo-chinesecoding/
 *
 * @author jianhua.luo
 * @date 2020/12/23
 */
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

        System.out.println("=============================================");

        // CharsetEncoder，根据编码方式，将char[]转成byte[]
        // UTF-16 与 UTF-8 都是处理 Unicode 编码，它们的编码规则不太相同，相对来说 UTF-16 编码效率最高，字符到字节相互转换更简单，进行字符串操作也更好。
        // 它适合在本地磁盘和内存之间使用，可以进行字符和字节之间快速切换，如 Java 的内存编码就是采用 UTF-16 编码。
        // 但是它不适合在网络之间传输，因为网络传输容易损坏字节流，一旦字节流损坏将很难恢复，
        // 想比较而言 UTF-8 更适合网络传输，对 ASCII 字符采用单字节存储，另外单个字符损坏也不会影响后面其它字符，
        // 在编码效率上介于 GBK 和 UTF-16 之间，所以 UTF-8 在编码效率上和编码安全性上做了平衡，是理想的中文编码方式。
        charSetEncode(name, "UTF-8");

        // 这里有一个问题，你是否认真考虑过一段文本它的实际大小应该怎么计算，我曾经碰到过一个问题：就是要想办法压缩 Cookie 大小，减少网络传输量，
        // 当时有选择不同的压缩算法，发现压缩后字符数是减少了，但是并没有减少字节数。所谓的压缩只是将多个单字节字符通过编码转变成一个多字节字符。
        // 减少的是 String.length()，而并没有减少最终的字节数。例如将”ab”两个字符通过某种编码转变成一个奇怪的字符，
        // 虽然字符数从两个变成一个，但是如果采用 UTF-8 编码这个奇怪的字符最后经过编码可能又会变成三个或更多的字节。
        // 同样的道理比如整型数字 1234567 如果当成字符来存储，采用 UTF-8 来编码占用 7 个 byte，采用 UTF-16 编码将会占用 14 个 byte，
        // 但是把它当成 int 型数字来存储只需要 4 个 byte 来存储。
        // 所以看一段文本的大小，看字符本身的长度是没有意义的，即使是一样的字符采用不同的编码最终存储的大小也会不同，所以从字符到字节一定要看编码类型。

        // http请求中带中文字符的编码规范
        // http://localhost:8080/servlet/华哥?name=华哥  转换为
        // http://localhost:8080/servlet/%E5%8D%8E%E5%93%A5?name=%E5%8D%8E%E5%93%A5
        // %E5%8D%8E%E5%93%A5 是UTF-8编码
        // 查阅URL的编码规范RFC3986规范，浏览器编码URL是将非ASCII字符按照某种编码格式编码成16进制数字后，每个16进制数字前加上%

        // http各部分编解码方式：
        // uri：PathInfo（uri）编解码方式和connector设置的URIEncoding相关，QueryString编解码是在request.getParameter方法第一次被调用时进行
        // header：如Cookie、redirectPath，也是在调用 request.getHeader 是进行的。默认ISO-8859-1，不能修改，注意不要传超出ASCII意外的字符
        // POST表单：是在第一次调用 request.getParameter 发生的，通过 HTTP 的 BODY 传递到服务端的，根据ContentType的charset编码，通过 request.setCharacterEncoding(charset) 来设置
        // HTTP BODY：浏览器接收已经编码的response，通过Content-Type 的 charset 来解码，如果没有则通过Html 的 中的 charset 来解码，否则使用浏览器默认编码方式解码
    }

    public static String charSetEncode(String str, String charsetName) {
        try {
            Charset charset = Charset.forName(charsetName);
            CharsetEncoder encoder = charset.newEncoder();
            CharBuffer charBuffer = CharBuffer.wrap(str.toCharArray());
            ByteBuffer byteBuffer = encoder.encode(charBuffer);
            System.out.println("char[] to byte[], " + str + " to " + byteBuffer.toString());
            return byteBuffer.toString();
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        return null;
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
