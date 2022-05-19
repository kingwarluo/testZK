package com.rsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * rsa公钥私钥生成原理，加解密过程
 * @author kingwarluo
 * @date 2022/5/19 10:20
 */
public class RsaPrinciple {

    // keypair
    // 公钥：（E：N）（3, 33）
    private static List<Integer> publicKey = Arrays.asList(3, 33);

    // 私钥：（D：N）（7, 33）
    private static List<Integer> privateKey = Arrays.asList(7, 33);

    /**
     * 密钥对生成步骤：
     *  1、选择两个质数P, Q    3, 11
     *  2、计算N = P * Q，将N作为模   33
     *  3、选择一个大于1且小于(P - 1) * (Q - 1)的整数E，确保E与(P - 1) * (Q - 1)互质  3
     *  4、计算D，使 E * D - 1是(P - 1) * (Q - 1)的倍数  3 * 7 - 1 = 2 * 10
     *  5、公钥=(n,e)，私钥=(n,d)
     * @param args
     */
    public static void main(String[] args) {
        // 传输内容：c a o
        // 对应数字：3 1 15
        List<Integer> content = Arrays.asList(3, 1, 15);
        // 加密
        // 原文的E次方
        List<Double> secretContent = new ArrayList<>(content.size());
        secretContent.add(Math.pow(content.get(0), publicKey.get(0)));
        secretContent.add(Math.pow(content.get(1), publicKey.get(0)));
        secretContent.add(Math.pow(content.get(2), publicKey.get(0)));
        // E次方后取模
        for (int i = 0; i < secretContent.size(); i++) {
            secretContent.set(i, secretContent.get(i) % publicKey.get(1));
        }
        System.out.println(secretContent);

        // 解密
        // 密文的E次方
        List<Integer> oriContent = new ArrayList<>(content.size());
        secretContent.set(0, Math.pow(secretContent.get(0), privateKey.get(0)));
        secretContent.set(1, Math.pow(secretContent.get(1), privateKey.get(0)));
        secretContent.set(2, Math.pow(secretContent.get(2), privateKey.get(0)));
        // E次方后取模
        for (int i = 0; i < secretContent.size(); i++) {
            oriContent.add((int) (secretContent.get(i) % privateKey.get(1)));
        }
        System.out.println(oriContent);

        List<Integer> prime = findPrime();
        System.out.println(prime);
    }

    /**
     * 附：求100万内所有质数的方法
     * @return
     */
    public static List<Integer> findPrime(){
        List<Integer> list = new ArrayList<>(1000000);
        for (int n = 2; n < 1000000; n++) {
            boolean isPrime = true;
            int sqrt = (int) Math.sqrt(n);
            for (Integer i : list) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
                if (i > sqrt) {
                    break;
                }
            }
            if (isPrime) {
                list.add(n);
            }
        }
        return list;
    }

}
