package com.unicode;

/**
 * 万国码测试类
 *
 * @author jianhua.luo
 * @date 2020/10/24
 */
public class UnicodeTest {

    public static void main(String[] args) {
        // 用户昵称为🐟🐟🐂
        String content = "\ud83d\udc1f\ud83d\udc1f\ud83d\udc02";
        int realStringLength = realStringLength(content);
        System.out.println("content=" + content + ", length=" + realStringLength);

        // 截取前缀和后缀
        String namePrefix = subString(content, 1, 0);
        String nameSuffix = subString(content, realStringLength - 1, 1);
        content = String.format("%s%s%s", namePrefix, "*", nameSuffix);
        System.out.println(content);
    }

    /**
     * 截取字符串
     * @param content   原有字符串
     * @param location  开始截取的位置
     * @param type      type=0代表prefix，向前截取；1代表surfix，向后截取
     * @return
     */
    private static String subString(String content, int location, int type) {
        if(location < 0) {
            return content;
        }

        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if(count == location) {
                if(type == 0) {
                    return content.substring(0, i);
                }
                return content.substring(i);
            }

            char c = content.charAt(i);
            if(Character.isHighSurrogate(c) || Character.isLowSurrogate(c)) {
                i++;
            }
            count++;
        }
        return content;
    }

    /**
     * 包含emoji表情的字符串实际长度
     * 原理：代理从高位+低位分离出一个组合，表示emoji
     * highSurrogate  从 U+D800 - U+DBFF
     * lowSurrogate  从 U+DC00 - U+DFFF
     *
     * @param content   原有字符串
     * @return  实际长度
     */
    private static int realStringLength(String content) {
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if(Character.isHighSurrogate(c) || Character.isLowSurrogate(c)) {
                i++;
            }
            count++;
        }
        return count;
    }

}
