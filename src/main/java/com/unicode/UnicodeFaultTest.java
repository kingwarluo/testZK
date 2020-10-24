package com.unicode;

import org.apache.commons.lang.StringUtils;

/**
 * 万国码测试类（错误示范）
 *
 * @author jianhua.luo
 * @date 2020/10/24
 */
public class UnicodeFaultTest {

    /**
     * Unicode平面：
     * U+0000 - U+FFFF 基本多文种平面 BMP
     * U+10000 - U+1FFFF 多文种补充平面 SMP
     * U+20000 - U+2FFFF  表意文字补充平面 SIP
     * U+30000 - U+3FFFF  表意文字第三平面 TIP
     * U+40000 - U+DFFFF  尚未使用
     * U+E0000 - U+EFFFF  特别用途补充平面 SSP
     * U+F0000 - U+FFFFF  保留作为私人使用区（A区） PUA-A
     * U+100000 - U+10FFFF  保留作为私人使用区（B区） PUA-B
     */
    public static void main(String[] args) {
        // 用户昵称为🐟🐟🐂
        String content = "\ud83d\udc1f\ud83d\udc1f\ud83d\udc02";
        System.out.println("content=" + content + ", length=" + content.length());

        // 截取前缀和后缀
        String namePrefix = StringUtils.substring(content, 0, 1);
        String nameSuffix = StringUtils.substring(content, content.length() - 1);
        content = String.format("%s%s%s", namePrefix, "*", nameSuffix);
        // 打印不符合预期了
        System.out.println(content);

        //查看表情、中文、英文占用字节数（1字节=8比特）
        String emoji = "\ud83d\udc1f";
        System.out.println(emoji.getBytes().length);//4字节
        String chinese = "我";
        System.out.println(chinese.getBytes().length);//3字节
        String english = "m";
        System.out.println(english.getBytes().length);//1字节
    }

}
