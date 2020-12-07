package com.thread;

import java.util.LinkedList;

/**
 * db连接池超时
 *
 * @author jianhua.luo
 * @date 2020/12/7
 */
public class DBConnTimeOut {


    static class Connection {
        DBPool dbPool;

        {
            try {
                dbPool = new DBPool(5, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class DBPool {

        private LinkedList<Connection> pool = new LinkedList<>();

        public DBPool(int init, long mills) throws InterruptedException {
            if (init > 0) {
                pool.addLast(getConnection(mills));
            }
        }

        public Connection getConnection(long mills) throws InterruptedException {
            synchronized (pool) {
                // mills<0表示永不超时
                if(mills < 0) {
                    while(pool.isEmpty()) {
                        pool.wait(mills);
                    }
                    return pool.removeFirst();
                } else {
                    long overTime = System.currentTimeMillis() + mills;
                    long reTime = mills;
                    while(pool.isEmpty() && reTime > 0) {
                        pool.wait(reTime);
                        reTime = overTime - System.currentTimeMillis();
                    }
                    Connection conn = null;
                    if(!pool.isEmpty()) {
                        conn = pool.removeFirst();
                    }
                    return conn;
                }
            }
        }
    }

}
