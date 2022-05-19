package com.rsa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


public class RSATest {
	
	private static final char[] HexCode = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	'f' };
	
	
	public static String bytesToHexString(byte[] bytes) {
		if (null == bytes)
			return "";
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			if (i%16 == 0)
				stringBuilder.append("\r\n");
//			stringBuilder.append("0x");
			stringBuilder.append(HexCode[(bytes[i] >>> 4) & 0x0f]);
			stringBuilder.append(HexCode[bytes[i] & 0x0f]);
//			stringBuilder.append(",");
			stringBuilder.append(" ");
		}
		return stringBuilder.toString().toLowerCase();
	}
	

	public static String bytesToNumString(byte[] bytes) {
		if (null == bytes)
			return "";
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			if (i%16 == 0)
				stringBuilder.append("\r\n");
			stringBuilder.append(HexCode[(bytes[i] >>> 4) & 0x0f]);
			stringBuilder.append(HexCode[bytes[i] & 0x0f]);
			stringBuilder.append(" ");
//			stringBuilder.append(bytes[i]);
//			stringBuilder.append(",");
		}
		return stringBuilder.toString().toLowerCase();
	}
	
	public static void printPublicKeyInfo(PublicKey publicKey) {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
		String moudlusStr = rsaPublicKey.getModulus().toString(16);
		byte[] bytes = moudlusStr.getBytes();
        System.out.println("----------RSAPublicKey----------");
        System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
        // System.out.println("Modulus=" + RSATest.bytesToHexString(rsaPublicKey.getModulus().toString(16));
        // System.out.println("Modulus=" + rsaPublicKey.getModulus().toString(16));
//		 System.out.println("Modulus=" + moudlusStr);
		// System.out.println("Modulus=" + bytesToHexString(bytes));
        // System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
//         System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
        // System.out.println("getFormat =" + rsaPublicKey.getFormat());

    }
	
	
	public static byte[]CharToBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

	public static void main(String[] args) throws FileNotFoundException, CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
		
		KeyPair keyPair = RSAUtils.generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        RSATest.printPublicKeyInfo(publicKey);
        PrivateKey privateKeytest;
        int i = 0;
        String test = "RSA PKCS1_v1.5 Encryption of user messag";
          byte[] encryptData = RSAUtils.encryptData(test.getBytes(), publicKey);
//        char[] data = 
//        byte[] encryptData = 
//        	{ };
//		byte[] encryptData = new byte[256];
		
