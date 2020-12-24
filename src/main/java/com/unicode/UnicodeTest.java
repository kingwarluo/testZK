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

        System.out.println(containsEmoji(content));
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

    /**
     * 是否包含emoji表情
     *
     * @author jianhua.luo
     * @date 2020/12/24
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i+1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50 ) {
                    return true;
                }
                if (source.length() > 1 && i < source.length() -1) {
                    char ls = source.charAt(i+1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return  false;
    }

}
