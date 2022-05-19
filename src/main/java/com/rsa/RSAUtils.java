package com.rsa;


import java.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA Util
 * 1.java生成的公私钥格式为 pkcs8, 而openssl默认生成的公私钥格式为 pkcs1，两者的密钥实际上是不能直接互用的
 * 2.java采用的rsa默认补齐方式是pkcs1, 因此互用的时候需要将openssl中的补齐方式设置为RSA_PKCS1_PADDING采用
 * openssl的rsa算法实现公私钥加解密
 * <p>
 * RSA/ECB/PKCS1Padding 是指补足位数，不足8位的用0x00补齐，少8位要补足8个 0x00
 * <p>
 * RSA/ECB/PKCS5Padding 这个是缺几位就用几补足，类似缺2位就补足 0x02
 *
 */
public final class RSAUtils {
    private static String RSA = "RSA";
    private static String RSA_Crypt = "RSA/ECB/PKCS1Padding";
    private static final int KEY_LENGTH = 2048;

    public static void main(String[] args) {
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        printPublicKeyInfo(publicKey);
        String test = "123abc";
        byte[] encryptData = encryptData(test.getBytes(), publicKey);
        byte[] bytes = decryptData(encryptData, privateKey);
        if (bytes != null) {
            System.out.println(new String(bytes));
        }
    }

    /**
     * Randomly generate RSA key pair (default key length is 512)
     *
     * @return KeyPair
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(KEY_LENGTH);
    }

    /**
     * Random generation of RSA key pair
     *
     * @param keyLength Key length, range: 512-2048  General 1024 ,but now is 512
     * @return RSA key pair
     */
    private static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用公钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data      需加密数据的byte数据
     * @param publicKey 公钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptData(byte[] data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_Crypt);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用私钥解密
     *
     * @param encryptedData 经过encryptedData()加密返回的byte数据
     * @param privateKey    私钥
     * @return decrypt date
     */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_Crypt);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 用公钥解密
     *
     * @param encryptedData 经过encryptedData()加密返回的byte数据
     * @param privateKey    私钥
     * @return decrypt date
     */
    public static byte[] decryptData(byte[] encryptedData, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_Crypt);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
     *
     * @param keyBytes 获取到的公钥数据
     * @return 返回公钥
     * @throws NoSuchAlgorithmException 算法异常
     * @throws InvalidKeySpecException  公钥无效异常
     */
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 通过私钥byte[]将公钥还原，适用于RSA算法
     *
     * @param keyBytes 获取到的私钥数据
     * @return 私钥
     * @throws NoSuchAlgorithmException 算法异常
     * @throws InvalidKeySpecException  公钥无效异常
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 使用N、e值还原公钥
     *
     * @param modulus        算法modulus
     * @param publicExponent 公钥指数
     * @return 公钥
     * @throws NoSuchAlgorithmException 算法异常
     * @throws InvalidKeySpecException  公钥无效异常
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 使用N、d值还原私钥
     *
     * @param modulus         算法modulus
     * @param privateExponent 公钥指数
     * @return 私钥
     * @throws NoSuchAlgorithmException 算法异常
     * @throws InvalidKeySpecException  公钥无效异常
     */
    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(modulus);
        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @return 公钥
     * @throws Exception 加载公钥时产生的异常
     */
    private static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }
    private static final char[] HexCode = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	'f' };
    public static String bytesToHexString(byte[] bytes) {
		if (null == bytes)
			return "";
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			stringBuilder.append(HexCode[(bytes[i] >>> 4) & 0x0f]);
			stringBuilder.append(HexCode[bytes[i] & 0x0f]);
		}
		return stringBuilder.toString().toLowerCase();
	}

    /**
     * 从字符串中加载私钥<br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr 私钥数据字符串
     * @return 私钥
     * @throws Exception 加载私钥时产生的异常
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
            System.out.println("key: " + bytesToHexString(buffer));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public static PublicKey loadPublicKey(InputStream in) throws Exception {
        try {
            return loadPublicKey(readKey(in));
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @return 是否成功
     * @throws Exception 加载私钥时产生的异常
     */
    public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
        try {
            return loadPrivateKey(readKey(in));
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    /**
     * 读取密钥信息
     *
     * @param in 输入流
     * @return 读到的数据
     * @throws IOException io 文件异常
     */
    private static String readKey(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String readLine;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) != '-') {
                sb.append(readLine);
                sb.append('\r');
            }
        }
        return sb.toString();
    }

    /**
     * 打印公钥信息
     *
     * @param publicKey 公钥
     */
    public static void printPublicKeyInfo(PublicKey publicKey) {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        System.out.println("----------RSAPublicKey----------");
        System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
        System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
        System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
        System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
        System.out.println("getFormat =" + rsaPublicKey.getFormat());


    }

    /**
     * 打印私钥信息
     *
     * @param privateKey 私钥
     */
    public static void printPrivateKeyInfo(PrivateKey privateKey) {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
        System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
        System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
        System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());

    }

}