//		char[] data_Private =
//        String key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCwhRR3R6/aQiKKDdz0bZELu+AfpGfxmI9ghoxswOsagMcmJnuM1/nFFhrv93l1x8m+7elnjYr9Bjvk7N3MluTrt+BEiMOr1Y7iYNyMN3QYDfC3XL/nMLTq15DaX4SjAGzWsZcqD09P1yRSPesYkpWP9i5Va2jw8tcuv8X2nLjF8MdIfh44ReLJcVCV4/f3RI6MiIkiQFbCQdl27iozamFZ26PQP0mIKjOea5IUmAqCVBxaSTMZsbR13sQljw3GcMzsyDEivn4pEQrqzAfoMWyEJXeC0t7Ux6YajFWrJ8Ne+miwZnqs9jwSY0iKkLpX0dFrmLKWoUzz+oYa0y2xj62PAgMBAAECgf9igmq2SI+ntacqdTzIxW21Ud+Ot/45CPvHnj3Q2djBArapk789Uhcz1MzvYpLdfV1MlooipoXIJm41ZKWWQygGhmQIY8IkeJJm9rhidtRCD8nPExXjVeTUXXjOh5DkM2mbEaLTPITTTfkIMNs1uULxabXpSwgBTWJio3AsQY5T1sgu0PhUL8AfUIFDcXuZwi15NKZSjBYQLRgLFgMXrkZx+3VUXMeRulQNUo6+s+Z810+yIIpi+LeOdMgwBraM6CFAWjcUwk5KtqUKZGeqff+F3YuJHPwtAzjP1cd2HIHCf5luaOCMMg3m6Va27dwertIbjhaAwnYrns7TmJ8DrOkCgYEAxhRwiYTjbKKPshW26Fk7kVMaZ3h69IdVPib5M0vxxkw7Pvwc7JQJEUzsfHBlYE/GbFXcTBSw3e4mew6pcQriUCIczmBH1tOXdCjvd3mFryPA7v/roktmKVh6cimwwKZi4vjZQncoqT9CmsdCHtz74qachFmxtAirU91N7Y6OyhMCgYEA5CK8Ey+zEs5dkYTtfF2SpQGsRKyGLLGsiMLphEMrIohTwTV0tquLhDDpn9qQHq/Z53CWDOxQNjnhfVV6hCp/h9tdnk87bPJovU4o0RMcZNShsZpzn4cdhPT6A1kLjgB/G0kn2ADAqyOVrOFJ/oryHKiY6x4mX3crCgSmnQ6FvhUCgYEAkqaFXztUyZRdgQLUpZeTI5f8X4jyt2IO+XIPZCwg96hu4MX1gC5lvDpQiB0K4VCO3px++pEDFXyBXvBPANOHFL78xsq/xUxowGaiTLlbAX0eFZTCVyktnQTVuvok060cX2DQMYZZQ10DqeZGjOUdVAP4s4BYsasNdYzOakf2baMCgYAm72qr15s3XXMIr+gcLpglgziUa+FmZVht6t4Rzajh9uRceDQYLQTdpb9podKw+hA1eLiTMg2qg4xhaKJFRj+Hvz+mEh2xRVns3RcIP7u71bJW7mZHVrcKBwlYYu0e6iTHUl/zdIVtb+BOphCL2cjibT/T+MxRxLPavNp2uZDH1QKBgQCR5LAKwbxTR2i2pKVFqKxkhzbmbtg6C9QM1rPouL8q2k6NKDoS08emdu/EvN4Zkcrj8Y5HJfiVoYLkUGlItlWA1YOjfuaL3UUNZcX9B58ddTVpt42ZZ1ZYYJ3kI7pZKAEJ09XKW4DDHKMIeo7W6xhqPXU1F3I4oE3v1Nk+68Y+Hw==";
//        byte[] privateData = key.getBytes();
//        System.out.println("length: " + privateData.length + ",Private Key: " + bytesToNumString(privateData));
//        byte[] privateData = 
//		byte[] privateData = new byte[1216];
        
		
//		encryptData = CharToBytes(data);		//加密数据 
//		privateData = CharToBytes(data_Private);
		
        //  System.out.println(bytesToHexString(encryptData));	// 鍔犲瘑鍚庢暟鎹�
//         byte[] privateTest = privateKey.getEncoded();
//		 RSAUtils.getPrivateKey(privateTest);
        byte[] publicTest = privateKey.getEncoded();
		 RSAUtils.getPublicKey(publicTest);
//       privateKeytest = RSAUtils.getPrivateKey(privateKey.getEncoded());
          byte[] bytes = RSAUtils.decryptData(encryptData, privateKey);
//      byte[] bytes = RSAUtils.decryptData(encryptData, privateKeytest);
//         System.out.println("length: " + publicKey.getEncoded().length + ",Public Key: " + bytesToHexString(publicKey.getEncoded()));
//       System.out.println("length: " + privateKey.getEncoded().length + ",Private Key: " + bytesToHexString(privateKey.getEncoded()));
//        System.out.println("format :"+privateKey.getFormat());
//    System.out.println("length: " + privateKey.getEncoded().length + ",Private Key: " + bytesToNumString(privateKey.getEncoded()));

        if (bytes != null) {
        	System.out.println("length: " + bytes.length + "," + bytesToHexString(bytes));
        } 
	}
}
