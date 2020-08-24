package com.tcp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class LocalUtils {

    /**
     * 获取本机ip
     * @return
     */
    public static String getLocalIp() {
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            String ip = null;

            while(netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                Enumeration ips = ni.getInetAddresses();

                while(ips.hasMoreElements()) {
                    InetAddress ipObj = (InetAddress)ips.nextElement();
                    if (ipObj.isSiteLocalAddress()) {
                        ip = ipObj.getHostAddress();
                        return ip;
                    }
                }
            }
            return ip;
        } catch (Exception var5) {
            return null;
        }
    }

}
