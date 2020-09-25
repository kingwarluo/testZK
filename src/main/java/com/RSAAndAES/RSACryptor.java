package com.RSAAndAES;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class RSACryptor {

    private static final Charset CHARSET = Charset.forName(CharEncoding.UTF_8);

    private static final String ALGORITHM = "RSA";

	private static final String SIGNATURE = "SHA1withRSA";

	public static String encrypt(String target, String publicKey) {

        try {

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    toBytes(publicKey));

            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);

            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            byte[] encyteData = cipher.doFinal(target.getBytes(CHARSET));

            String unsafeStr = new String(Base64.encodeBase64(encyteData, false), CHARSET);

            return unsafeStr.replace('+', '-').replace('/', '_');

        } catch (Exception e) {
            throw new RuntimeException("RSA加密异常", e);
        }
    }

    public static String decrypt(String target, String privateKey) {

        try {

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    toBytes(privateKey));

            PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);

            cipher.init(Cipher.DECRYPT_MODE, priKey);

            String unsafeStr = target.replace('-', '+').replace('_', '/');

            byte[] result = cipher.doFinal(Base64.decodeBase64(unsafeStr.getBytes(CHARSET)));

            return new String(result, CHARSET);

        } catch (Exception e) {
            throw new RuntimeException("RSA解密异常", e);
        }
    }

	/**
	 * 加签
	 *
	 * @param target     待加签数据
	 * @param privateKey 签名私钥
	 * @return
	 */
	public static String sign(String target, String privateKey) {
		try {
			//获取priKey
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(toBytes(privateKey));
			PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
			//初始化Signature
			Signature sign = Signature.getInstance(SIGNATURE);
			sign.initSign(priKey);
			//更新用于签名的数据
			sign.update(target.getBytes(CHARSET));
			byte[] signature = sign.sign();
			return Base64.encodeBase64String(signature);
		} catch (Exception e) {
			throw new RuntimeException("SHA1withRSA加签异常", e);
		}
	}

	/**
	 * 验签
	 *
	 * @param target     待签名数据
	 * @param signResult 签名
	 * @param publicKey  验签公钥
	 * @return
	 */
	public static boolean verify(String target, String signResult, String publicKey) {
		try {
			//获取pubKey
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(toBytes(publicKey));
			PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
			//初始化Signature
			Signature sign = Signature.getInstance(SIGNATURE);
			sign.initVerify(pubKey);
			sign.update(target.getBytes(CHARSET));
			return sign.verify(Base64.decodeBase64(signResult));
		} catch (Exception e) {
			throw new RuntimeException("SHA1withRSA验签异常", e);
		}
	}

	/**
	 * 签名并加密相关参数
	 * 加密使用AES加密
	 *
	 * @param bodyContentMap     参数Map
	 * @param shenzhouPrivateKey 签名私钥
	 * @param secretKey          加密秘钥
	 * @return
	 */
	public static String signAndEncrypt(Map<String, Object> bodyContentMap, String shenzhouPrivateKey, String secretKey) {
		//签名
		String sign = RSACryptor.sign(getSignStrBySortedParam(bodyContentMap), shenzhouPrivateKey);
		//加入签名字段
		bodyContentMap.put("sign", sign);
		//重新生成返回报文并加密
		return AESCryptor.encrypt(JSONObject.toJSONString(bodyContentMap, JSON_CONFIG), secretKey, Boolean.FALSE);
	}

	/** json配置 */
	public static final SerializerFeature[] JSON_CONFIG = {SerializerFeature.QuoteFieldNames,
			SerializerFeature.SkipTransientField,
			SerializerFeature.WriteEnumUsingToString,
			SerializerFeature.SortField,
			SerializerFeature.WriteMapNullValue,
			SerializerFeature.WriteNullListAsEmpty,
			SerializerFeature.WriteNullNumberAsZero,
			SerializerFeature.WriteNullStringAsEmpty};

	/**
	 * 通过排序参数获取待签名字符串(新版)
	 *
	 * @param originalMap
	 * @return
	 */
	public static String getSignStrBySortedParam(Map<String, Object> originalMap) {
		return getSignStr(originalMap, "&");
	}

	/**
	 * 排序参数后获取待签名字符串
	 *
	 * @param originalMap 参数Map
	 * @param separator   分隔符
	 * @return
	 */
	private static String getSignStr(Map<String, Object> originalMap, String separator) {
		if(originalMap == null){
			//若map为null，则返回空字符串
			return "";
		}
		//排序
		SortedMap<String, String> signMap = new TreeMap<String, String>();
		Iterator<Map.Entry<String, Object>> originalMapIterator = originalMap.entrySet().iterator();
		while (originalMapIterator.hasNext()) {
			Map.Entry<String, Object> entry = originalMapIterator.next();
			Object obj = entry.getValue();
			String key = entry.getKey();
			//若obj不为空则进行后续操作
			if (obj != null) {
				String value = obj.toString();
				//若value不为空，且key不为sign、aid则put
				if (StringUtils.isNotEmpty(value)
						&& !key.equals("sign")
						&& !key.equals("aid")) {
					signMap.put(key, value);
				}
			}
		}
		//拼接字符串
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, String>> signMapIterator = signMap.entrySet().iterator();
		while (signMapIterator.hasNext()) {
			Map.Entry<String, String> entry = signMapIterator.next();
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append(separator);
		}
		return sb.substring(0, sb.length() - 1);
	}


	private static final byte[] toBytes(String encodeStr) throws UnsupportedEncodingException {
        return Base64.decodeBase64(encodeStr.getBytes(CHARSET));
    }

    public static void main(String[] args) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKAHIAZ4F0983KFhkiDAF5ybQPhWXBlSzEG61x" +
                "Ut99c8AOWyToynXSg4gyvzS9veqRFPZM1uk7mX0nEgxDc/sv/cu+lc/7mfF6eaTAIgL7JCSggEf4" +
                "k0SN403BGKVKg8e90RwwLgbkVUYM0pa2MOGYaey0SLpX7aDH7z+xwNnC2wIDAQAB";

        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIoAcgBngXT3zcoWGSIMAXnJtA+F" +
                "ZcGVLMQbrXFS331zwA5bJOjKddKDiDK/NL296pEU9kzW6TuZfScSDENz+y/9y76Vz/uZ8Xp5pMAi" +
                "AvskJKCAR/iTRI3jTcEYpUqDx73RHDAuBuRVRgzSlrYw4Zhp7LRIulftoMfvP7HA2cLbAgMBAAEC" +
                "gYB+aXQEXMwgWlKfGGkDYB4F8rdHNHoGvuBX78aMNU7V+y7lt6XyvGr7YFNlsPLeEFcLeTk5H/Bc" +
                "EPiszoJ/U4G2kK55lxwj10RB9j70Kw3HMn7Ux3JK+A/0y4kTZw9utdnn5OU9OteTqAz39NOYE5gg" +
                "DdBDjn+FB9xu5U/zVssgUQJBAOPu3Bs/QuPe0BX1oITMM/fh/fpMO2i0yFfEK6Pa8nxke+Nej4/D" +
                "HD0KOcWgjSRR9wwi0f+wqqSFm8pF7Aqh4+MCQQCa/q2jFx7PZN9IKZoABu1P0xmxYqO2BJ1bI1AX" +
                "mT/s+vzIluKxi2pzpkh9rHtYbCcWU2TXwGNVA7Caly/cQQapAkEArYqtlylBWpHIyQ9tGe8IGlG8" +
                "uBfWm9AZQYmg2G7ZdRBkqybSE1kwZOEmP2wQSnkFqf3Q+eae5gi8JvO51haQlQJAFgLS79VJt9Qw" +
                "bVQTHaveKbbl8C38581x905lgmRuEp52JZpf6SzOWkwkAUzsW1tIlcAUExusP5d9jgaoclP9uQJA" +
                "PjDM0ZPr/WeO2bjdJU1PdC3nhC2WAIZgGP9kdWLlYD6kIlsUb3pqJQ7LgFVmOftgaxyY4RAdEa8c" +
                "AICbiELThA==";


        System.out.println(encrypt("abc", publicKey));

        System.out.println(decrypt(encrypt("abc", publicKey), privateKey));
        String encodePrivteKey = StaticSecretKey.AESEncrypt(privateKey);

        String base64EncodePrivteKey = Base64.encodeBase64String(encodePrivteKey.getBytes());


        System.out.println(encodePrivteKey);
        System.out.println(base64EncodePrivteKey);


        String privateKey2 = StaticSecretKey.AESDecrypt(
        new String(Base64.decodeBase64(base64EncodePrivteKey), Charset.forName("utf-8")));

        System.out.println(privateKey2);

        System.out.println(privateKey.equals(privateKey2));


    }

}
