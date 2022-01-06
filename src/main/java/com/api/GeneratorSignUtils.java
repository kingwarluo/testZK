package com.api;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

/**
 * description: 模拟B-Link验证签名sign生成
 *
 * @author 徐小明（xiaoming.xu@ucarinc.com）
 * @version 1.0
 * @date 2019-05-21 17:15
 */
@Slf4j
public class GeneratorSignUtils {

    public static String generate(String urlResourcePart, Map<String, String> params, String secretKey) {
        try {
            List<Map.Entry<String, String>> parameters = new LinkedList(params.entrySet());
            Collections.sort(parameters, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return ((String)o1.getKey()).compareTo((String)o2.getKey());
                }
            });
            StringBuilder sb = new StringBuilder();
            sb.append(urlResourcePart).append("_");
            Iterator it = parameters.iterator();

            while(it.hasNext()) {
                Map.Entry<String, String> param = (Map.Entry)it.next();
                sb.append(param.getKey()).append("=").append(param.getValue()).append("_");
            }

            sb.append(secretKey);
            String baseString = URLEncoder.encode(sb.toString(), "UTF-8");
            return MD5Util.md5(baseString);
        } catch (Exception e) {
            log.error("生成B-Link签名失败", e);
            return null;
        }
    }

    public static String generate(String url, String appkey, String secretkey, Map<String, ?> params) {
        try {
            Matcher slashMatcher = compile("/").matcher(url);
            int mIdx = 0;
            while(slashMatcher.find()) {
                mIdx++;
                if(mIdx == 3){
                    break;
                }
            }
            int index = slashMatcher.start();
            String urlResourcePart = url.substring(index + 1, url.length());
            Map<String, String> paramMap = Maps.newHashMap();
            paramMap.put("appkey", appkey);
            if(params != null && !params.isEmpty()) {
                Iterator it = params.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry<String, Object> param = (Map.Entry)it.next();
                    paramMap.put(param.getKey(), String.valueOf(param.getValue()));
                }
            }
            return generate(urlResourcePart, paramMap, secretkey);
        } catch (Exception e) {
            log.error("生成B-Link签名失败", e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        //车型对应车辆服务
//        String url="vehicle-service/internal/vehModelConfigs";
        //车辆服务信息
//        String url="vehicle-service/internal/packageService";
        //车型接口
        String url="http://exttest.tsp.borgward.com.cn/openapi/location/location/getLastLocationForNewApp";
        Map<String, Object> params = Maps.newHashMap();
//        ?appkey=3917230828&sign=7e6be61be58f17925146808c154c4eaf
        String appKey = "3917230828";
        String secretKey = "2d3e04538ce7ec0a4b21851747eda121";
        params.put("uniqueId", "LVCP2GVC4GA001093");
        params.put("appid", "FOTON");
        String sign=generate(url, appKey, secretKey, params);
        System.out.println("生产的sign："+sign);
    }
}