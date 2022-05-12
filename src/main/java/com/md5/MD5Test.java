package com.md5;

import com.api.MD5Util;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/5/12 15:35
 */
public class MD5Test {

    public static List<Integer> nonceList;

//    //判断timestamp参数是否有效
//    if( $now - $timestamp > 60){
//            die("请求超时");
//        }
//    //判断nonce参数是否在“集合”已存在
//    if( in_array($nonce,$nonceArray) ){
//            die("请求仅一次有效");
//        }
//    //验证数字签名
//    if( $sign != md5($uid.$stime.$nonce.$secretkey) ){
//            die("数字签名验证失败");
//        }
//    //记录本次请求的nonce参数
//    $nonceArray.push($nonce);
//    //开始处理合法的请求
    public static void main(String[] args) {
        // api 防重放攻击
        String secretkey = "123456";
        long timestamp = System.currentTimeMillis();
        String uid = "112233445566";
        // 请求后
        int nonce = "4564879".hashCode();
        try {
            if(nonceList.contains(nonce)) {
                // 请求拦截
            } else {
                // 请求放行，并设置60s过期，一般用redis实现
                nonceList.add(nonce);
            }
            // 生成签名，校验是否一致
            String sign = MD5Util.md5(secretkey + timestamp + uid + nonce);
            System.out.println(sign);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
