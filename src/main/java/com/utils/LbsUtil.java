package com.utils;
import java.text.DecimalFormat;


/**
 * @Authror zdf
 * @Date 2020/11/12 16:40
 * @Version 1.0
 */
public class LbsUtil {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    /**
     * 计算两个经纬度之间的距离
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return km
     */
    public static double getDistanceWithMeter(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //转米
        s = s * 1000;
        int integer = Double.valueOf(s).intValue();
        DecimalFormat df = new DecimalFormat("#.000");
        s = Double.parseDouble(df.format(integer));
        //返回距离默认为km，如果需要其他单位请记得换算
        return s;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        DecimalFormat df = new DecimalFormat("#.000");
        s = Double.parseDouble(df.format(s));

        //返回距离默认为km，如果需要其他单位请记得换算
        return s;
    }

    public static void main(String[] args) {
        // 1426105726751453337
        double distance = getDistance(28.7021540000, 115.8076500000, 28.6680470000, 115.9690640000);
        // 1426105726751453201
        double distance2 = getDistance(28.6572740000, 115.8867700000, 28.6680470000, 115.9690640000);
        System.out.println(distance);
        System.out.println(distance2);
    }


}
