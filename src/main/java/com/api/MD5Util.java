package com.api;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * description: 该文件说明
 *
 * @author 徐小明（xiaoming.xu@ucarinc.com）
 * @version 1.0
 * @date 2019-05-21 17:16
 */
public class MD5Util {
    public MD5Util() {
    }

    public static final String md5(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] inputBytes = s.getBytes("UTF-8");
        MessageDigest mdInst = MessageDigest.getInstance("md5");
        mdInst.update(inputBytes);
        byte[] md = mdInst.digest();
        int j = md.length;
        char[] str = new char[j * 2];
        int k = 0;

        for(int i = 0; i < j; ++i) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }

        return new String(str);
    }
}