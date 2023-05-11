package com.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisUtil {

    private static final String CONNECT_STRING = "192.168.0.18:16379";
    private static final String PASSWORD = "xk@redis";

    private static RedisUtil util = null;

    public RedisUtil(){}

    public static RedisUtil getInstance(){
        if(util == null){
            synchronized (RedisUtil.class){
                util = new RedisUtil();
            }
        }
        return util;
    }

    public static RedissonClient getRedisClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + CONNECT_STRING);
        config.useSingleServer().setPassword(PASSWORD);
        config.useSingleServer().setDatabase(8);
        RedissonClient client = Redisson.create(config);
        return client;
    }

    public static void shutDown(RedissonClient client){
        client.shutdown();
    }

}
