package com.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat引发的多线程问题
 *
 * @author jianhua.luo
 * @date 2020/11/17
 */
public class FaultDateFormat {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        FaultDateFormat f = new FaultDateFormat();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println(f.sdf.parse("2020-11-18 12:12:12"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
