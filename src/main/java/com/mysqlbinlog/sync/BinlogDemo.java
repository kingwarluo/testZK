package com.mysqlbinlog.sync;

import java.io.IOException;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/7/4 16:19
 */
public class BinlogDemo {

    public static void main(String[] args) {
        BinlogHandler binlogHandler = new BinlogHandler();
        new Thread(() -> {
            try {
                binlogHandler.startBinLogListener();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
