package com.api;

import com.google.common.collect.Maps;
import jodd.util.StringUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * api接口下行，对接口做安全保护
 * 1、防止重放攻击
 * 2、防止dos攻击
 *
 * @author luojianhua
 * @date 2022/1/6 19:32
 **/
public class DownStream {

    // 盐值
    private final static String salt = "lajgalgaslkasdflds";

    private static List<String> nonceList = new ArrayList<>();

    public static void main(String[] args) {
        String url = "http://exttest.tsp.borgward.com.cn/openapi/location/location/getLastLocationForNewApp?sign=bbbbb&";
        Map<String, String> params = Maps.newHashMap();
        // 这两个就是盐值
        String appKey = "3917230828";
        String secretKey = "2d3e04538ce7ec0a4b21851747eda121";
        params.put("timestamp", String.valueOf(System.currentTimeMillis())); // 时间戳，防止60s内的dos攻击
        params.put("nonce", String.format("%08X", System.currentTimeMillis())); // 转为16进制，保存服务器60s，防止60s内重复请求
        params.put("sign", "asdfaasdfasdfsafsd"); // 入参传入的签名
        if(!check(url, params)) {
            return;
        }
        GeneratorSignUtils.generate(url, appKey, secretKey, params);
    }

    private static boolean check(String url, Map<String, String> params) {
        Long timestamp = Long.parseLong(params.get("timestamp"));
        // 判断timestamp是否超过60s
        if((System.currentTimeMillis() - timestamp) / 1000 > 60) {
            System.out.println("请求超时");
            return false;
        }
        //判断nonce参数是否在“集合”已存在
        String nonce = params.get("nonce");
        if(nonceList.contains(nonce)){
            System.out.println("请求仅一次有效");
            return false;
        }
        //验证数字签名
        String sign = params.get("sign");
        if(!StringUtil.equals(sign, GeneratorSignUtils.generate(url, params, "2d3e04538ce7ec0a4b21851747eda121"))){
            System.out.println("数字签名验证失败");
            return false;
        }
        //记录本次请求的nonce参数
        nonceList.add(nonce);
        //开始处理合法的请求
        return true;
    }
}
