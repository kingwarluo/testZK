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
public class GoodDateFormat {

    // 使用threadlocal可以有效的解决线程安全问题
    ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        GoodDateFormat f = new GoodDateFormat();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println(f.sdf.get().parse("2020-11-18 12:12:12"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
