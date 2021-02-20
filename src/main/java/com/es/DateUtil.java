package com.es;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lijizhong on 2017/9/7.
 */
public class DateUtil {

    /**
     * 将YYYY_MM_DD_HH_MI_SS转换成YYYYMMDD格式
     * @param time
     * @return
     */
    public static String formart4YYYYMMDD(String time) {
        if (StringUtils.isNotBlank(time)) {
            Date date = TimeTools.parseYYYY_MM_DD_HH_MI_SS(time);
            return TimeTools.format4YYYYMMDD(date);
        } else {
            return null;
        }
    }

    /**
     * 将YYYY_MM_DD_HH_MI_SS转换成YYYYMMDDHHMISS格式
     * @param time
     * @return
     */
    public static String formartYYYY_MM_DD_HH_MI_SS(String time) {
        if (StringUtils.isNotBlank(time)) {
            Date date = TimeTools.parseYYYY_MM_DD_HH_MI_SS(time);
            return TimeTools.format4YYYYMMDDHHMISS(date);
        } else {
            return null;
        }
    }

    public static class TimeTools {

        static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");

        static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");

        public static Date parseYYYY_MM_DD_HH_MI_SS(String time) {
            try {
                return sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String format4YYYYMMDDHHMISS(Date date) {
            return sdf2.format(date);
        }

        public static String format4YYYYMMDD(Date date) {
            return sdf3.format(date);
        }
    }

}
