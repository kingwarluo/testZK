package com.RSAAndAES;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 *  本类主要作用是提供对称加密算法，可以使用此类对文本进行对称加解密<br/>
 *  目前支持的对称加密算法暂时只有AES，其它较为常用的对称加密有DES,3DES等<br/>
 *  具体可参见https://en.wikipedia.org/wiki/Symmetric-key_algorithm <br/>
 *  其实本类的名字叫SymmetricCryptogram更合适，但由于这两个单词比较生疏，所以命名为StaticSecretKey（静态密钥）
 * <p/>
 *
 * @author jianhua.luo
 * @date 2020/9/25
 */
public class StaticSecretKey {


    /**
     * 使用AES算法对内容进行对称加密(使用默认密钥)
     * @param content 需加密的内容
     * @return 加密后的内容
     */
    public static String AESEncrypt(String content) {
        if (StringUtils.isBlank(content)) {
            return "";
        }

        return AESCryptor.encrypt(content, SecretKey.STATIC_KEY_KEY);
    }

    /**
     * <p>使用AES算法对内容进行对称加密<br/>
     * 如果传入的加密密钥为空，会抛出异常
     * <p/>
     * @param content 需加密的内容
     * @param key 加密时使用的密钥
     * @return 加密后的内容
     * @throws
     */
    public static String AESEncrypt(String content, String key) {
        if (StringUtils.isBlank(content)) {
            return "";
        }

        if (StringUtils.isBlank(key)) {
            throw new RuntimeException();
        }

        return AESCryptor.encrypt(content,key);
    }

    /**
     * <p>
     *     使用AES算法对内容进行解密<br/>
     * </p>
     * @param encryptedContent 使用AES算法加密后的内容
     * @return 解密后的内容
     */
    public static String AESDecrypt(String encryptedContent) {
        if (StringUtils.isBlank(encryptedContent)) {
            return "";
        }

        return AESCryptor.decrypt(encryptedContent,SecretKey.STATIC_KEY_KEY);
    }

    /**
     * <p>使用AES算法对内容进行对称解密<br/>
     * 如果传入的加密密钥为空，会抛出异常
     * <p/>
     * @param encryptedContent 使用AES算法加密后的内容
     * @param key 解密时使用的密钥（需与加密时一致）
     * @return 解密后的内容
     */
    public static String AESDecrypt(String encryptedContent,String key) {
        if (StringUtils.isBlank(encryptedContent)) {
            return "";
        }

        if (StringUtils.isBlank(key)) {
            throw new RuntimeException();
        }

        return AESCryptor.decrypt(encryptedContent,key);
    }

    /**
     * <p>
     *     扩展AESDecrypt方法带默认值，使用AES算法对内容进行解密<br/>
     *     1、使用场景，静态秘钥入库使用AES加密算法。显示时需解密。<br/>
     * </p>
     * @param encryptedContent 使用AES算法加密后的内容
     * @param defaultValueForDecryptError 解密失败返回该值
     * @return 解密后的内容
     * @author add by 陈志民 at 2017-07-26
     */
    public static String AESDecryptWithDefaultValue(String encryptedContent,String defaultValueForDecryptError){
        try {
            return AESDecrypt(encryptedContent);
        } catch (Exception e) {
            return defaultValueForDecryptError;
        }
    }

    public static void main(String[] args) {
        //System.out.println(StaticSecretKey.AESDecrypt(StaticSecretKey.AESEncrypt("nihao")));
        String s = "you are right";

        String s1 = StaticSecretKey.AESEncrypt(s);
        System.out.println(s1);
        System.out.println(StaticSecretKey.AESDecrypt(s1));

        RSACryptor.encrypt("123", SecretKey.DYNAMIC_PUBLIC_KEY);

    }
}
