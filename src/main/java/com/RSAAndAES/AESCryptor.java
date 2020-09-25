package com.RSAAndAES;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;

/**
 * AES密码机。
 * 统一编码UTF8。
 * 128位密钥；ECB分组；PKCS7填充。
 * 密钥不足128位，补填0。
 * @author Phoenix Lee
 * @since 2014/11/11
 */
public class AESCryptor {

    /** AES128位加密 */
    private static final int KEY_BIT_SIZE = 128;

    /** 使用UTF-8字符集 */
    private static final Charset CHAR_SET = Charset.forName("utf-8");

    /** AES，简单分组，填充7 */
    private static final String ALGORITHM = "AES/ECB/PKCS7Padding";

    // 我们平常都使用jdk自带的加密包对数据进行加密，加密方式也都是使用的默认的，如果我们想选择别的加密方式，发现会报错
//    <dependency>
//		<groupId>org.bouncycastle</groupId>
//		<artifactId>bcprov-jdk16</artifactId>
//		<version>1.45</version>
//	</dependency>
//    static {
//        Security.addProvider(new BouncyCastleProvider());
//    }

    /**
     * 加密字符串。
     * @param target    原始字符串
     * @param key   密钥字符串
     * @return  加密结果字符串
     */
    public static String encrypt(String target, String key){
		return AESCryptor.encrypt(target,key,true);
    }

	/**
	 * 加密字符串。
	 * @param target    原始字符串
	 * @param key   密钥字符串
	 * @param isUrlSafe 是否url安全
	 * @return  加密结果字符串
	 */
	public static String encrypt(String target, String key,Boolean isUrlSafe){
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, initKey(key));
			byte[] encryptResult = cipher.doFinal(target.getBytes(CHAR_SET));
			//兼容安卓环境的1.2codec
			String unsafeStr = new String(Base64.encodeBase64(encryptResult, false), CHAR_SET);
			//若为url安全，则替换相关符号
			if(isUrlSafe){
				return unsafeStr.replace('+','-').replace('/','_');
			}
			return unsafeStr;
		} catch (Exception e) {
			throw new RuntimeException("敏感数据加密错误",e);
		}
	}


	/**
     * 解密字符串。
     * @param target    加密结果字符串
     * @param key   密钥字符串
     * @return  原始字符串
     */
    public static String decrypt(String target, String key){
		return AESCryptor.decrypt(target,key,true);
	}

	/**
	 * 解密字符串。
	 * @param target    加密结果字符串
	 * @param key   密钥字符串
	 * @param isUrlSafe 是否url安全
	 * @return  原始字符串
	 */
	public static String decrypt(String target, String key,Boolean isUrlSafe){
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, initKey(key));
			String unsafeStr = target;
			if(isUrlSafe){
				unsafeStr = target.replace('-','+').replace('_','/');
			}
			byte[] decryptResult = cipher.doFinal(Base64.decodeBase64(unsafeStr.getBytes(CHAR_SET)));
			return new String(decryptResult, CHAR_SET);
		} catch (Exception e) {
			throw new RuntimeException("敏感数据解密错误",e);
		}
	}


	/**
     * 生成密钥字节数组，原始密钥字符串不足128位，补填0.
     * @param originalKey 原始秘钥
     * @return SecretKeySpec
     */
    private static SecretKeySpec initKey(String originalKey){
        byte[] keys = originalKey.getBytes(CHAR_SET);

        byte[] bytes = new byte[KEY_BIT_SIZE / 8];
        for (int i = 0; i < bytes.length; i++) {
            if (keys.length > i) {
                bytes[i] = keys[i];
            }else{
                bytes[i] = 0;
            }
        }

        return new SecretKeySpec(bytes, "AES");
    }


}
