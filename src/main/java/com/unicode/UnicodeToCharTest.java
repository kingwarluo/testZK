package com.unicode;

/**
 * unicode和char之间相互转换
 *
 * @author jianhua.luo
 * @date 2020/10/24
 */
public class UnicodeToCharTest {

    public static void main(String[] args) {
        // Unicode to chars
        char[] chars = Character.toChars(127851);
        System.out.println(String.valueOf(chars));

        // chars to unicode
        // 用户昵称为🐟🐟🐂
        String content = "1\uD83D\uDC1F\uD83D\uDC1F\uD83D\uDC02";
        content.codePoints().boxed().forEach(System.out::println);

        //
        gbEncoding("🐟");//打印单个char打不出来，因为emoji是辅助平面组合生成的
        gbEncoding("测试");//打印单个字符打得出来

        System.out.println(decodeUnicode("\\uD83D\\uDC1F"));
    }

    //中文转Unicode
    public static String gbEncoding(final String gbString) {   //gbString = "测试"
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            System.out.println("char:" + (int)utfBytes[byteIndex]);
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
            System.out.println("hexb:" + hexB);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    //Unicode转中文
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            System.out.println("charStr:" + charStr);
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        System.out.println(buffer.toString().length());
        return buffer.toString();
    }



}
